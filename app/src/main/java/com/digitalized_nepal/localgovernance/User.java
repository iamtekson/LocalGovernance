package com.digitalized_nepal.localgovernance;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String fullName;
    public String wardno;
    public String citizenshipno;
    public String province;
    public String municipality;

    public User()
    {

    }

    public User(String fullName, String wardno, String citizenshipno, String province, String municipality) {
        this.fullName = fullName;
        this.wardno = wardno;
        this.citizenshipno = citizenshipno;
        this.province = province;
        this.municipality = municipality;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWardno() {
        return wardno;
    }

    public void setWardno(String wardno) {
        this.wardno = wardno;
    }

    public String getCitizenshipno() {
        return citizenshipno;
    }

    public void setCitizenshipno(String citizenshipno) {
        this.citizenshipno = citizenshipno;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
}