package week5.orestes.cdnt4.common;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
