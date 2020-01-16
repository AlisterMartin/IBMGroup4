package com.ibm.watson.developer_cloud.android.myapplication;

public class Confrence {
    public String conferenceID;
    public String title;
    public String startTime;
    public String endTime;
    public String location;
    public String speakers;
    public String description;


    public Confrence(String conferenceID, String title, String startTime, String endTime, String location, String speakers, String description) {
        this.conferenceID = conferenceID;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.speakers = speakers;
        this.description = description;
    }

    public String timeInString(){
        String[] s = startTime.split(" ");
        String[] e = endTime.split(" ");

        return (s[0] + " " + s[2] + " " + s[3] + " " + s[5] + " " + s[7] + " to " + e[6] + ":00");
    }


}
