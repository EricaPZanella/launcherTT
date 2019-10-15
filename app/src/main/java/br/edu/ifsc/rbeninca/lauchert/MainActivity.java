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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView chromeIcon ;
    ListView listView;

    public static final String TAG ="Apps";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PackageManager packageManager= getPackageManager();


        final PackageManager pm = getPackageManager();
        //get a list of installed apps
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
        listView=findViewById(R.id.listView);

       // aplicativosList=getPackagesInstaleds(pm);

        ArrayList<AppInfo> aplicativosList=loadAppInf();
        AppInfoArrayAdapter arrayAdapter  = new AppInfoArrayAdapter(getApplicationContext(),R.layout.item_list_app,aplicativosList);

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,
//                                                R.layout.item_list_app,
//                                                aplicativosList
//                                                );
        listView.setAdapter(arrayAdapter);
    }


    public  ArrayList<String> getPackagesInstaleds(PackageManager pm ){
        ArrayList<String>  arrayList = new ArrayList<String>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getApplicationContext().getPackageManager().queryIntentActivities( mainIntent, 0);

        Iterator<ResolveInfo> it  =pkgAppsList.iterator();
        while (it.hasNext()){
            ResolveInfo resolveInfo = it.next();
            String pkname = resolveInfo.activityInfo.packageName;
            if (pkname != null ){
                arrayList.add(pkname);
            }
        }
        return  arrayList;
     }


    public  ArrayList<String> getPackagesAplicationsInstalleds(PackageManager pm ){
        ArrayList<String>  arrayList = new ArrayList<String>();
        arrayList=getPackagesInstaleds(pm);

        return  arrayList;
    }

    public  ArrayList<String> getNamesApps(PackageManager pm ){
        ArrayList<String>  arrayList = new ArrayList<String>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for ( PackageInfo pi: packs) {
            if(pi.applicationInfo.FLAG_SYSTEM==1){
                arrayList.add(pi.applicationInfo.loadLabel(getPackageManager()).toString());
            }

        }
        return  arrayList;
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



    public  Drawable getIconApp(PackageInfo pi){
        return  pi.applicationInfo.loadIcon(getPackageManager());
    }

    public void onChromeButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        startActivity(launchIntent);
    }

    public static Drawable getActivityIcon(Context context, String packageName, String activityName) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);
        return resolveInfo.loadIcon(pm);
    }







}
