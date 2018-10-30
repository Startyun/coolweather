package android.coolweather.com.coolweather.menu;

import android.coolweather.com.coolweather.R;
import android.coolweather.com.coolweather.util.HttpUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AcgclubActivity extends AppCompatActivity {

    private TextView tet7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acgclub);
        tet7=(TextView)findViewById(R.id.tet7);
        sendRequestWithOKHttp();

    }





    private void loadpicture() {
        String url="https://rabtman.com/api/v2/acgclub/pictures";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String responseText =response.body().string();
                 try {
                     JSONObject jsonObject = new JSONObject(responseText);
                     JSONArray jsonArray=jsonObject.getJSONArray("data");
                     JSONObject jsonObject1=jsonArray.getJSONObject(0);
                     String title=jsonObject1.getString("title");
                     Toast.makeText(AcgclubActivity.this, title, Toast.LENGTH_SHORT).show();
                 }catch (Exception e){
                     e.printStackTrace();

                 }

            }
            @Override
            public void onFailure(Call call, IOException e) {

            }


        });
    }



    private void sendRequestWithOKHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://rabtman.com/api/v2/acgclub/pictures")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseText = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseText);
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        JSONObject jsonObject1=jsonArray.getJSONObject(0);
                        String title=jsonObject1.getString("title");
                        Toast.makeText(AcgclubActivity.this, title, Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



}
