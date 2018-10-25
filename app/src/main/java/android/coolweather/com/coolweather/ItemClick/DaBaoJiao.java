package android.coolweather.com.coolweather.ItemClick;

import android.coolweather.com.coolweather.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DaBaoJiao extends AppCompatActivity {

    private TextView tet3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_bao_jiao);
        tet3=(TextView)findViewById(R.id.tet3);
        tet3.setText(   "大堡礁（英文：The Great Barrier Reef），是世界最大最长的珊瑚礁群，位于南半球，它纵贯于澳洲的东北沿海，北从托雷斯海峡，南到南回归线以南，绵延伸展共有2011公里，最宽处161公里。有2900个大小珊瑚礁岛，自然景观非常特殊。大堡礁的南端离海岸最远有241公里，北端较靠近，最近处离海岸仅16公里。在落潮时，部分的珊瑚礁露出水面形成珊瑚岛。在礁群与海岸之间是一条极方便的交通海路。风平浪静时，游船在此间通过，船下连绵不断的多彩、多形的珊瑚景色，就成为吸引世界各地游客来猎奇观赏的最佳海底奇观。1981年列入世界自然遗产名录。\n" +
                "2017年3月，科学家发现，大堡礁最原始的北部区域的大部分珊瑚礁2016年已被过热的海水杀死。往南一些，大堡礁中部的珊瑚礁2016年得以幸免，然而现在正在白化，这是另一场大规模死亡的潜在预兆。");
    }
}
