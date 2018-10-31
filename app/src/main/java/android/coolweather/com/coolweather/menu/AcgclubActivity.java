package android.coolweather.com.coolweather.menu;

import android.content.SharedPreferences;
import android.coolweather.com.coolweather.R;
import android.coolweather.com.coolweather.WeatherActivity;
import android.coolweather.com.coolweather.util.HttpUtil;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AcgclubActivity extends AppCompatActivity {

    private TextView tet7;
    private ImageView ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acgclub);
        tet7=(TextView)findViewById(R.id.tet7);
        ima=(ImageView)findViewById(R.id.ima);
        loadpicture();
    }





    private void loadpicture() {
        String url="https://rabtman.com/api/v2/acgclub/pictures";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String responseText =response.body().string();
                 String title = null;
               String image =null;
                 try {
                     JSONObject jsonObject = new JSONObject(responseText);
                     JSONArray jsonArray=jsonObject.getJSONArray("data");
                     JSONObject jsonObject1=jsonArray.getJSONObject(0);
                     title=jsonObject1.getString("title");
                     JSONArray jsonArray1=jsonObject1.getJSONArray("imgUrls");
                     image=jsonArray1.getString(0);
                     //loadpicture1(image);
                 }catch (Exception e){
                     e.printStackTrace();

                 }
                final String finalTitle = title;
                 final String finalImage=image;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tet7.setText(finalTitle);
                        Glide.with(AcgclubActivity.this).load(finalImage).into(ima);

                    }
                });

            }
            @Override
            public void onFailure(Call call, IOException e) {

            }


        });
    }




}
