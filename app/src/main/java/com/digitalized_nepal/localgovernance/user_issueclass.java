package com.digitalized_nepal.localgovernance;

public class user_issueclass {

    private String iss_name;
    private String des;
    private String topic_name;

    public user_issueclass()
    {

    }

    public user_issueclass(String iss_name, String des, String topic_name) {
        this.iss_name = iss_name;
        this.des = des;
        this.topic_name = topic_name;
    }

    public String getIss_name() {
        return iss_name;
    }

    public void setIss_name(String iss_name) {
        this.iss_name = iss_name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }
}
