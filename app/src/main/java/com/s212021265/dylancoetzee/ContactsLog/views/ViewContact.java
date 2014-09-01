package com.s212021265.dylancoetzee.ContactsLog.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.s212021265.dylancoetzee.ContactsLog.R;
import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;
import com.s212021265.dylancoetzee.ContactsLog.repository.DataSourceDAO;
import com.s212021265.dylancoetzee.ContactsLog.repository.Impl.DatasourceDAOImpl;

import java.util.ArrayList;


public class ViewContact extends Activity {

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        list = (ListView)findViewById(R.id.listView);
        list.setAdapter(new MyAdapter(this));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView contactId = (TextView) view.findViewById(R.id.Id);
                int d = Integer.parseInt(contactId.getText().toString());
                Intent intent = new Intent(getApplication(), EditContact.class);
                intent.putExtra("Id", d);
                finish();
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
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

class MyAdapter extends BaseAdapter
{

    ArrayList<Contact> contacts;
    DataSourceDAO dao;
    Context c;
    public MyAdapter(Context context) {
        c = context;
        dao = new DatasourceDAOImpl(context);
        contacts = dao.findAll();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.single_row, parent, false);
        TextView id = (TextView)row.findViewById(R.id.Id);
        TextView surname = (TextView)row.findViewById(R.id.txtSurname);
        TextView cell = (TextView)row.findViewById(R.id.txtNumber);

        Contact temp = contacts.get(position);
        id.setText(temp.getId() + "");
        surname.setText(temp.getLastName());
        cell.setText(temp.getCellNumber());
        return row;
    }
}
