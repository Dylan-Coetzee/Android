package com.s212021265.dylancoetzee.ContactsLog.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.s212021265.dylancoetzee.ContactsLog.MyDetailsActivity;
import com.s212021265.dylancoetzee.ContactsLog.R;
import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;
import com.s212021265.dylancoetzee.ContactsLog.repository.DataSourceDAO;
import com.s212021265.dylancoetzee.ContactsLog.repository.Impl.DatasourceDAOImpl;

/**
 * Created by Dylan 21/08/2014.
 */

public class MainActivity extends Activity {

    private EditText firstName;
    private EditText lastName;
    private EditText emailAddress;
    private EditText cellNumber;
    private EditText homeAddress;

    final DataSourceDAO dao = new DatasourceDAOImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = (EditText)findViewById(R.id.txtName);
                lastName = (EditText)findViewById(R.id.txtLastName);
                emailAddress = (EditText)findViewById(R.id.txtEmail);
                cellNumber = (EditText)findViewById(R.id.txtPhoneNumber);
                homeAddress = (EditText)findViewById(R.id.txtHomeAddress);

                Contact contact = new Contact.Builder(firstName.getText().toString())
                        .lastName(lastName.getText().toString())
                        .emailAddress(emailAddress.getText().toString())
                        .cellNumber(cellNumber.getText().toString())
                        .homeAddress(homeAddress.getText().toString())
                        .build();

                dao.createContact(contact);
                Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_LONG).show();
                finish();
                //toList(view);

                /*CREATE THE INTENT*/
                final Intent intent = new Intent(MainActivity.this, ViewContact.class);
                startActivity(intent);
            }
        });

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

    public void saveContact(View view) {

    }

    public void toList (View view) {
        Intent intent = new Intent(getApplication(), ViewContact.class);
        startActivity(intent);
    }

}
