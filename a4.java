package com.example.farmerhelper;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Farmer Helper App - 23ITR035");
        setContentView(R.layout.activity_main);

        Spinner crop = findViewById(R.id.spinnerCrop);
        Spinner soil = findViewById(R.id.spinnerSoil);
        Spinner season = findViewById(R.id.spinnerSeason);
        TextView tvResult = findViewById(R.id.tvResult);

        crop.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Rice","Wheat","Cotton"}));

        soil.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Black Soil","Red Soil","Sandy Soil"}));

        season.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Summer","Winter","Rainy"}));

        findViewById(R.id.btnShow).setOnClickListener(v -> {
            tvResult.setText("Crop: "+crop.getSelectedItem()
                    +"\nSoil: "+soil.getSelectedItem()
                    +"\nSeason: "+season.getSelectedItem()
                    +"\nTip: Use organic fertilizers.");
        });

        tvResult.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Contact Expert - 23ITR035")
                        .setItems(new String[]{"Call","SMS","Email"},
                                (d,w)->{
                                    Intent intent;
                                    if(w==0)
                                        intent=new Intent(Intent.ACTION_DIAL,
                                                Uri.parse("tel:9999999999"));
                                    else if(w==1)
                                        intent=new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("sms:9999999999"));
                                    else
                                        intent=new Intent(Intent.ACTION_SENDTO,
                                                Uri.parse("mailto:agri@gmail.com"));
                                    startActivity(intent);
                                }).show());
    }
}
