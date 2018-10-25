package android.coolweather.com.coolweather.ItemClick;

import android.coolweather.com.coolweather.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaiJiLing extends AppCompatActivity {


    private TextView tet6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_ji_ling);
        tet6=(TextView)findViewById(R.id.tet6);
        tet6.setText("   泰姬陵（Taj Mahal ），是印度知名度最高的古迹之一，世界文化遗产，被评选为“世界新七大奇迹”。 [1] \n" +
                "泰姬陵全称为“泰姬·玛哈拉”，是一座白色大理石建成的巨大陵墓清真寺，是莫卧儿皇帝沙贾汗为纪念他心爱的妃子于1631年至1653年在阿格拉而建的。位于今印度距新德里200多公里外的北方邦的阿格拉(Agra)城内，亚穆纳河右侧。由殿堂、钟楼、尖塔、水池等构成，全部用纯白色大理石建筑，用玻璃、玛瑙镶嵌，具有极高的艺术价值。\n" +
                "泰姬陵是印度穆斯林艺术最完美的瑰宝，是世界遗产中的经典杰作之一，被誉为“完美建筑”，又有“印度明珠”的美誉。\n" +
                "泰姬陵因爱情而生，这段爱情的生命也因泰姬陵的光彩被续写，光阴轮回，生生不息。尽管有人说，沙贾汗只是一个好大喜功的暴君，根本不是多情种子；尽管有人说，泰姬陵美轮美奂的脚下，不知堆砌着多少人的鲜血乃至生命。但是我们似乎更愿意相信这世上真的有情深意重的男子，有穿越时空的思恋，有生死相随的爱情。泰姬陵依然超越着简单的建筑学意义，默默地美丽着，不为别的，只为世人心中那一点对爱情的美好向往。 [2] \n" +
                "2018年1月23日，印度考古局局长维克拉姆表示，印度政府拟于今年2月份颁布限客令，将阿格拉市著名景点泰姬陵的游客日接待量控制在4万人以内。");
    }
}
