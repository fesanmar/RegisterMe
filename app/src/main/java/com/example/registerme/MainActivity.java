package com.example.registerme;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    HashMap<String, View> elements = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        fillElementsMap();
    }

    private void setUpViews() {
        fillMaritalStatusSpinner();
        txtName = findViewById(R.id.tfName);
        txtSurname = findViewById(R.id.tfSurname);
        txtAge = findViewById(R.id.tfAge);
        rgGender = findViewById(R.id.rgGender);
        swChildren = findViewById(R.id.swChildren);
        btnButton = findViewById(R.id.btnSave);
        btnButton.setOnClickListener(this);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        lblMonitor = findViewById(R.id.lblMonitor);
    }

    private void fillElementsMap() {
        elements.put(getString(R.string.text_view_surname), txtSurname);
        elements.put(getString(R.string.text_view_name), txtName);
        elements.put(getString(R.string.text_view_age), txtAge);
        elements.put(getString(R.string.text_view_gender), rgGender);
        elements.put(getString(R.string.text_view_marital_status), spMaritalStatus);
        elements.put(getString(R.string.any_children_switch), swChildren);
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
        else
            displayAccurateMessage();
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

    private void displayAccurateMessage() {
        Collection<String> emptyFields = getMissingFields();
        if (emptyFields.isEmpty())
            showOutputAndClearForm();
        else
            displayMissingFieldsError(emptyFields);
    }

    private void showOutputAndClearForm() {
        displaySuccessMessage();
        clearTextFields();
    }

    private Collection<String> getMissingFields() {
        List<String> missingFields = new ArrayList<>();
        for (Map.Entry<String, View> pair : elements.entrySet()) {
            if (isEmpty(pair.getValue()))
                missingFields.add(pair.getKey());
        }
        return missingFields;
    }

    private boolean isEmpty(View view) {
        if (view instanceof TextView)
            return ((TextView) view).getText().toString().trim().isEmpty();
        else if (view instanceof RadioGroup)
            return ((RadioGroup) view).getCheckedRadioButtonId() == -1;
        else
            return false;
    }

    private void displaySuccessMessage() {
        String surname = txtSurname.getText().toString();
        String name = txtName.getText().toString();
        String adult = (Integer.parseInt(txtAge.getText().toString())) >= 18
                ? getString(R.string.adult)
                : getString(R.string.under_age);
        int id = rgGender.getCheckedRadioButtonId();
        RadioButton g = findViewById(id);
        String gender = g.getText().toString().toLowerCase();
        String maritalStatus = spMaritalStatus.getSelectedItem().toString().toLowerCase();
        String hasChildren = swChildren.isChecked()
                ? getString(R.string.with_children)
                : getString(R.string.no_children);

        String message = getString(R.string.success_message,
                surname, name, adult, gender, maritalStatus, hasChildren);

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void displayMissingFieldsError(Collection<String> missingFields) {
        StringBuilder sb = new StringBuilder(getString(R.string.missing_fields_message));
        for (String field : missingFields)
            sb.append(", ").append(field);
        lblMonitor.setText(sb.toString());
    }

}
