package com.sigin.domain;

/**
 * Created by dam on 14-5-21.
 */
public class SignRecord {

    private String recordId;
    private String userId;
    private String optTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }
}
