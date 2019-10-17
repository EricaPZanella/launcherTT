package br.edu.ifsc.rbeninca.lauchert;

import android.graphics.drawable.Drawable;

import java.util.Comparator;

public class ComparatorAppInfo implements Comparator<AppInfo> {

    public enum Order {appname, datadir, packagedir}

    private Order sortingBy = Order.packagedir;

    @Override
    public int compare(AppInfo appInfo1, AppInfo appInfo2) {

        switch(sortingBy) {
            case appname: return appInfo1.appname.compareTo(appInfo2.appname);
            case datadir: return appInfo1.datadir.compareTo(appInfo2.datadir);
            case packagedir: return appInfo1.pname.compareTo(appInfo2.pname);
        }
        return appInfo1.appname.compareTo(appInfo2.appname);
    }
    public void setSortingBy(Order sortBy) {
        this.sortingBy = sortingBy;
    }



}
