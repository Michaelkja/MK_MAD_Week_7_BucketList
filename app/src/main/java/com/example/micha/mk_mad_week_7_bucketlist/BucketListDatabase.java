package com.example.micha.mk_mad_week_7_bucketlist;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = BucketListItem.class, version = 1)
public abstract class BucketListDatabase extends RoomDatabase {

    private static BucketListDatabase instance;

    public abstract BucketListItemDao bucketListItemDao();

    public static synchronized BucketListDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BucketListDatabase.class, "BucketListDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static final class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BucketListItemDao bucketListItemDao;

        private PopulateDbAsyncTask(BucketListDatabase db) {
            bucketListItemDao = db.bucketListItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bucketListItemDao.insert(new BucketListItem("Big Ben", "Holiday", false));
            bucketListItemDao.insert(new BucketListItem("Big Apple", "Holiday", true));
            bucketListItemDao.insert(new BucketListItem("Eiffel tower", "Holiday", true));
            return null;
        }
    }
}