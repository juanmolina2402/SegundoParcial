package com.example.segundoparcial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.segundoparcial.adapterMC.ContactsMCAdapter;
import com.example.segundoparcial.daoMC.ContactsMCDao;
import com.example.segundoparcial.daoMC.ContactsMCDaoImpRoom;
import com.example.segundoparcial.modelMC.ContactsMC;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainMCActivity extends AppCompatActivity {
    private FloatingActionButton fabAddContact;
    private RecyclerView rvContacts;
    private List<ContactsMC> contacts;
    private ContactsMCDao contactsMCDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mcactivity);

        contactsMCDao = new ContactsMCDaoImpRoom(getApplicationContext());
        Intent intent=new Intent(this, AddContactMCActivity.class);

        this.fabAddContact = findViewById(R.id.fabAddContact);
        this.rvContacts = findViewById(R.id.rvContacts);

        this.fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        this.loadData();
        ContactsMCAdapter adapter = new ContactsMCAdapter(this.contacts, getApplicationContext(),contactsMCDao);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setAdapter(adapter);
    }

    private void loadData(){
        contacts = new ArrayList<ContactsMC>();
        contacts = contactsMCDao.getAll();
    }
}