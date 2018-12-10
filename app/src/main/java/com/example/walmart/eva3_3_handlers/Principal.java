package com.example.walmart.eva3_3_handlers;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    Thread THilo;
    TextView Txt1;
    Handler HHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                int i = (int)msg.obj;
               Txt1.append(i+"_");
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Txt1 = findViewById(R.id.Txt1);
        MiHilo mhHilo = new MiHilo();
        THilo = new Thread(mhHilo);
        THilo.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        THilo.interrupt();
    }
    class  MiHilo implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (true){
                i++;
                try {
                    Message msg =  HHandler.obtainMessage(1,i);
                    HHandler.sendMessage(msg);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

    }
}
