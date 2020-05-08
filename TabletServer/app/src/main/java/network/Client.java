package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;


public class Client implements Runnable {
    String srcID;
    String dstnID;
    String dstnIP;
    int dstnPort;
    Socket socket;

    OutputStream os;
    ObjectOutputStream oos;

    public Client() {}
    public Client(String dstnIP,int dstnPort) {
        System.out.println("Class Client = ip:port="+dstnIP+":"+dstnPort);
        this.dstnIP = dstnIP;
        this.dstnPort = dstnPort;
        srcID = "tabletServer";
        dstnID = "tcpipServer";
    }

    @Override
    public void run() {

        try {
            socket = makeSocket(dstnIP,dstnPort);
            System.out.println("Connected to TCP/IP Server → "+dstnIP+":"+dstnPort);
            MainActivity.printConsole("Connected to TCP/IP Server → "+dstnIP+":"+dstnPort);


        } catch (Exception outerE) {
            System.out.println("Exception while making socket at Client.java");
            outerE.printStackTrace();
            while (true) {
                System.out.println("Retry to make socket at Client.java");
                MainActivity.printConsole("Retry to make socket at Client.java");
                try {
                    Thread.sleep(1000);
                    socket = makeSocket(dstnIP,dstnPort);
                    System.out.println("Connected to TCP/IP Server → "+dstnIP+":"+dstnPort);
                    MainActivity.printConsole("Connected to TCP/IP Server → "+dstnIP+":"+dstnPort);
                    break;
                } catch (Exception innerE) {
                    System.out.println("Exception while making socket at Client.java");
                    innerE.printStackTrace();
                }
            }
        }

        try {

            int poolSize = ((ThreadPoolExecutor)MainActivity.executorService).getPoolSize();//스레드 풀 사이즈 얻기
            String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
            System.out.println(" Client [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
            Runnable clientReceiver = new ClientReceiver(socket);
            MainActivity.executorService.submit(clientReceiver);
        } catch (Exception outerE) {
            System.out.println("Exception occured while generating ClientReceiver");
//            outerE.printStackTrace();
            while (true) {
                System.out.println("Retry to Generate ClientReceiver..");
                MainActivity.printConsole("Retry to Generate ClientReceiver..");
                try {
                    Thread.sleep(1000);
                    Runnable clientReceiver = new ClientReceiver(socket);
                    MainActivity.executorService.submit(clientReceiver);
                    System.out.println("Client Receiver Generated");
                } catch (Exception innerE) {
                    System.out.println("Exception occured while generating ClientReceiver");
//                    innerE.printStackTrace();
                }
            }
        }
    }

    public Socket makeSocket(String dstnIP,int dstnPort) throws IOException {
        Socket newSocket = new Socket(dstnIP, dstnPort);
        os = newSocket.getOutputStream();
        oos = new ObjectOutputStream(os);
        System.out.println("Client.java : os,oos : "+os+", "+oos);
        oos.writeObject(new Msg(null, srcID, null, dstnID));
        return newSocket;
    }
}
