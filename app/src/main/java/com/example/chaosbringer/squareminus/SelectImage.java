package com.example.chaosbringer.squareminus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class SelectImage extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_picture);
        extras = getIntent().getExtras();

        GridView gridv = (GridView) this.findViewById(R.id.pictures_display);
        gridv.setAdapter(new ImageAdapter(this));
        gridv.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        Intent i = new Intent(this, PlayGround.class);


        i.putExtra("imageToDisplay", id);
        i.putExtra("titlesize", extras.getInt("titlesize"));


        startActivity(i);
    }

}
