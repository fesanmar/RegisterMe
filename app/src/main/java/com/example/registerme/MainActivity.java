package com.example.registerme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnButton;
    Button btnClear;
    EditText txtName;
    EditText txtSurname;
    Spinner spMaritalStatus;

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
        btnButton = findViewById(R.id.button);
        btnButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        btnClear = findViewById(R.id.button2);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, String.valueOf(view.getContext() == MainActivity.this), Toast.LENGTH_SHORT).show();
                assert view.getContext() == MainActivity.this;
                clearTextFields(view);
            }
        });
    }

    private void fillMaritalStatusSpinner() {
        spMaritalStatus = findViewById(R.id.spMaritialStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marital_statuses, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaritalStatus.setAdapter(adapter);
    }

    public void click(View view) {
        Toast.makeText(MainActivity.this, "Me pulsaste...", Toast.LENGTH_SHORT).show();
    }

    public void clearTextFields(View view) {
        txtName.setText("");
        txtSurname.setText("");
    }
}