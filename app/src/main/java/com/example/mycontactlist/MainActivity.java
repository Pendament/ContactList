package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {

    private Contact currentContact;         // connects with Contact.java


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentContact = new Contact();     // connects with Contact.java
        initTextChangeEvents();
        initToggleButton();
        initListActivity();
        initSaveButton();
        initMapActivity();
        initSettingsActivity();
        initChangeDateButton();
        setForEditing(false);
    }

    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",-1);
        if(position != -1){
            ContactDataSource ds = new ContactDataSource(this);
            try{
                ds.open();
                currentContact = ds.getContact(position+1);
                ds.close();
                EditText nameEdit = findViewById(R.id.editContact);
                nameEdit.setText(currentContact.getName());
                EditText addressEdit = findViewById(R.id.editAddress);
                addressEdit.setText(currentContact.getAddress());
                EditText cityEdit = findViewById(R.id.editCity);
                cityEdit.setText(currentContact.getCity());
            } catch (Exception e){
                Toast.makeText(this,"Error accessing contact", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void initListActivity() {       // code for list button which will take you to the contact list page
        Button listButton = findViewById(R.id.buttonList); //check out renaming other ones in map and settings. that seems to be the issue.
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initMapActivity() {        // code for map button which will take you to the map page
        Button mapButton = findViewById(R.id.buttonMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initSettingsActivity() {       // code for settings button which will take you to the settings page
        Button settingButton = findViewById(R.id.settingsButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }



    private void initToggleButton() {
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setForEditing(toggleButton.isChecked());
            }
        });
    }

    private void initChangeDateButton(){
        Button changeDateButton = findViewById(R.id.buttonChange);
        changeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm,"DatePick");
            }
        });
    }

    private void initTextChangeEvents(){    // this method will initialize all data needed for contact
        EditText nameEdit = findViewById(R.id.editContact);
        nameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setName(nameEdit.getText().toString());
                //currentContact.setContactID(-1);
            }
        });

        EditText addressEdit = findViewById(R.id.editAddress);
        addressEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setAddress(addressEdit.getText().toString());
            }
        });

        EditText cityEdit = findViewById(R.id.editCity);
        cityEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setCity(cityEdit.getText().toString());
            }
        });

        EditText stateEdit = findViewById(R.id.editState);
        stateEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setState(stateEdit.getText().toString());
            }
        });

        EditText zipCodeEdit = findViewById(R.id.editZipcode);
        zipCodeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setZipCode(zipCodeEdit.getText().toString());
            }
        });

        EditText homeEdit = findViewById(R.id.editHome);
        zipCodeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setHomeNumber(homeEdit.getText().toString());
            }
        });

        EditText cellEdit = findViewById(R.id.editCell);
        cellEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setCellNumber(cellEdit.getText().toString());
            }
        });

        EditText emailEdit = findViewById(R.id.editEmail);
        emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setEmail(emailEdit.getText().toString());
            }
        });
        homeEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        cellEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }
    private void initSaveButton() {
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                ContactDataSource ds  = new ContactDataSource(MainActivity.this);
                try {
                    ds.open();
                    if (currentContact.getContactID() == -1) {
                        wasSuccessful = ds.insertContact(currentContact);
                        if (wasSuccessful) {
                            int newID = ds.getLastContactID();
                            currentContact.setContactID(newID);

                        }
                    } else {
                            wasSuccessful = ds.updateContact(currentContact);
                        }
                        ds.close();

                } catch (Exception e) { ///check photos on phone for code
                    wasSuccessful = false;

                    }
                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButton);
                    editToggle.toggle();
                    setForEditing(false);
                }
                }

        });
    }



    private void setForEditing(boolean enabled) {
        EditText editContact = findViewById(R.id.editContact);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);
        EditText editHome = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEmail);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonChange = findViewById(R.id.buttonChange);

        editContact.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipcode.setEnabled(enabled);
        editHome.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonSave.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
    }

    @Override
    public void didFinishDatePickerDialog(Calendar selectedDate) {
        TextView textBirthday = findViewById(R.id.textBirthday);
        textBirthday.setText(DateFormat.format("MM/dd/yyyy", selectedDate));
        currentContact.setBirthday(selectedDate); // connects with Contact.java to update the birthdate to whatever day is selected for the contact
    }
}