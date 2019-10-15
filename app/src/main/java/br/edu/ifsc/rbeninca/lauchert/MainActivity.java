package br.edu.ifsc.rbeninca.lauchert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        ArrayList<AppInfo> aplicativosList=loadAppInf();
        final AppInfoArrayAdapter arrayAdapter  = new AppInfoArrayAdapter(getApplicationContext(),
                                                                    R.layout.item_list_app,
                                                                    aplicativosList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = getPackageManager().getLaunchIntentForPackage ( ((AppInfo) adapterView.getItemAtPosition(i)).pname);
                //intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }



    public  ArrayList<AppInfo> loadAppInf(){
        ArrayList<AppInfo>  arrayList = new ArrayList<AppInfo>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for ( PackageInfo pi: packs) {
            if(pi.applicationInfo.FLAG_SYSTEM==1){
                arrayList.add(new AppInfo(pi,getPackageManager()));
            }
        }
        return  arrayList;
    }








}
