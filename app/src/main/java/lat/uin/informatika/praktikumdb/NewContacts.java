package lat.uin.informatika.praktikumdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContacts extends AppCompatActivity {

    Contact isikontak;
    DBHandler db;
    int idkontak;
    String namakontak;
    String nokontak;
    EditText nmkontak;
    EditText telpkontak;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contacts);

        nmkontak = (EditText) findViewById(R.id.namasave);
        telpkontak = (EditText) findViewById(R.id.kontaksave);
        btnsave = (Button) findViewById(R.id.btnSave);
        db = new DBHandler(this);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                String nama = nmkontak.getText().toString();
                String kontak = telpkontak.getText().toString();
                db.addContact(new Contact(nama, kontak));
                Toast.makeText(getApplicationContext(), "Kontak baru di simpan", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NewContacts.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
