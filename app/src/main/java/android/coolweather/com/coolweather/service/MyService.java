package android.coolweather.com.coolweather.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    Context mContext;
    int mFlag =4;
    boolean isStop = false ;
    Thread thread ;
    private Callback callback;


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new Binder();
    }


    public class Binder extends android.os.Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Yzy", "onCreate: 服务开启");
        startAdd();
    }

    @Override
    public void onDestroy() {
        isStop = true;
        super.onDestroy();
        Log.d("Yzy", "onCreate: 服务销毁");

    }

    public void  startAdd()
    {
        thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                while (!isStop)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    for(;mFlag>=0;mFlag-- ){
//
//                    }
                    mFlag--;
                    Log.d("Yzy", "run: -----当前flag"+mFlag);

                    if (callback != null)
                    {
                        callback.onDataChange(mFlag+ "");
                    }
                }
            }
        };

        thread.start();
    }


    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public  interface Callback {
        void onDataChange(String data);
    }


}
