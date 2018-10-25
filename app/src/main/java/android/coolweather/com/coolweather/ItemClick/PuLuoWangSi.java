package android.coolweather.com.coolweather.ItemClick;

import android.coolweather.com.coolweather.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PuLuoWangSi extends AppCompatActivity {

    private TextView tet5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu_luo_wang_si);
        tet5=(TextView)findViewById(R.id.tet5);
        tet5.setText("   普罗旺斯（Provence），位于法国东南部，是一个濒临地中海的蓝色海岸，薰衣草的故乡。该地出产优质的葡萄酒。\n" +
                "普罗旺斯是一座“骑士之城”，也是中世纪骑士叙情诗的发源地，著名的城镇有马赛和艾克斯等。\n" +
                "普罗旺斯和意大利接壤，是从地中海沿岸延伸到内陆的丘陵地带。中间有大河隆河流过。从阿尔卑斯山经里昂南流的罗讷河，在普罗旺斯附近分为两大支流，然后注入地中海。\n" +
                "普罗旺斯是世界闻名的薰衣草故乡，并出产优质葡萄酒。普罗旺斯还是欧洲的“骑士之城”，是中世纪重要文学体裁骑士抒情诗的发源地。\n" +
                "普罗旺斯境内有艾克斯、马赛等名城，还有阿尔勒、葛德、阿维尼翁（又译亚维农）、尼姆等市镇组成。此地区物产丰饶、阳光明媚、风景优美，从古希腊、古罗马时代起就吸引着无数游人，至今依然是旅游胜地。");
    }
}
