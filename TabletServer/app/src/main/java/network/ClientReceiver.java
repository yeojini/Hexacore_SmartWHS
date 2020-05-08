package network;

import android.graphics.Color;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.DomainCombiner;
import java.util.concurrent.ThreadPoolExecutor;

import logistics.TaskQueueAdapter;
import msg.Msg;
import msg.Task;

public class ClientReceiver implements Runnable {

    InputStream is;
    ObjectInputStream ois;

    Socket socket;

    public ClientReceiver() {

    }

    public ClientReceiver(Socket socket) {
        System.out.println("ClientReceiver 객체 생성");
        this.socket = socket;

        try {
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            System.out.println("Connected to TCP/IP Server : "+socket.getInetAddress().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
        int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
        String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
        System.out.println("ClientReceiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);

        while(ois!=null) {

            Msg msg = null;
            try {
                msg = (Msg) ois.readObject();
                // msg에 들어있는 task를 queue에 추가
                Task task = msg.getTask();
                MainActivity.taskQueue.offer(msg.getTask());
                // taskUI 바꿔주는 메소드 호출;
                MainActivity.updateTaskQueueUI();
                task.getIo();
                task.getName();
                task.getQty();
                task.getLocX();
                task.getLocY();
                MainActivity.printConsole("새로운 테스크 "+task.getIo()+", "+task.getName()+"*"+task.getQty()+"("+task.getLocX()+", "+task.getLocY()+")가 추가되었습니다.");
//                System.out.println("taskQueue Size : "+MainActivity.taskQueue.size());

//                // taskQueue랑 forkLiftQueue랑 비교해서 할당하는 메소드 호출
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Error at ClientReceiver");

                String dstnIP = "70.12.113.200";
                int dstnPort = 9999;
                Runnable client = new Client(dstnIP, dstnPort);
                MainActivity.executorService.execute(client);
                e.printStackTrace();
                break;
            }

        }

        try {
            if (ois != null) ois.close();
            if (socket != null) socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
