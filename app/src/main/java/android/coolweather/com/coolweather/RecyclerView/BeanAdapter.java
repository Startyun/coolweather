package android.coolweather.com.coolweather.RecyclerView;

import android.content.Context;
import android.coolweather.com.coolweather.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bean bean=mBeanList.get(position);
        holder.beanName.setText(bean.getName());
        Glide.with(mContext).load(bean.getImageId()).into(holder.beanImage);
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

}
