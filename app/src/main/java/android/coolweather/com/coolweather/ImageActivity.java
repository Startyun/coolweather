package android.coolweather.com.coolweather;

import android.content.Intent;
import android.coolweather.com.coolweather.RecyclerView.Bean;
import android.coolweather.com.coolweather.RecyclerView.BeanAdapter;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;

    private  Bean[] beans={new Bean("天王盖地虎",R.drawable.ic_launcher_20),
                           new Bean("小鸡炖蘑菇",R.drawable.ic_launcher_21),
                           new Bean("宝塔镇河妖",R.drawable.ic_launcher_22),
                           new Bean("蘑菇放辣椒",R.drawable.ic_launcher_24),
                           new Bean("河妖用大招",R.drawable.ic_launcher_25),
                           new Bean("二楼弯下腰",R.drawable.ic_launcher_26)};

    private List<Bean>beanList=new ArrayList<>();
    private BeanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout1);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        mDrawerLayout.openDrawer(GravityCompat.START);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_call:
                       Toast.makeText(ImageActivity.this, "点电话干嘛", Toast.LENGTH_SHORT).show();
                       break;
                    case R.id.nav_friends:
                        Toast.makeText(ImageActivity.this, "点朋友干嘛", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        //Toast.makeText(ImageActivity.this, "点位置干嘛", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(ImageActivity.this,MapActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(ImageActivity.this, "点邮箱干嘛", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Intent intent1 =new Intent(ImageActivity.this,SearchActivity.class);
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });

        View drawview = navView.inflateHeaderView(R.layout.nav_header);
        CircleImageView icon_image = (CircleImageView) drawview.findViewById(R.id.icon_image);
        icon_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageActivity.this,"我这么可爱，你点我干嘛！！！",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(ImageActivity.this,"嘿嘿嘿",Toast.LENGTH_SHORT).show();
                Snackbar.make(view,"点右边那个",Snackbar.LENGTH_SHORT)
                        .setAction("点点点", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(ImageActivity.this,"嘿嘿嘿",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });



        initBean();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new BeanAdapter(beanList);
        recyclerView.setAdapter(adapter);
        swipeRefresh =(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void initBean() {
        beanList.clear();
        for(int i=0;i<50;i++) {
            Random random =new Random();
            int index= random.nextInt(beans.length);
            beanList.add(beans[index]);
        }
    }


    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initBean();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }




    public boolean onCreateOptionsMenu (Menu menu) {
          getMenuInflater().inflate(R.menu.toolbar,menu);
          return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                Toast.makeText(this,"没用的，删不掉的",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case android.R.id.home:
               finish();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
