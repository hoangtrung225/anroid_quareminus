package com.example.chaosbringer.squareminus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.R.attr.bitmap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button easy_select = (Button) this.findViewById(R.id.easyselect);
        easy_select.setOnClickListener(this);

        Button normal_select = (Button) this.findViewById(R.id.normalselect);
        normal_select.setOnClickListener(this);

        Button hard_select = (Button) this.findViewById(R.id.hardselect);
        hard_select.setOnClickListener(this);
    }

    public void onClick(View v){
        int titlesize = 0;

        if(v.getId() == R.id.easyselect) titlesize = 3;
        else if(v.getId() == R.id.normalselect) titlesize = 4;
        else if(v.getId() == R.id.hardselect) titlesize = 5;

        Intent i = new Intent(this, SelectImage.class);
        i.putExtra("titlesize", titlesize);
        startActivity(i);
    }
}
