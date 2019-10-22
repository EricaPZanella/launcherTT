package br.edu.ifsc.rbeninca.lauchert;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static br.edu.ifsc.rbeninca.lauchert.AppInfo.pname;

public class LaucherControler {
    Context mContext ;
    ArrayList<AppInfo>  appInfoArrayList ;
    List<ApplicationInfo> applicationInfoList;
    ArrayList<AppInfo> appInfoArrayListFinal;

    LaucherControler ( Context  context){
        mContext = context;
        applicationInfoList= mContext.getPackageManager().getInstalledApplications(0);
        appInfoArrayList = new ArrayList<AppInfo>();
    }


    public ArrayList<AppInfo> loadAppInf(final String key){
        appInfoArrayList = new ArrayList<AppInfo>();
        appInfoArrayListFinal = new ArrayList<AppInfo>();


        // LoadApps using Intent Query and filter intent category Launcher.
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = mContext.getPackageManager().queryIntentActivities( mainIntent, 0);
        Iterator<ResolveInfo> resolveInfoIterator = pkgAppsList.iterator();

        //Create arrayListAppInfo, (Easily optimized, but not done)
        while(resolveInfoIterator.hasNext()){
            ResolveInfo resolveInfo = resolveInfoIterator.next();
            appInfoArrayList.add(new AppInfo(resolveInfo.activityInfo.applicationInfo,mContext.getPackageManager()));
        }

        //Order by itens
        ComparatorAppInfo comparator = new ComparatorAppInfo();
        //Lamda Expression (>1.8)  see build.gralde  has pragma compile
        Collection<AppInfo>  appInfos = appInfoArrayList.stream().filter((d) -> d.appname.toLowerCase().contains (key.toLowerCase().trim())).collect(Collectors.toList());
        appInfoArrayList = new ArrayList<>(appInfos);

        Collections.sort(appInfoArrayList, comparator);

        String [] pakApps  =  {"com.google.android.youtube"}; //, "com.google.android.dialer", "com.google.android.apps.messaging"};

//        String [] pakApps  = {"com.google.android.dialer", "com.google.android.apps.messaging", "com.android.contacts", "com.google.android.deskclock", "com.android.calculator2",
//                "com.google.android.music", "com.google.android.apps.maps", "com.google.android.youtube", "com.google.android.apps.photos", "com.google.android.apps.photos", "com.google.android.videos",
//                "com.android.settings", "com.android.chrome"};


        for(AppInfo appInfo : appInfoArrayList) {
            for (int x = 0; x < pakApps.length; x++) {
                if(appInfo.pname.equals(pakApps[x])){
                    appInfoArrayListFinal.add(appInfo);
                }
            }
        }
        return  appInfoArrayListFinal;
    }

}
