package com.example.steakhouse;

import java.util.Date;
import java.util.UUID;

public class Steak {
    private UUID mId;
    private String mTitle;
    private String mAddress;
    private String mComments;
    private Date mDate;
    private boolean mRecmed;

    public Steak() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public UUID getId() {
        return mId; }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAddress() {
        return mAddress;
    }
    public void setAddress(String address) {
        mAddress = address;
    }
    public String getComments() {
        return mComments;
    }
    public void setComments(String comments) { mComments = comments; }

    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isRecmed() {
        return mRecmed;
    }
    public void setRecmed(boolean Recmed) {
        mRecmed = Recmed;
    }
}
