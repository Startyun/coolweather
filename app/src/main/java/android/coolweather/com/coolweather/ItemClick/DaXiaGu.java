package android.coolweather.com.coolweather.ItemClick;

import android.coolweather.com.coolweather.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DaXiaGu extends AppCompatActivity {


    private TextView tet4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click1);
        tet4=(TextView)findViewById(R.id.tet4);
        tet4.setText("   美国大峡谷（Grand Canyon）是一个举世闻名的自然奇观，科罗拉多河穿流其中，它是联合国教科文组织选为受保护的天然遗产之一。大峡谷位于美国亚利桑那州西北部的凯巴布高原上，是地球上最为壮丽的景色之一。\n" +
                "科罗拉多河在科罗拉多高原上共切割出19条主要峡谷，总面积为2724.7平方公里，其中最深、最宽、最长的一个就是大峡谷。它全长446公里，是世界上最长的峡谷之一。峡谷顶宽6至28公里，最深处1800米。谷底水面不足 1000米宽，夏季冰雪融水，水深增至18米。山石多为红色。");
    }
}
