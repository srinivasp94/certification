package com.example.pegasys.pegasyshmis.model;

/**
 * Created by pegasys on 3/22/2018.
 */

public class specDoclist {

    public String id;
    public String Doc_name;
//    public String specialisationName;
    public String experience;
//    public String degreeName;
//    public String hospitalName;
    public String distance;
    public String phone;
    public String profilePic;

    public specDoclist(String id, String doc_name,String experience, String distance, String phone, String profilePic) {
        this.id = id;
        Doc_name = doc_name;
//        this.specialisationName = specialisationName;
        this.experience = experience;
//        this.degreeName = degreeName;
//        this.hospitalName = hospitalName;
        this.distance = distance;
        this.phone = phone;
        this.profilePic = profilePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoc_name() {
        return Doc_name;
    }

    public void setDoc_name(String doc_name) {
        Doc_name = doc_name;
    }



    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

//    public String getDegreeName() {
//        return degreeName;
//    }
//
//    public void setDegreeName(String degreeName) {
//        this.degreeName = degreeName;
//    }
//
//    public String getHospitalName() {
//        return hospitalName;
//    }
//
//    public void setHospitalName(String hospitalName) {
//        this.hospitalName = hospitalName;
//    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return "specDoclist{" +
                "id='" + id + '\'' +
                ", Doc_name='" + Doc_name + '\'' +
                ", experience='" + experience + '\'' +
                ", distance='" + distance + '\'' +
                ", phone='" + phone + '\'' +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }
}
