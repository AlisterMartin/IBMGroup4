package com.ibm.watson.developer_cloud.android.myapplication;

import java.io.Serializable;

public class User implements Serializable {

    private String uName;

    public User(String uName)
    {
        uName = "";
    }

    public void setUserName(String uName)
    {
        this.uName = uName;
    }

    public String getUserName()
    {
        return uName;
    }
}
