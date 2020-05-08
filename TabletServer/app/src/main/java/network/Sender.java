package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Sender implements Runnable{

    OutputStream os;
    ObjectOutputStream oos;
    Msg msg;


    public Sender () {

    }

    public Sender(Socket socket) throws IOException {
        String dstnIP = socket.getInetAddress().toString();
        oos = ActiveConnection.ipToOos.get(dstnIP);
        if(oos==null) {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
        }
    }

    public Sender(Msg msg) {
        this.msg = msg;
    }

    public Sender(Socket socket,Msg msg) throws IOException {
        this.msg = msg;

        String dstnIP = socket.getInetAddress().toString();
        oos = ActiveConnection.ipToOos.get(dstnIP);
        if(oos==null) {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
        }
    }

    @Override
    public void run() {


        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
        int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
        String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기

        System.out.println("Sender [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);

        if(oos!=null) {

            try {
                oos.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

