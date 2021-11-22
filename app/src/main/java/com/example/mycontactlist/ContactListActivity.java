package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactListActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position",position);
            startActivity(intent);
        }
    };

    private class SortByName implements Comparator<Contact>{

        private boolean isAscending;

        public SortByName(boolean isAscending){
            this.isAscending = isAscending;
        }

        @Override
        public int compare(Contact contact, Contact t1) {
            if(isAscending)
                return contact.getName().compareTo(t1.getName());
            else
                return t1.getName().compareTo(contact.getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        initListActivity();
        initMapActivity();
        initSettingsActivity();

        ContactDataSource ds = new ContactDataSource(this);
        ArrayList<Contact> contacts;
        try {
            ds.open();
            contacts = ds.getContacts();
            ds.close();

            // This code check if you are trying to order by name and if you are, it will sort by name
            SharedPreferences sharedPref = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE);
            String sortBy = sharedPref.getString("sortfield", "name");
            String sortOrder = sharedPref.getString("sortorder", "ascending");
            if (sortBy.equals("name")){
                if(sortOrder.equals("ascending"))
                contacts.sort(new SortByName(true));
            } else {
                contacts.sort(new SortByName(false));
            }


            RecyclerView contactList = findViewById(R.id.rvContacts);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            contactList.setLayoutManager(layoutManager);
            ContactAdapter contactAdapter = new ContactAdapter(contacts);
            contactAdapter.setOnClickListener(onClickListener);
            contactList.setAdapter(contactAdapter);
        } catch (Exception e){
            Toast.makeText(this, "Error retrieving contacts",Toast.LENGTH_LONG).show();
        }
    }


    private void initListActivity() {
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSettingsActivity() {
        Button settingButton = findViewById(R.id.settingsButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initMapActivity() {
        Button mapButton = findViewById(R.id.buttonMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, MapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}