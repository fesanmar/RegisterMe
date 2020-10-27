package com.example.registerme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnButton;
    Button btnClear;
    EditText txtName;
    EditText txtSurname;
    EditText txtAge;
    RadioGroup rgGender;
    Spinner spMaritalStatus;
    SwitchCompat swChildren;
    TextView lblMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews() {
        fillMaritalStatusSpinner();
        txtName = findViewById(R.id.tfName);
        txtSurname = findViewById(R.id.tfSurname);
        txtAge = findViewById(R.id.tfAge);
        rgGender = findViewById(R.id.rgGender);
        swChildren = findViewById(R.id.swChildren);
        btnButton = findViewById(R.id.btnSave);
        btnButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        lblMonitor = findViewById(R.id.lblMonitor);
    }

    private void fillMaritalStatusSpinner() {
        spMaritalStatus = findViewById(R.id.spMaritialStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marital_statuses, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaritalStatus.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnClear))
            clearTextFields();
    }

    public void clearTextFields() {
        txtName.setText("");
        txtSurname.setText("");
        txtAge.setText("");
        rgGender.clearCheck();
        spMaritalStatus.setSelection(0);
        swChildren.setChecked(false);
        lblMonitor.setText("");
    }

    public void click(View view) {
        Toast.makeText(MainActivity.this, "Me pulsaste...", Toast.LENGTH_SHORT).show();
    }
}