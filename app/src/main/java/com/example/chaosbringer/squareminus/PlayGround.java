package com.example.chaosbringer.squareminus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class PlayGround extends AppCompatActivity implements View.OnClickListener{

    private Bundle extras;
    private int ImageDiv;
    private long ImageId;
    Bitmap nullBitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        extras = getIntent().getExtras();
        ImageDiv = extras.getInt("titlesize");
        ImageId = extras.getLong("imageToDisplay");

        try{
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            Bitmap originalImg = BitmapFactory.decodeResource(this.getResources(), (int)ImageId, options);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

            int tableHeight = displaymetrics.heightPixels;
            int tableWidth = displaymetrics.widthPixels;

            float ratioHeight = (float)tableHeight/originalImg.getHeight();
            float ratioWidth = (float)tableWidth/originalImg.getWidth();
            float scaledHeight, scaledWidth;
            if(ratioHeight < ratioWidth){
                scaledHeight = tableHeight;
                scaledWidth = ratioHeight * originalImg.getWidth();
            }
            else{
                scaledWidth = tableWidth;
                scaledHeight = ratioWidth * originalImg.getHeight();
            }

            final Bitmap ImagePlay = Bitmap.createScaledBitmap(originalImg, (int)scaledWidth, (int)scaledHeight, true);

            int cutHeight = ImagePlay.getHeight()/ImageDiv;
            int cutWidth = ImagePlay.getWidth()/ImageDiv;

            TableLayout rootTable = (TableLayout)this.findViewById(R.id.imageplay);
            int counter = 0;
            for(int i = 0; i < ImageDiv; i++) {

                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                row.setLayoutParams(lp);

                for (int j = 0; j < ImageDiv; j++) {
                    ImageView image = new ImageView(this);

                    if( i != ImageDiv - 1|| j != ImageDiv -1) {
                        Bitmap bitmap = Bitmap.createBitmap(ImagePlay, j * cutWidth, i * cutHeight, cutWidth, cutHeight);
                        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        image.setImageBitmap(bitmap);

                        imageTag object = new imageTag(i * ImageDiv + j + 1, j + 1, i + 1);
                        object.setImage(bitmap);
                        image.setTag(object);
                        image.setOnClickListener(this);

                    }
                    else{

                        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
                        nullBitmap = Bitmap.createBitmap(cutWidth, cutHeight, conf); // this creates a MUTABLE bitmap

                        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        image.setImageBitmap(nullBitmap);

                        imageTag object = new imageTag(0, j + 1, i + 1);
                        image.setTag(object);
                        image.setOnLongClickListener(new View.OnLongClickListener(){
                            @Override
                            public boolean onLongClick(View v) {
                                ImageView helperImage = (ImageView)findViewById(R.id.helperimage);
                                helperImage.setImageBitmap(ImagePlay);
                                return true;
                            }
                        });
                        image.setOnClickListener(this);

                    }
                    image.setId(i * ImageDiv + j+ 1);
                    row.addView(image);

                }
                rootTable.addView(row);

            }
            ImageView helperImage = (ImageView) findViewById(R.id.helperimage);
            helperImage.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    // Do something here
                    ((ImageView) v).setVisibility(View.GONE);
                }
            });
            helperImage.setImageBitmap(ImagePlay);
            helperImage.setVisibility(View.GONE);
        }
        catch(Exception e){}

//        add event listener onclick for helper image


    }

    private int[][] adjTile = {{-1, 0}, {0, -1}, {1, 0},{0, 1}};
    public void onClick(View v){
        imageTag object = (imageTag)v.getTag();

//        Toast.makeText(getApplicationContext(), "object id" + object.getImageIndex() + " object iamge" + object.getImage(), Toast.LENGTH_SHORT).show();

        //get adjacent tile
        int[] adjacentIds = new int[4];
        for(int i = 0; i < 4; i++){
            adjacentIds[i] = v.getId() + adjTile[i][1]* ImageDiv + adjTile[i][0];

            if(adjacentIds[i] > 0 && adjacentIds[i] <= ImageDiv*ImageDiv){

//                Toast.makeText(getApplicationContext(), "adjacent tile id: "+ adjacentIds[i], Toast.LENGTH_SHORT).show();

                ImageView tmpView = (ImageView)this.findViewById(adjacentIds[i]);
                imageTag tmpOject = (imageTag)tmpView.getTag();

                if(tmpOject.getImageIndex() == 0){
                    tmpOject.setImageIndex(object.getImageIndex());
                    tmpOject.setImage(object.getImage());
                    tmpView.setTag(tmpOject);
                    tmpView.setImageBitmap(tmpOject.getImage());
                    tmpView.setOnLongClickListener(null);
//                    tmpView.setOnClickListener(this);

                    object.setImageIndex(0);
                    object.setImage(null);

                    v.setTag(object);
                    ((ImageView) v).setImageBitmap(nullBitmap);
                    v.setOnLongClickListener(new View.OnLongClickListener(){
                        @Override
                        public boolean onLongClick(View vi) {
                            ImageView helperImage = (ImageView)findViewById(R.id.helperimage);
                            helperImage.setVisibility(View.VISIBLE);
                            return true;
                        }
                    });
                }
            }
        }
        for(int i = 1 ; i < ImageDiv* ImageDiv; i++){
            ImageView tmpview = (ImageView)this.findViewById(i);
            if(((imageTag)tmpview.getTag()).getImageIndex() != i)   return;
        }
        Toast.makeText(getApplicationContext(), "finished", Toast.LENGTH_SHORT).show();

    }

}
