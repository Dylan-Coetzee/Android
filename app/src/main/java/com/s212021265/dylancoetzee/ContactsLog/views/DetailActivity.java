package com.s212021265.dylancoetzee.ContactsLog.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.s212021265.dylancoetzee.ContactsLog.R;
import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;
import com.s212021265.dylancoetzee.ContactsLog.repository.DataSourceDAO;
import com.s212021265.dylancoetzee.ContactsLog.repository.Impl.DataSourceDAOImpl;

/**
 * Created by Kurvin Hendricks on 20/08/2014.
 */
public class DetailActivity extends Activity{


    private DataSourceDAO dao;

    TextView phneNumber_detail;
    TextView lname_detail;
    TextView fname_detail;
    TextView email_detail;
    TextView address_detail;
    Button return_button;
    Button show_details_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dao = new DataSourceDAOImpl(this);
        phneNumber_detail = (TextView) findViewById(R.id.phneNumber_detail);
        lname_detail = (TextView) findViewById(R.id.lname_detail);
        fname_detail = (TextView) findViewById(R.id.fname_detail);
        email_detail = (TextView) findViewById(R.id.email_detail);
        address_detail = (TextView) findViewById(R.id.address_detail);

        return_button = (Button) findViewById(R.id.return_button);
        show_details_button = (Button) findViewById(R.id.show_details_button);

        int ContactID = this.getIntent().getExtras().getInt("ContactID");
        Contact contact;

        Log.i("details activity id", ContactID + "");

        contact = dao.findContactByID(ContactID);

        phneNumber_detail.setText(contact.getCellNumber());
        lname_detail.setText(contact.getlName());

        fname_detail.setText(contact.getfName());
        email_detail.setText(contact.getEmailAddress());
        address_detail.setText(contact.getHomeAddress());

        //Toast.makeText(getApplicationContext(), contact.getId() + " " + contact.getlName() + " " + contact.getCellNumber(), Toast.LENGTH_LONG).show();
        show_details_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(null);
                alert.setTitle("Full Details");
                alert.setMessage("Phone Number: "+phneNumber_detail.getText());
                alert.setMessage("First name: "+fname_detail.getText());
                alert.setMessage("Last Name: "+lname_detail.getText());
                alert.setMessage("email: "+email_detail.getText());
                alert.setMessage("Address: "+address_detail.getText());

                LinearLayout detail_view = (LinearLayout) findViewById(R.id.detail_view);
                detail_view.setVisibility(View.VISIBLE);
            }
        });

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newContact = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newContact);
            }
        });

    }
}
