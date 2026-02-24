package com.example.dailyexpenseapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> expenses = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Daily Expense App - 23ITR035");
        setContentView(R.layout.activity_main);

        EditText etAmount = findViewById(R.id.etAmount);
        EditText etDesc = findViewById(R.id.etDesc);
        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        RadioGroup rgPayment = findViewById(R.id.rgPayment);
        ListView listView = findViewById(R.id.listView);

        String[] categories = {"Office Supplies","Travel / Taxi",
                "Client Meeting","Printing / Xerox","Internet / Phone Bill"};

        spinnerCategory.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories));

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, expenses);
        listView.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            int id = rgPayment.getCheckedRadioButtonId();
            RadioButton rb = findViewById(id);

            String record = "₹" + etAmount.getText().toString()
                    + " | " + etDesc.getText().toString()
                    + " | " + spinnerCategory.getSelectedItem().toString()
                    + " | " + rb.getText().toString();

            expenses.add(record);
            adapter.notifyDataSetChanged();
        });

        findViewById(R.id.btnCall).setOnClickListener(v ->
                confirmAction("call","9876543210"));

        findViewById(R.id.btnSMS).setOnClickListener(v ->
                confirmAction("sms","9876543210"));

        findViewById(R.id.btnEmail).setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:manager@gmail.com"));
            startActivity(email);
        });
    }

    private void confirmAction(String action,String phone){
        new AlertDialog.Builder(this)
                .setTitle("Confirmation - 23ITR035")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes",(d,w)->{
                    Intent intent;
                    if(action.equals("call"))
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                    else
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+phone));
                    startActivity(intent);
                })
                .setNegativeButton("No",null)
                .show();
    }
}
