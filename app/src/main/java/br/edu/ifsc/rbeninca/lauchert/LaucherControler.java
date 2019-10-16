package br.edu.ifsc.rbeninca.lauchert;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LaucherControler {
    Context mContext ;

    LaucherControler ( Context  context){
        mContext = context;
    }


    public ArrayList<AppInfo> loadAppInf(final String key){
        ArrayList<AppInfo>  arrayList = new ArrayList<AppInfo>();
        List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
        for ( PackageInfo pi: packs) {
            if(pi.applicationInfo.FLAG_SYSTEM==1){
                arrayList.add(new AppInfo(pi,mContext.getPackageManager()));
            }
        }
        ComparatorAppInfo comparator = new ComparatorAppInfo();
        Collection<AppInfo>  appInfos = arrayList.stream().filter((d) -> d.appname.toLowerCase().contains (key.toLowerCase())).collect(Collectors.toList());
        arrayList = new ArrayList<>(appInfos);
        Collections.sort(arrayList, comparator);

        return  arrayList;
    }



}
