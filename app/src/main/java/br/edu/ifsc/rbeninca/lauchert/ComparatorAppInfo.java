package br.edu.ifsc.rbeninca.lauchert;

import java.util.Comparator;

public class ComparatorAppInfo implements Comparator<AppInfo> {


    @Override
    public int compare(AppInfo appInfo1, AppInfo appInfo2) {
        return appInfo1.appname.compareTo(appInfo2.appname);
    }


}
