package lat.uin.informatika.praktikumdb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView selection;
    ListView list;
    List<Contact> contacts;
    ArrayAdapter<String> listAdapter;
    ArrayList<String> namekon;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = (TextView) findViewById(R.id.selection);
        list = (ListView) findViewById(R.id.list);
        db = new DBHandler(this);
        contacts = db.getAllContacts();
        namekon = new ArrayList<String>();
        for (Contact cn : contacts) {
            namekon.add(cn.getName());
        }
        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow,
                namekon);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                // TODO Auto-generated method stub
                int idkon = (int) id + 1;
                Intent i = new Intent(MainActivity.this, EditContacts.class);
                i.putExtra("idkontak", idkon);
                startActivity(i);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                removeItemFromList(pos);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.tambah) {
            Intent i = new Intent(MainActivity.this, NewContacts.class);
            startActivity(i);
        } else if (id == R.id.keluar) {
            AppExit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void AppExit() {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void removeItemFromList(int pos) {
        final int deletePosition = pos;

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Delete");
        alert.setMessage("Apakah anda ingin menghapus kontak ini?");
        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Contact condel = db.getContact(deletePosition+1);
                db.deleteContact(condel);
                namekon.remove(deletePosition);
                listAdapter.notifyDataSetChanged();
                listAdapter.notifyDataSetInvalidated();
            }
        });
        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }
}
