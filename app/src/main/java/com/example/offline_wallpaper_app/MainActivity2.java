package com.example.offline_wallpaper_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity
{

    GridView gridView;

    ArrayList<String> wallpaperNameArrayList;

    int wallpaperCategoryPosissino;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gridView = findViewById(R.id.Main_2_GridView);


/////////////////////==-->>    This Data is gate From MainActivity Send By INTENT    <<<<---===//////////////////////////

        wallpaperCategoryPosissino = getIntent().getIntExtra("WallpaperCategoryPosission" , 0);

        /////////////

        String[] imageName = new String[0];

        try
        {
            imageName = getAssets().list(Config.WallpaperCategoryFolderInAssets[wallpaperCategoryPosissino]);
            wallpaperNameArrayList = new ArrayList<>(Arrays.asList(imageName));


//////////////////=====--->>>>>>     Set Adapter Hear  <<<<----======================//////////////////////////////////////////

            GridView_Wallpaper_Adapter gridView_wallpaper_adapter = new GridView_Wallpaper_Adapter(this
                    ,wallpaperNameArrayList,
                    Config.WallpaperCategoryFolderInAssets[wallpaperCategoryPosissino]);

            gridView.setAdapter(gridView_wallpaper_adapter);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


/////////////////////==--->>>   Click On any GridView Item and Get Posission , Name and Array and send it in MainActivity_3   <<--==//////

        gridView.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent Inext ;
            Inext = new Intent(MainActivity2.this , MainActivity3.class);

            Inext.putExtra("WallpaperPosission" , i);
            Inext.putExtra("wallpaperNameArrayList" , wallpaperNameArrayList.get(i));
            Inext.putExtra("wallpaperCategoryPosissino",wallpaperCategoryPosissino);

            startActivity(Inext);

        });

    }
}