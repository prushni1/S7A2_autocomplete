package connecttoandroid.s7a2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Listview Adapter
    ArrayAdapter<String> adapter;


    DatabaseHandlerGaud db_gaud;
    AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db_gaud = new DatabaseHandlerGaud(this);

          actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);

        Log.d("Insert: ", "Inserting ..");
        db_gaud.addContact(new GaudDbFile("samsung", "1234", "bdate_A"));
        db_gaud.addContact(new GaudDbFile("iphone", "9199999999","bdate_B"));
        db_gaud.addContact(new GaudDbFile("micromax", "9522222222","bdate_C"));
        db_gaud.addContact(new GaudDbFile("jio", "9533333333","bdate_D"));
        db_gaud.addContact(new GaudDbFile("gionee", "9533333333","bdate_D"));
        db_gaud.addContact(new GaudDbFile("mobile", "9533333333","bdate_D"));



        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<GaudDbFile> contacts_gaud = db_gaud.getAllContacts();

        String[] countries =db_gaud.getAllCountries1();

        for (GaudDbFile cn : contacts_gaud) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Log :   ", log);

        }


         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, countries);
        actv.setAdapter(adapter);


    }
}
