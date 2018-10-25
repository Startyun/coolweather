package android.coolweather.com.coolweather.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.coolweather.com.coolweather.ImageActivity;
import android.coolweather.com.coolweather.ItemClick.AiQingHai;
import android.coolweather.com.coolweather.ItemClick.BuLaGe;
import android.coolweather.com.coolweather.ItemClick.DaBaoJiao;
import android.coolweather.com.coolweather.ItemClick.DaXiaGu;
import android.coolweather.com.coolweather.ItemClick.PuLuoWangSi;
import android.coolweather.com.coolweather.ItemClick.TaiJiLing;
import android.coolweather.com.coolweather.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class BeanAdapter extends RecyclerView.Adapter<BeanAdapter.ViewHolder> {

    private Context mContext;
    private List<Bean> mBeanList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView beanImage;
        TextView beanName;

        public ViewHolder(View view) {
            super(view);
            cardView=(CardView)view;
            beanImage=(ImageView)view.findViewById(R.id.bean_image);
            beanName=(TextView)view.findViewById(R.id.bean_name);
        }
    }

    public BeanAdapter(List<Bean> BeanList) {
        mBeanList=BeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null) {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.bean_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.beanName.setText(mBeanList.get(position).getName());
        Glide.with(mContext).load(mBeanList.get(position).getImageId()).into(holder.beanImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(mContext, "能不能轻点点我！！！"+position, Toast.LENGTH_SHORT).show();
                if(mBeanList.get(position).getName() == "美国大峡谷") {
                    Intent intent2=new Intent(mContext, DaXiaGu.class);
                    mContext.startActivity(intent2);
                }
                if(mBeanList.get(position).getName() == "澳大利亚大堡礁") {
                    Intent intent3=new Intent(mContext, DaBaoJiao.class);
                    mContext.startActivity(intent3);
                }
                if(mBeanList.get(position).getName() == "爱琴海") {
                          Intent intent1=new Intent(mContext, AiQingHai.class);
                          mContext.startActivity(intent1);
                }
                if(mBeanList.get(position).getName() == "普罗旺斯") {
                    Intent intent4=new Intent(mContext, PuLuoWangSi.class);
                    mContext.startActivity(intent4);
                }
                if(mBeanList.get(position).getName() == "泰姬陵") {
                    Intent intent5=new Intent(mContext, TaiJiLing.class);
                    mContext.startActivity(intent5);
                }
                if(mBeanList.get(position).getName() == "布拉格城堡") {
                    Intent intent6=new Intent(mContext, BuLaGe.class);
                    mContext.startActivity(intent6);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

}
