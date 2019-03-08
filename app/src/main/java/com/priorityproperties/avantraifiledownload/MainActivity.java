package com.priorityproperties.avantraifiledownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ResponseListner {


    Button downLoad;

    private long endTime;
    private long startTime;
    private TextView vT_time;
    private int seconds;
    private long executionTime;
    private int minutes;
    private String timeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        downLoad.setOnClickListener(this);
    }

    private void initViews() {
        downLoad = findViewById(R.id.download_btn);
        vT_time = findViewById(R.id.vT_time);
    }

    @Override
    public void onClick(View view) {
        defaultValue();
        callForDownLoadFile();
    }

    private void defaultValue() {
        startTime = 0;
        endTime = 0;
        seconds = 0;
        executionTime=0;
        minutes=0;
        vT_time.setText("0");
    }

    public void callForDownLoadFile() {
        String pageNumber;

        synchronized (this) {


            for (char i = 'a'; i <= 'z'; i++) {
                pageNumber = "wb1913_" + i + ".html";
                System.out.print("FileNumber : " + pageNumber);

                new FileDownloadTask(this,pageNumber).execute();


            }


        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        defaultValue();

    }

    @Override
    public void onRespose(String response) {
        System.out.println("FinalOne" + response);
        synchronized (this) {
            if (response != null && response.toString() != null) {

                System.out.print("Response" + response.toString());
                synchronized (this) {


                    DbHelper dbHelper = new DbHelper(this);
                    startTime = System.currentTimeMillis();
                    System.out.println("Started Time in minutes" + startTime);
                    dbHelper.insertNote(response.toString());
                    endTime = startTime + startTime;
                    System.out.println("End Time minutes" + TimeUnit.MILLISECONDS.toMinutes(endTime));
                     executionTime = TimeUnit.MILLISECONDS.toMinutes(endTime - startTime);
                    seconds = seconds+(int) ((executionTime / 1000) %60);

                    if (seconds==0){
                        minutes=1;
                    }else {
                      timeElapsed= String.valueOf(seconds/60);
                    }

                    if (seconds>60){

                        System.out.println("Total Execution Time(minutes) " + seconds);
                    }else {
                        System.out.println("Total Execution Time(seconds) " + seconds);
                    }


                        System.out.println("Total Execution Time Elapsed(seconds) " + seconds);





                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (timeElapsed!=null)
                            vT_time.setText(timeElapsed +"min");
                           else
                        vT_time.setText(String.valueOf(seconds) + " seconds");
                    }
                });

            }
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        startTime = 0;
        endTime = 0;
        seconds = 0;
        minutes=0;
    }
}
