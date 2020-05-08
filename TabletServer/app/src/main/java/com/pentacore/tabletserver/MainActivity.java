package com.pentacore.tabletserver;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import logistics.ConsoleQueueAdapter;
import logistics.ForkLift;
import logistics.ForkLiftViewSet;
import logistics.TaskQueueAdapter;
import logistics.Warehouse;
import msg.Msg;
import msg.Task;
import network.Client;
import network.Server;
import network.SendInTcpip;


public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity;

    static Warehouse warehouse;
    ForkLift forkLift1, forkLift2, forkLift3, forkLift4;
    static ForkLiftViewSet forkLiftViewSet1, forkLiftViewSet2, forkLiftViewSet3, forkLiftViewSet4;

    public static Map<String, ForkLift> forkLiftMap;
    static Map<String, ForkLiftViewSet> forkLiftViewSetMap;

    static int WORKING = 0;
    static int WAITING = 1;
    static int CHARGING = 2;

    static ConstraintLayout layoutWarehouseMap;
    public static Queue taskQueue;
    public static Queue<String> waitingForkLiftQueue;
    public static Queue consoleQueue;

    private static RecyclerView taskQueueRecyclerView;
    public static TaskQueueAdapter taskQueueAdapter;

    private RecyclerView consoleQueueRecyclerView;
    private static ConsoleQueueAdapter consoleQueueAdapter;

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    Runnable server;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        // Run TabletServer
        int port=8888;
        server = new Server(port);
        executorService.execute(server);

        // Connect to TCP/IP Server
        String dstnIP = "70.12.113.200";
        int dstnPort = 9999;
        Runnable client = new Client(dstnIP, dstnPort);
        executorService.execute(client);

        warehouse = new Warehouse(26, 14);
        forkLift1 = new ForkLift("forklift1");
        forkLift2 = new ForkLift("forklift2");
        forkLift3 = new ForkLift("forklift3");
        forkLift4 = new ForkLift("forklift4");

        forkLiftMap = new HashMap<String, ForkLift>();
        forkLiftMap.put(forkLift1.getName(), forkLift1);
        forkLiftMap.put(forkLift2.getName(), forkLift2);
        forkLiftMap.put(forkLift3.getName(), forkLift3);
        forkLiftMap.put(forkLift4.getName(), forkLift4);

        forkLiftViewSet1 = new ForkLiftViewSet();
        forkLiftViewSet1.status = findViewById(R.id.forkLift1_status);
        forkLiftViewSet1.taskContent = findViewById(R.id.forkLift1_taskContent);
        forkLiftViewSet1.temperatureCurrent = findViewById(R.id.forkLift1_temparatureCurrent);
        forkLiftViewSet1.batteryCurrent = findViewById(R.id.forkLift1_batteryCurrent);
        forkLiftViewSet1.forkLiftView = findViewById(R.id.forkLift1);

        forkLiftViewSet2 = new ForkLiftViewSet();
        forkLiftViewSet2.status = findViewById(R.id.forkLift2_status);
        forkLiftViewSet2.taskContent = findViewById(R.id.forkLift2_taskContent);
        forkLiftViewSet2.temperatureCurrent = findViewById(R.id.forkLift2_temparatureCurrent);
        forkLiftViewSet2.batteryCurrent = findViewById(R.id.forkLift2_batteryCurrent);
        forkLiftViewSet2.forkLiftView = findViewById(R.id.forkLift2);

        forkLiftViewSet3 = new ForkLiftViewSet();
        forkLiftViewSet3.status = findViewById(R.id.forkLift3_status);
        forkLiftViewSet3.taskContent = findViewById(R.id.forkLift3_taskContent);
        forkLiftViewSet3.temperatureCurrent = findViewById(R.id.forkLift3_temparatureCurrent);
        forkLiftViewSet3.batteryCurrent = findViewById(R.id.forkLift3_batteryCurrent);
        forkLiftViewSet3.forkLiftView = findViewById(R.id.forkLift3);

        forkLiftViewSet4 = new ForkLiftViewSet();
        forkLiftViewSet4.status = findViewById(R.id.forkLift4_status);
        forkLiftViewSet4.taskContent = findViewById(R.id.forkLift4_taskContent);
        forkLiftViewSet4.temperatureCurrent = findViewById(R.id.forkLift4_temparatureCurrent);
        forkLiftViewSet4.batteryCurrent = findViewById(R.id.forkLift4_batteryCurrent);
        forkLiftViewSet4.forkLiftView = findViewById(R.id.forkLift4);

        forkLiftViewSetMap = new HashMap<String, ForkLiftViewSet>();
        forkLiftViewSetMap.put("forklift1", forkLiftViewSet1);
        forkLiftViewSetMap.put("forklift2", forkLiftViewSet2);
        forkLiftViewSetMap.put("forklift3", forkLiftViewSet3);
        forkLiftViewSetMap.put("forklift4", forkLiftViewSet4);

        taskQueue = new LinkedList();
        waitingForkLiftQueue = new LinkedList<String>();
        consoleQueue = new LinkedList();

        LinearLayoutManager taskQueueLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager consoleQueueLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        taskQueueRecyclerView = findViewById(R.id.RecyclerView_taskQueue);
        taskQueueRecyclerView.setLayoutManager(taskQueueLayoutManager);
        taskQueueAdapter = new TaskQueueAdapter(taskQueue);
        taskQueueRecyclerView.setAdapter(taskQueueAdapter);

        consoleQueueRecyclerView = findViewById(R.id.RecyclerView_consoleQueue);
        consoleQueueRecyclerView.setLayoutManager(consoleQueueLayoutManager);
        consoleQueueAdapter = new ConsoleQueueAdapter(consoleQueue);
        consoleQueueRecyclerView.setAdapter(consoleQueueAdapter);

        View generateTask = findViewById(R.id.titleTaskQueue);
        generateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick();
            }
        });

        View assignTask = findViewById(R.id.titleForkLift);
        assignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignTask();
            }
        });
    }

    boolean firstFocusOnWindowFlag = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(!firstFocusOnWindowFlag) return;
        layoutWarehouseMap = findViewById(R.id.layoutWarehouseMap4);
        warehouse.setScreenSize(layoutWarehouseMap.getWidth(), layoutWarehouseMap.getHeight());

        locateForkLift(forkLiftViewSet1.forkLiftView, 11, 13);
        locateForkLift(forkLiftViewSet2.forkLiftView, 12, 13);
        locateForkLift(forkLiftViewSet3.forkLiftView, 13, 13);
        locateForkLift(forkLiftViewSet4.forkLiftView, 14, 13);
        firstFocusOnWindowFlag = false;
    }

    public void btnClick() {
        int[] arrayX = {2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23};
        int[] arrayY = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        Task task = new Task(
                (int)(Math.random()*2),
                "item_no_"+(int)(Math.random()*99),
                (int)(Math.random()*10),
                arrayX[(int)(Math.random()*arrayX.length)],
                arrayY[(int)(Math.random()*arrayY.length)]
        ); // int io, String name, int qty, int locX, int locY
        MainActivity.taskQueue.offer(task);
//        locateForkLift(forkLiftViewSet1.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
//        locateForkLift(forkLiftViewSet2.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
//        locateForkLift(forkLiftViewSet3.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
//        locateForkLift(forkLiftViewSet4.forkLiftView, (int)(Math.random()*26), (int)(Math.random()*14));
    }

    public static void locateForkLift(View forkLiftView, int dstnX, int dstnY) {
        ConstraintSet set = new ConstraintSet();
        if(layoutWarehouseMap==null) return;
        set.clone(layoutWarehouseMap);
        set.connect(forkLiftView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, (int)warehouse.axisX[dstnX]);
        set.connect(forkLiftView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, (int)warehouse.axisY[dstnY]);
        set.applyTo(layoutWarehouseMap);
    }

    public static void updateForkLiftUI(String forkLiftID) {
//        printConsole("updateForkLiftUI : "+forkLiftID);
        final ForkLiftViewSet forkLiftViewset = (ForkLiftViewSet)forkLiftViewSetMap.get(forkLiftID);
        final ForkLift forkLift = (ForkLift)forkLiftMap.get(forkLiftID);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                taskQueueAdapter.updateTaskQueue(taskQueue);
                if(forkLift.getStatus()==0) {
                    ((TextView)forkLiftViewset.status).setText("WORKING");
                    ((TextView)forkLiftViewset.status).setTextColor(Color.GREEN);
                } else if(forkLift.getStatus()==1) {
                    ((TextView)forkLiftViewset.status).setText("WAITING");
                    ((TextView)forkLiftViewset.status).setTextColor(Color.BLUE);
                } if(forkLift.getStatus()==2) {
                    ((TextView)forkLiftViewset.status).setText("CHARGING");
                    ((TextView)forkLiftViewset.status).setTextColor(Color.RED);
                }
                ((TextView)forkLiftViewset.taskContent).setText(forkLift.getCurrentTask()+"");
                ((TextView)forkLiftViewset.temperatureCurrent).setText(forkLift.getTemparature()+"");
                ((TextView)forkLiftViewset.batteryCurrent).setText(forkLift.getBattery()+"");
                locateForkLift((ImageView)forkLiftViewset.forkLiftView, forkLift.getCurrentX(), forkLift.getCurrentY());
            }
        });


    }

    public static void assignTask() {
        if (taskQueue.isEmpty()) {
            Toast.makeText(MainActivity.mainActivity,"현재 태스크가 없습니다.",Toast.LENGTH_SHORT).show();
            return;
        } else if (waitingForkLiftQueue.isEmpty()) {
            Toast.makeText(MainActivity.mainActivity,"대기중인 지게차가 없습니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        String polledForkLift = waitingForkLiftQueue.poll(); // 여기서 가져온 ID를 밑에 Msg에 담아서
        logistics.ForkLift forkLift = forkLiftMap.get(polledForkLift);
        MainActivity.forkLiftMap.get(forkLift.getName()).setStatus(WORKING); // status만 일단 먼저 바꿈

        msg.Task task = (msg.Task)(taskQueue.poll());
        Msg msg = new Msg("warehouse1", forkLift.getName()); // srcID, dstnID
        msg.setTask(task);
        forkLift.setTask(task);

        Runnable sendInTcpip = new SendInTcpip(msg); //전송
        MainActivity.executorService.submit(sendInTcpip);

        // taskUI 바꿔주는 메소드 호출
        updateTaskQueueUI();
    }

    public static void updateTaskQueueUI() {
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                taskQueueAdapter.updateTaskQueue(taskQueue);
            }
        });
    }

    public static void printConsole(String logMessage) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        consoleQueue.offer("["+simpleDate.format(new Date())+"] "+logMessage);
        if(consoleQueue.size()>50) consoleQueue.poll();
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                consoleQueueAdapter.updateConsoleQueue(consoleQueue);
            }
        });
    }

    private long lastTimeBackPressed;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        lastTimeBackPressed = System.currentTimeMillis();
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if(((Server)server).serverSocket!=null) ((Server)server).serverSocket.close();
            if(((Server)server).socket!=null) ((Server)server).socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void translateAnim(float xStart, float xEnd, float yStart, float yEnd, int duration, View view) {
//        TranslateAnimation translateAnimation = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF,xStart,
//                Animation.RELATIVE_TO_SELF, xEnd,
//                Animation.RELATIVE_TO_SELF,yStart,
//                Animation.RELATIVE_TO_SELF,yEnd);
//        translateAnimation.setDuration(duration);
//        translateAnimation.setFillAfter(true);
//        view.startAnimation(translateAnimation);
//    }

}
