package com.example.offline_wallpaper_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.GridViewMain);


//////////////////=====--->>>>>>     Set Adapter Hear  <<<<----======================//////////////////////////////////////////

        GridView_Category_Adapter gridView_category_adapter = new GridView_Category_Adapter(this ,
                                                                                            Config.WallpaperCategoryArray ,
                                                                                            Config.WallpaperCategoryNameArray);
        gridView.setAdapter(gridView_category_adapter);


//////////////////==-->>>> Take Permissions From USER    <<<--===//////////////////////////////////////////////////////////

        ActivityCompat.requestPermissions(MainActivity.this
                                                , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                                , Manifest.permission.WRITE_EXTERNAL_STORAGE} , 1);


/////////////////====--->>>   Click On GridView Item ClickListener and get That Categorry Photos    <<<<<--====////////////////
        ///////===--->>>>     Send WallpaperCategoryPosission on MainActivity_2       <<<<<---===///////////

        gridView.setOnItemClickListener((adapterView, view, i, l) -> {

            Animation animation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.first);
            view.setAnimation(animation);

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    Intent Inext;
                    Inext = new Intent(MainActivity.this , MainActivity2.class);
                    Inext.putExtra("WallpaperCategoryPosission" , i);
                    startActivity(Inext);

                }
            } , 200);

        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 1 :
            {
                if ((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) &&
                        (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED))
                {
                    File file = new File(Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                            + "/demo1");

                    if (!file.exists())
                    {
                        if (!file.mkdir())
                        {
                            System.out.println("folder created");
                        }
                        else
                        {
                            System.out.println("folder not created");
                        }
                    }
                }
                else
                {
                    System.out.println("permission denied");
                    Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}