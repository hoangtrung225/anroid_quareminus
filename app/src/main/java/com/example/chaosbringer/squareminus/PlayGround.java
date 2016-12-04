package com.example.chaosbringer.squareminus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class PlayGround extends AppCompatActivity {

    private Bundle extras;
    private int ImageDiv;
    private long ImageId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);

        extras = getIntent().getExtras();
        ImageDiv = extras.getInt("titlesize");
        ImageId = extras.getLong("imageToDisplay");

        Toast.makeText(getApplicationContext(), "Image divice unit: " + ImageDiv +" Image Id: "+ImageId, Toast.LENGTH_SHORT).show();
        try{
            Bitmap ImagePlay = BitmapFactory.decodeResource(this.getResources(), (int)ImageId);
            int cutHeight = ImagePlay.getHeight()/ImageDiv;
            int cutWidth = ImagePlay.getWidth()/ImageDiv;

            TableLayout rootTable = (TableLayout)this.findViewById(R.id.imageplay);
            int counter = 0;
            for(int i = 0; i < ImageDiv; i++) {

                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                rootTable.addView(row);

                for (int j = 0; j < ImageDiv; j++) {
                    Bitmap bitmap = Bitmap.createBitmap(ImagePlay, j * cutWidth, i * cutHeight, cutWidth, cutHeight);
                    ImageView image = new ImageView(this);
                    image.setImageBitmap(bitmap);
                    row.addView(image);
                }
            }
        }
        catch(Exception e){}

    }


}
