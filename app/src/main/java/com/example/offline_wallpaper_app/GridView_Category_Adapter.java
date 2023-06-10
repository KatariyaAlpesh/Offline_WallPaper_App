package com.example.offline_wallpaper_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridView_Category_Adapter extends BaseAdapter
{
    MainActivity mainActivity;
    int[] wallpaperCategoryArray;
    String[] wallpaperCategoryNameArray;
    public GridView_Category_Adapter(MainActivity mainActivity ,
                                     int[] wallpaperCategoryArray ,
                                     String[] wallpaperCategoryNameArray)
    {
        this.mainActivity = mainActivity;
        this.wallpaperCategoryArray = wallpaperCategoryArray;
        this.wallpaperCategoryNameArray = wallpaperCategoryNameArray;

    }

    @Override
    public int getCount()
    {
        return wallpaperCategoryNameArray.length;
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
        view = LayoutInflater.from(mainActivity).inflate(R.layout.main_category_item , viewGroup , false);

        ImageView imageView = view.findViewById(R.id.CategoryWallpapers);   ///  get Image From Main Category Item.
        imageView.setImageResource(Config.WallpaperCategoryArray[i]);    /////   Get Image Possition From WallpaperCategoryArray From Config.

        TextView textView = view.findViewById(R.id.CategoryWallpapersName);
        textView.setText(Config.WallpaperCategoryNameArray[i]);


        return view;
    }
}
