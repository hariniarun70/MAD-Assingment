package com.example.employeecontact;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> employees = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Employee Contact Manager - 23ITR035");
        setContentView(R.layout.activity_main);

        EditText etName = findViewById(R.id.etName);
        EditText etId = findViewById(R.id.etId);
        EditText etPhone = findViewById(R.id.etPhone);
        Spinner spinnerDept = findViewById(R.id.spinnerDept);
        ListView listView = findViewById(R.id.listView);

        String[] dept = {"IT","HR","Sales","Marketing"};
        spinnerDept.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, dept));

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, employees);
        listView.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {

            String record = etName.getText().toString()
                    + " | " + etId.getText().toString()
                    + " | " + spinnerDept.getSelectedItem().toString();

            employees.add(record);
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

            new AlertDialog.Builder(this)
                    .setTitle("Contact Employee - 23ITR035")
                    .setItems(new String[]{"Call","SMS","Email"},
                            (dialog, which) -> {
                                Intent intent;
                                if(which==0)
                                    intent=new Intent(Intent.ACTION_DIAL,
                                            Uri.parse("tel:"+etPhone.getText().toString()));
                                else if(which==1)
                                    intent=new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("sms:"+etPhone.getText().toString()));
                                else
                                    intent=new Intent(Intent.ACTION_SENDTO,
                                            Uri.parse("mailto:employee@gmail.com"));
                                startActivity(intent);
                            }).show();
        });
    }
}
