package br.edu.ifsc.rbeninca.lauchert;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    LaucherControler laucherControler;
    EditText EditTextPesquisa;
    ArrayList<AppInfo> aplicativosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        EditTextPesquisa = findViewById(R.id.editTextKeyWord);

        laucherControler=new  LaucherControler(getApplicationContext());
        this.loadListView("");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = getPackageManager().getLaunchIntentForPackage ( ((AppInfo) adapterView.getItemAtPosition(i)).pname);
                //intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        EditTextPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loadListView(EditTextPesquisa.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        }

    public void loadListView(String key ){
        aplicativosList=laucherControler.loadAppInf(key);
        AppInfoArrayAdapter arrayAdapter  = new AppInfoArrayAdapter(getApplicationContext(),
                R.layout.item_list_app,
                aplicativosList );

                listView.setAdapter(arrayAdapter);
    }











}
