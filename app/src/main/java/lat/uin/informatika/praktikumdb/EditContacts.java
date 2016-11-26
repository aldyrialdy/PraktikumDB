package lat.uin.informatika.praktikumdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContacts extends AppCompatActivity {

    Contact isikontak;
    DBHandler db;
    int idkontak;
    String namakontak;
    String nokontak;
    EditText nmkontak;
    EditText telpkontak;
    Button btnedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        nmkontak = (EditText) findViewById(R.id.namaedit);
        telpkontak = (EditText) findViewById(R.id.kontakedit);
        btnedit = (Button) findViewById(R.id.btnEdit);
        db = new DBHandler(this);
        Intent myIntent = getIntent();
        int idkon = myIntent.getIntExtra("idkontak", 0);
        isikontak = new Contact();

        isikontak = db.getContact(idkon);
        idkontak = isikontak.getId();
        namakontak = isikontak.getName();
        nokontak = isikontak.getPhonenumber();
        nmkontak.setText(namakontak);
        telpkontak.setText(nokontak);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isikontak.setId(idkontak);
                isikontak.setName(nmkontak.getText().toString());
                isikontak.setPhonenumber(telpkontak.getText().toString());
                db.updateContact(isikontak);
                Toast.makeText(getApplicationContext(), "Kontak telah di edit", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditContacts.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
