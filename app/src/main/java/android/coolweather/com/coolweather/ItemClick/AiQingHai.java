package android.coolweather.com.coolweather.ItemClick;

import android.coolweather.com.coolweather.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AiQingHai extends AppCompatActivity {


    TextView tet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_qing_hai);
        tet1=(TextView)findViewById(R.id.tet1);
        tet1.setText("   爱琴海是希腊半岛东部的一个蓝色系海洋，南抵克里特岛，属地中海的一部分。爱琴海是黑海沿岸国家通往地中海以及大西洋、印度洋的必经水域，在航运和战略上具有重要地位。\n" +
                "海域南北长610公里，东西宽300公里，海岸线非常曲折，港湾众多，岛屿星罗棋布，所以爱琴海又有“多岛海”之称。海中最大的一个岛名叫克里特岛，面积约8300km²，东西狭长，是爱琴海南部的屏障。\n" +
                "爱琴海名称起源有各种解释，其中一个可能的词源是Αιγ- ，意思是波浪，因此Αιγαίο Πέλαγος意思是波浪起伏的海。它不仅是西方欧洲文明的摇篮，对世界各国旅客来说，更是浪漫情调旅程的象征。");
    }
}
