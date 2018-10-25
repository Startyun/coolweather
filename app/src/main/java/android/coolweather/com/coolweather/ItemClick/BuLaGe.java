package android.coolweather.com.coolweather.ItemClick;

import android.coolweather.com.coolweather.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BuLaGe extends AppCompatActivity {

    private  TextView tet2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_la_ge);
        tet2=(TextView)findViewById(R.id.tet2);
        tet2.setText("   布拉格城堡始建于9世纪，经过国内外建筑师和艺术家多次改建、装饰和完善，城堡集中了各个历史时期的艺术精华，是捷克较吸引人的游览胜地之一。城堡过去是皇帝、国王的宫殿，如今是捷克总统为外国元首来访举行欢迎仪式和接受各国大使递交国书的地方。站在城堡上眺望整个布拉格市，美景尽收眼底。城堡内有三个庭院、几条古老街巷、画廊、花园，以及捷克较大的哥特式教堂--圣维特大教堂（始建于1344年）。城堡中较大的厅为西班牙大厅和弗拉迪斯拉夫大厅，西班牙大厅是总统举行授勋仪式和国宴的场所，弗拉迪斯拉夫大厅是总统举行就职典礼等大型政治活动的地方。1992年，布拉格城堡被选列入世界文化遗产目录。");
    }
}
