package br.edu.ifsc.rbeninca.lauchert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView chromeIcon ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PackageManager packageManager= getPackageManager();

        int i=0;
        List<PackageInfo>  packageInfoList = packageManager.getInstalledPackages(i);
        Iterator<PackageInfo> it  = packageInfoList.iterator();
       while(it.hasNext() ){
            PackageInfo packageInfo = it.next();
            Log.i("packageInfo", i +" "+packageInfo.toString());
            i++;
        }

       chromeIcon= findViewById(R.id.chromeButton);
       chromeIcon.setImageDrawable(getActivityIcon(getApplicationContext(), "com.android.chrome", "com.google.android.apps.chrome.Main"));

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
