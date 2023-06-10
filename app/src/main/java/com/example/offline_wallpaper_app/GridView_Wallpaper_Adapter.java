package com.example.offline_wallpaper_app;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GridView_Wallpaper_Adapter extends BaseAdapter
{
    MainActivity2 mainActivity2;
    ArrayList<String> wallpaperNameArrayList;
    String wallpaperCategoryFolderInAsset;

    public GridView_Wallpaper_Adapter(MainActivity2 mainActivity2
                                      , ArrayList<String> wallpaperNameArrayList
                                      , String wallpaperCategoryFolderInAsset)
    {
        this.mainActivity2 = mainActivity2;
        this.wallpaperNameArrayList = wallpaperNameArrayList;
        this.wallpaperCategoryFolderInAsset = wallpaperCategoryFolderInAsset;

    }

    @Override
    public int getCount()
    {
        return wallpaperNameArrayList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = LayoutInflater.from(mainActivity2).inflate(R.layout.main2_wallpaper_item , viewGroup , false);

        ImageView imageView = view.findViewById(R.id.Wallpapers);

        InputStream inputStream = null;

        try {

            inputStream = mainActivity2.getAssets().open(wallpaperCategoryFolderInAsset + "/" +
                    wallpaperNameArrayList.get(i));
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        Drawable drawable = Drawable.createFromStream(inputStream,null);

        imageView.setImageDrawable(drawable);

        return view;
    }
}
