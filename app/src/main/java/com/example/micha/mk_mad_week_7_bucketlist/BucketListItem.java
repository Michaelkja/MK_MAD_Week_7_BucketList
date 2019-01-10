package com.example.micha.mk_mad_week_7_bucketlist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "BucketListItems")
public class BucketListItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String  name;
    private String  description;
    private Boolean checked;

    public BucketListItem(String name, String description, Boolean checked) {
        this.name = name;
        this.description = description;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}