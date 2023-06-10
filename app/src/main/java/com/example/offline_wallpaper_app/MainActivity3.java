package com.example.offline_wallpaper_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity
{
    ImageView wallpaper , expand , share ;
    LinearLayout setAsWallpaper;

    int wallpaperPosission;
    int wallpaperCategoryPosissino;

    String folderName ,wallpaperName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        wallpaper = findViewById(R.id.Main3_Wallpaper);
        expand = findViewById(R.id.Main3_Expand);
        share = findViewById(R.id.Main3_Share);
        setAsWallpaper = findViewById(R.id.Main3_SetAsWallpaper);


/////////////////////==-->>    This Data is gate From MainActivity_2 Send By INTENT    <<<<---===//////////////////////////

        wallpaperPosission = getIntent().getIntExtra("WallpaperPosission" , 0);
        wallpaperCategoryPosissino = getIntent().getIntExtra("wallpaperCategoryPosissino" , 0);
        wallpaperName = getIntent().getStringExtra("wallpaperNameArrayList");

        folderName = Config.WallpaperCategoryFolderInAssets[wallpaperCategoryPosissino];

        InputStream inputStream = null;
        try
        {
            inputStream = getAssets().open(folderName + "/" + wallpaperName);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Drawable drawable = Drawable.createFromStream(inputStream,null);


////////////////====--->>>>>    Set Image As Wallpaper     <<<<<<<<----====//////////////////////////////

        wallpaper.setImageDrawable(drawable);

        Bitmap bitmap = drawableToBitmap(drawable);


///////////////////===----->>>>>>    Expand The Image Like Zoom      <<<<<---======//////////////////////

        expand.setOnClickListener(v -> {

            wallpaper.setScaleType(ImageView.ScaleType.FIT_XY);
        });


//////////////////=======--->>>>>    Share Image Other Person From This App     <<<<<<---=====///////////////

        share.setOnClickListener(v -> {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
            String currentTimeStamp = dateFormat.format(new Date());

            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +
                    "/demo1/" + currentTimeStamp + ".jpeg");

            try {

                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            startActivity(Intent.createChooser(share, "Share Image"));

        });


        setAsWallpaper.setOnClickListener(v -> {

            WallpaperManager myWallpaperManager = WallpaperManager.getInstance(this);

            try {

                myWallpaperManager.setBitmap(bitmap);
                Toast.makeText(MainActivity3.this,"Wallpaper is Set",Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(e.getMessage());
            }
        });

    }

    private Bitmap drawableToBitmap(Drawable drawable)
    {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {

            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

            if(bitmapDrawable.getBitmap() != null) {

                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {

            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel

        } else {

            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}