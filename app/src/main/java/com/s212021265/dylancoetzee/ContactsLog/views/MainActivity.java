package com.s212021265.dylancoetzee.ContactsLog.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.s212021265.dylancoetzee.ContactsLog.R;
import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;
import com.s212021265.dylancoetzee.ContactsLog.repository.DataSourceDAO;
import com.s212021265.dylancoetzee.ContactsLog.repository.Impl.DataSourceDAOImpl;


public class MainActivity extends Activity {

    private Button pressMe;
    private DataSourceDAO dao;

    EditText phone_number_edittext;
    EditText last_name_edittext;
    EditText first_name_edittext;
    EditText email_address_edittext;
    EditText home_address_edittext;

    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";
    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayWelcome();

        pressMe = (Button) findViewById(R.id.press_me_button);
        phone_number_edittext = (EditText) findViewById(R.id.phone_number_edittext);
        last_name_edittext = (EditText) findViewById(R.id.last_name_edittext);
        first_name_edittext = (EditText) findViewById(R.id.first_name_edittext);
        email_address_edittext = (EditText) findViewById(R.id.email_address_edittext);
        home_address_edittext = (EditText) findViewById(R.id.home_address_edittext);

        dao = new DataSourceDAOImpl(this);

        pressMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Log.i("button click", phone_number_edittext.getText().toString());

                Contact contact = new Contact.Builder(phone_number_edittext.getText().toString())
                        .setlName(last_name_edittext.getText().toString())
                        .setfName(first_name_edittext.getText().toString())
                        .setEmailAddress(email_address_edittext.getText().toString())
                        .setHomeAddress(home_address_edittext.getText().toString())
                        .build();

                dao.createContact(contact);


                int size = dao.getCursor();
                contact = dao.findContactByID(size);

                Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
                detailIntent.putExtra("ContactID", contact.getId());
                startActivity(detailIntent);

            }
        });
    }

    public void displayWelcome() {

        // Access the device's key-value storage
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        // Read the user's name,
        // or an empty string if nothing found
        String name = mSharedPreferences.getString(PREF_NAME, "");

        if (name.length() > 0) {

            // If the name is valid, display a Toast welcoming them
            Toast.makeText(this, name + ",Please enter details, note: *compulsory fields", Toast.LENGTH_LONG).show();
        } else {

            // otherwise, show a dialog to ask for their name
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Hello!");
            alert.setMessage("What is your name?");

            // Create EditText for entry
            final EditText input = new EditText(this);
            alert.setView(input);

            // Make an "OK" button to save the name
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {

                    // Grab the EditText's input
                    String inputName = input.getText().toString();

                    // Put it into memory (don't forget to commit!)
                    SharedPreferences.Editor e = mSharedPreferences.edit();
                    e.putString(PREF_NAME, inputName);
                    e.commit();

                    // Welcome the new user
                    Toast.makeText(getApplicationContext(), inputName + " ,Please enter details, note: *compulsory fields", Toast.LENGTH_LONG).show();
                }
            });

            // Make a "Cancel" button
            // that simply dismisses the alert
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });

            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
