package com.priorityproperties.avantraifiledownload;

import android.content.ContentValues;

public class FileHelper {


    public static final String TABLE_NAME = "Dictionary";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PAGE_INFO = "pageInfo";
    private int id;
    private String pageInfo;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PAGE_INFO + " TEXT"
                                       + ")";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo;
    }
}
