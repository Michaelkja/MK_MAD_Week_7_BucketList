package com.example.micha.mk_mad_week_7_bucketlist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

public class BucketListItemRepository {
    private BucketListItemDao bucketListItemDao;
    private LiveData<List<BucketListItem>> allItems;

    public BucketListItemRepository (Application application){
        BucketListDatabase database = BucketListDatabase.getInstance(application);
        bucketListItemDao = database.bucketListItemDao();
        allItems = bucketListItemDao.getAllItems();
    }

    public void insert(BucketListItem item) {
        new InsertItemAsyncTask(bucketListItemDao).execute(item);
    }

    public void update(BucketListItem item) {
        new UpdateItemAsyncTask(bucketListItemDao).execute(item);
    }

    public void delete(BucketListItem item) {
        new DeleteItemAsyncTask(bucketListItemDao).execute(item);
    }

    public LiveData<List<BucketListItem>> getAllItems() {
        return allItems;
    }

    private static class InsertItemAsyncTask extends AsyncTask<BucketListItem, Void, Void> {
        private BucketListItemDao bucketListItemDao;

        private InsertItemAsyncTask(BucketListItemDao bucketListItemDao) {
            this.bucketListItemDao = bucketListItemDao;
        }

        @Override
        protected Void doInBackground(BucketListItem... bucketListItems) {
            bucketListItemDao.insert(bucketListItems[0]);
            return null;
        }
    }

    private static class UpdateItemAsyncTask extends AsyncTask<BucketListItem, Void, Void> {
        private BucketListItemDao bucketListItemDao;

        private UpdateItemAsyncTask(BucketListItemDao bucketListItemDao) {
            this.bucketListItemDao = bucketListItemDao;
        }

        @Override
        protected Void doInBackground(BucketListItem... bucketListItems) {
            bucketListItemDao.update(bucketListItems[0]);
            return null;
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<BucketListItem, Void, Void> {
        private BucketListItemDao bucketListItemDao;

        private DeleteItemAsyncTask(BucketListItemDao bucketListItemDao) {
            this.bucketListItemDao = bucketListItemDao;
        }

        @Override
        protected Void doInBackground(BucketListItem... bucketListItems) {
            bucketListItemDao.delete(bucketListItems[0]);
            return null;
        }
    }
}
