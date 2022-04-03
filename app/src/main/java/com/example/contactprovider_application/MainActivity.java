package com.example.contactprovider_application;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void btnGetContacts(View view)
    {
        getPhoneContacts();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getPhoneContacts()
    {
        //Here we check the permission is allowed for read contacts
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},0);
        }

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursorContacts = getContentResolver().query(uri,null,null,null);

        //If you want to see the output then copy this CONTACT_PROVIDER_DEMO from Log.i and paste in logcate search option and after run the app.

        Log.i("CONTACT_PROVIDER_DEMO","TOTAL NO OF CONTACTS ::: " + cursorContacts.getCount());

        //Check value

        if(cursorContacts.getCount() > 0)
        {
            while(cursorContacts.moveToNext())
            {
                @SuppressLint("Range") String ContactName = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String ContactNumber = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.i("CONTACT_PROVIDER_DEMO", "Contact Name: " + ContactName + "Contact Number: " + ContactNumber);
            }
        }
    }
}