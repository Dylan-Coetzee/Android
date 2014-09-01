package com.s212021265.dylancoetzee.ContactsLog.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.s212021265.dylancoetzee.ContactsLog.R;
import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;
import com.s212021265.dylancoetzee.ContactsLog.repository.DataSourceDAO;
import com.s212021265.dylancoetzee.ContactsLog.repository.Impl.DatasourceDAOImpl;

/**
 * Created by Dylan 21/08/2014.
 */

public class EditContact extends Activity {

    int id;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText cell;
    EditText address;

    DataSourceDAO dao = new DatasourceDAOImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        firstName = (EditText)findViewById(R.id.txtName);
        lastName = (EditText)findViewById(R.id.txtLastName);
        email = (EditText)findViewById(R.id.txtEmail);
        cell = (EditText)findViewById(R.id.txtPhoneNumber);
        address = (EditText)findViewById(R.id.txtHomeAddress);

        Intent intent = getIntent();
        id = intent.getIntExtra("Id", 0);
        Contact contact = dao.findUserById(id);

        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        email.setText(contact.getEmailAddress());
        cell.setText(contact.getCellNumber());
        address.setText(contact.getHomeAddress());
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

    public void updateContact(View view) {
        Contact contact = new Contact.Builder(firstName.getText().toString())
                .lastName(lastName.getText().toString())
                .emailAddress(email.getText().toString())
                .cellNumber(cell.getText().toString())
                .homeAddress(address.getText().toString())
                .id(id)
                .build();
        dao.updateContact(contact);
        CharSequence text = "Contact Updated";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        finish();
        backToMain(view);
    }

    public void deleteContact(View view) {
        Contact contact = new Contact.Builder(firstName.getText().toString())
                .lastName(lastName.getText().toString())
                .emailAddress(email.getText().toString())
                .cellNumber(cell.getText().toString())
                .homeAddress(address.getText().toString())
                .id(id)
                .build();
        dao.deleteContact(contact);
        CharSequence text = "Contact Deleted";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        finish();
        backToMain(view);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        finish();
        startActivity(intent);
    }

}
