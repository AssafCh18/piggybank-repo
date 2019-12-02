package me.gustavs.piggybank.database;

import android.app.Application;

import me.gustavs.piggybank.entities.DaoMaster;
import me.gustavs.piggybank.entities.DaoSession;

public class App extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        mDaoSession = new DaoMaster(new DbOpenHelper(
                this,
                "piggybank.db"
        ).getWritableDb()).newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}