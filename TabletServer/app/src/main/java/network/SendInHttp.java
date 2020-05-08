package network;

import com.pentacore.tabletserver.MainActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;

public class SendInHttp implements Runnable {
    private JSONObject jsonObject;

    public SendInHttp(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        OutputStream os = null;
        HttpURLConnection con = null;

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
        int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
        String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기

        try {

            System.out.println("SendInHttp [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
            //URL url = new URL ("http://70.12.113.195/WebApp/receivefl.pc");
            URL url = new URL ("http://70.12.113.195/WebApp/receivefl.pc");
            con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);
            os = con.getOutputStream(); //(HTTPLog)-Static: isSBSettingEnabled false

            OutputStreamWriter out = new OutputStreamWriter(os);
            System.out.println("http://70.12.113.195/WebApp/receivefl.pc로 송신 : " + jsonObject.toString());

            out.write(jsonObject.toString());
            out.flush();
            con.getInputStream();

        } catch (IOException e) {
            System.out.println("Error at SendInHttp : "+e.getMessage());
            //e.printStackTrace();
        } finally {
            try {
                if(os!=null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*BufferedReader br = null;
        InputStreamReader isr = null;

        try{
            isr = new InputStreamReader(con.getInputStream(), "utf-8");
            br = new BufferedReader(isr);
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(isr != null) isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }
}
