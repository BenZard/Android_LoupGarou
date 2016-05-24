package com.cryocrystal.exampleproject.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cryocrystal.exampleproject.R;

public class MainActivity extends AppCompatActivity {

    private static final int CONTACT_REQUEST_CODE = 42; // the number you want but since you can choose. Obviously 42

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNextClicked(View v){
        // Explicit Intent ---> I want to launch the ExampleActivity
        Intent exampleActivityIntent = new Intent(this, ExampleActivity.class);
        startActivity(exampleActivityIntent);

        // close this activity after launching the ExampleActivity
        finish();
    }

    public void onGoogleClicked(View v){
        // Implicit Intent ---> I want something capable of opening http://www.google.com
        Intent googleIntent = new Intent();
        googleIntent.setAction(Intent.ACTION_VIEW);
        Uri googleUri = Uri.parse("http://www.google.com");
        googleIntent.setData(googleUri);

        startActivity(googleIntent);
    }

    public void onContactClicked(View v){

        //Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts");
        // Proper way to do this :
        Intent contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactIntent, CONTACT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CONTACT_REQUEST_CODE:
                if (RESULT_OK == resultCode){
                    // the Intent 'data' contains the information you requested
                    Uri contactUri = data.getData();
                    // Do something with the contact Uri.

                    // We want for our example the name of the contact
                    String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                    // if you wanted the phone number for example change all Phone.DISPLAY_NAME by Phone.NUMBER
                    // String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};


                    // Obtain a cursor, like you would find in databases
                    Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    // Get the index of the column containing the DisplayName
                    int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = cursor.getString(column);

                    Toast.makeText(this, "You picked : " + name, Toast.LENGTH_LONG).show();
                    cursor.close();
                }
                else { // something wrong happened (cancel, back, etc ...)

                    // Display a Toast message : Temporary message on top of the activity
                    Toast.makeText(this, "Could'nt fetch a contact...", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
