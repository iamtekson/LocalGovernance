package com.digitalized_nepal.localgovernance;

public class userfeedback {

    private String username;
    private String projecttitle;
    private String projectdesc;

    public userfeedback()
    {

    }

    public userfeedback(String username, String projecttitle, String projectdesc) {
        this.username = username;
        this.projecttitle = projecttitle;
        this.projectdesc = projectdesc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjecttitle() {
        return projecttitle;
    }

    public void setProjecttitle(String projecttitle) {
        this.projecttitle = projecttitle;
    }

    public String getProjectdesc() {
        return projectdesc;
    }

    public void setProjectdesc(String projectdesc) {
        this.projectdesc = projectdesc;
    }
}
