package android.coolweather.com.coolweather.Boot;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.coolweather.com.coolweather.MainActivity;
import android.coolweather.com.coolweather.R;
import android.coolweather.com.coolweather.service.MyService;
import android.coolweather.com.coolweather.util.HttpUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BootActivity extends AppCompatActivity {

    private final int time = 3600;
    private boolean lag = true;
    private ImageView bootimage;
    private TextView btn2;
    int flag = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_boot);
        bootimage=(ImageView)findViewById(R.id.bootimage);

        /*动画效果*/
        AlphaAnimation a = new AlphaAnimation(0,1);
        a.setDuration(2400);//毫秒
        bootimage.startAnimation(a);
        /*加载背景图*/
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String bingPic=prefs.getString("bing_pic",null);
        if(bingPic !=null) {
            Glide.with(this).load(bingPic).into(bootimage);
        }else{
            loadBingPic();
        }


        Intent bindservice=new  Intent(this,MyService.class);
        bindService(bindservice,connection,BIND_AUTO_CREATE);



        /*按钮点击跳过*/
        btn2=(TextView) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    lag = false ;
                    Intent intent = new Intent(BootActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();


            }
        });

        /*延迟time进入界面*/
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {//延时time秒后，将运行如下代码
                if (lag) {
                    Intent intent = new Intent(BootActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, time);


        }



    /*
     * 加载必应每日一图*/
    private void loadBingPic() {
        String requestBingPic ="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic =response.body().string();
                SharedPreferences.Editor editor=PreferenceManager.
                        getDefaultSharedPreferences(BootActivity.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(BootActivity.this).load(bingPic).into(bootimage);
                    }
                });
            }
        });
    }


    private ServiceConnection connection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            MyService.Binder  binder = (MyService.Binder)iBinder;
            MyService myTestService = binder.getService();
            myTestService.setCallback(new MyService.Callback() {
                @Override
                public void onDataChange(String data) {
                    Log.d("Yzy", "回传回来的数据: "+data);
                    flag = Integer.parseInt(data);
                    Message msg = new Message();
                    msg.obj = data;
                    handler.sendMessage(msg);

                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btn2.setText("跳过"+msg.obj.toString());
        }
    };


}
