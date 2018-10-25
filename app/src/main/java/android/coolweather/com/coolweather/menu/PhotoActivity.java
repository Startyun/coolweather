package android.coolweather.com.coolweather.menu;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.coolweather.com.coolweather.R;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.annotation.Target;

public class PhotoActivity extends AppCompatActivity {

          private ImageView imagepicture;
          public static final int CHOOSE_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        imagepicture=(ImageView)findViewById(R.id.imagepicture);
        if(ContextCompat.checkSelfPermission(PhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PhotoActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            openAlbum();
        }
    }



    private void openAlbum() {
        Intent intent =new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,String [] permission,int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else{
                    Toast.makeText(this,"同意权限啊老铁！",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        switch(requestCode) {

            case CHOOSE_PHOTO:
                if(resultCode == RESULT_OK) {
                    if(Build.VERSION.SDK_INT>=19) {
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKiKat(data);
                    }
                }
                break;
                default:
                    break;

        }
    }


    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath=null;
        Uri uri =data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)) {
            String docId =DocumentsContract.getDocumentId(uri);
            if("com.android.provider.media.documents".equals(uri.getAuthority())) {
                String id=docId.split(":")[1];
                String selection= MediaStore.Images.Media._ID +"=" +id;
                imagePath =getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath=getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
    }


    private void handleImageBeforeKiKat(Intent data) {
        Uri uri =data.getData();
        String  imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection) {
        String path=null;
        Cursor cursor =getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null) {
            if(cursor.moveToFirst()) {
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if(imagePath !=null) {
            Bitmap bitmap= BitmapFactory.decodeFile(imagePath);
            imagepicture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"查找图片失败",Toast.LENGTH_SHORT).show();
        }
    }



}
