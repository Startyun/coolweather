package android.coolweather.com.coolweather.PoiList;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class poiadapter extends BaseAdapter {
    private List<poibean> mList ;
    private Context mContext ;
    public poiadapter(List<poibean> list , Context context)
    {
        mList = list ;
        mContext = context ;
    }
    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //如果view未被实例化过，缓存池中没有对应的缓存
        if (convertView == null) {
            viewHolder = new ViewHolder();
            // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null


            //通过setTag将convertView与viewHolder关联
            convertView.setTag(viewHolder);
        }else{//如果缓存池中有对应的view缓存，则直接通过getTag取出viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }



        return convertView;
    }
    // ViewHolder用于缓存控件，三个属性分别对应item布局文件的三个控件
    class ViewHolder{
        public TextView tet1;
        public TextView tet2;
        public TextView tet3;
        public TextView tet4;
        public TextView tet5;


    }


}
