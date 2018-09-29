package android.coolweather.com.coolweather;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout1);
        //NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        mDrawerLayout.openDrawer(GravityCompat.START);
//        navView.setCheckedItem(R.id.nav_call);
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                mDrawerLayout.closeDrawers();
//                return true;
//            }
//        });

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
