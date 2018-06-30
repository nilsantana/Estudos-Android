package com.example.nil.bloconotas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText ettext;
    Button btCriar;
    ListView listview;

    private ArrayList<String> notas = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ettext = (EditText) findViewById(R.id.ettext);
        btCriar = (Button) findViewById(R.id.btCriar);
        listview = (ListView) findViewById(R.id.listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notas);
        listview.setAdapter(adapter);
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNota = ettext.getText().toString();
                if (textoNota.length()>0){
                    ettext.setText("");
                    ettext.findFocus();
                    notas.add(textoNota);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder((MainActivity.this));
                adb.setTitle("Nota!");
                adb.setMessage("Você deseja apagar esta nota?");
                final int positionToRemove = position;
                adb.setNegativeButton("Não",null);
                adb.setPositiveButton("Sim",new AlertDialog.OnClickListener(){
                    public void  onClick(DialogInterface dialog, int which){
                        notas.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });
    }
}
