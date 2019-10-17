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

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    LaucherControler laucherControler;
    EditText editTextPesquisa;
    ArrayList<AppInfo> aplicativosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editTextPesquisa = findViewById(R.id.editTextKeyWord);

        //Inicialização do controlador do Laycher
        laucherControler=new  LaucherControler(getApplicationContext());
        this.loadListView(""); //Carga inicial do laucher


        //definição dos listeners para click e pesquisa
        listView.setOnItemClickListener( this.onItemClickListener);
        editTextPesquisa.addTextChangedListener(this.textWatcherPesquisa);
        }



    public void loadListView(String key ){
        aplicativosList=laucherControler.loadAppInf(key);
        AppInfoArrayAdapter arrayAdapter  = new AppInfoArrayAdapter(getApplicationContext(),
                R.layout.item_list_app,
                aplicativosList );
                listView.setAdapter(arrayAdapter);
    }
    //Implementação clase anonima para click em listview.
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = getPackageManager().getLaunchIntentForPackage ( ((AppInfo) adapterView.getItemAtPosition(i)).pname);
            //intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };


    //Implementação do analisador texto para o campo de pesquisa
    private TextWatcher textWatcherPesquisa =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            loadListView(editTextPesquisa.getText().toString());
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };










}
