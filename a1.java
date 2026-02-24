package com.example.patientinfoapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etPhone;
    RadioGroup rgGender;
    Spinner spinnerIllness;
    TextView tvDate;
    String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Patient Information App - 23ITR035");
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPhone = findViewById(R.id.etPhone);
        rgGender = findViewById(R.id.rgGender);
        spinnerIllness = findViewById(R.id.spinnerIllness);
        tvDate = findViewById(R.id.tvDate);

        String[] illness = {"Fever", "Cold", "Diabetes", "BP"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, illness);
        spinnerIllness.setAdapter(adapter);

        Button btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            DatePickerDialog dp = new DatePickerDialog(this,
                    (view, year, month, day) -> {
                        selectedDate = day + "/" + (month + 1) + "/" + year;
                        tvDate.setText(selectedDate);
                    },
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dp.show();
        });

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {

            int id = rgGender.getCheckedRadioButtonId();
            RadioButton rb = findViewById(id);

            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            intent.putExtra("name", etName.getText().toString());
            intent.putExtra("age", etAge.getText().toString());
            intent.putExtra("phone", etPhone.getText().toString());
            intent.putExtra("gender", rb.getText().toString());
            intent.putExtra("illness", spinnerIllness.getSelectedItem().toString());
            intent.putExtra("date", selectedDate);

            startActivity(intent);
        });
    }
}
