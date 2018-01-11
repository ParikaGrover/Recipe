package com.example.android.work;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

/**
 * Created by DELL on 08-01-2018.
 */

public class Global extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DbopenHelper(this, "Recipe").getWritableDb()).newSession();


    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public DaoSession getAppDaoSession() {
        return ((Global) getApplicationContext()).getDaoSession();
    }


}


