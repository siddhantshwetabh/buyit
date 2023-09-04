package com.user.entity;

import com.user.dto.BillingAdress;

import javax.persistence.*;
@Entity
@Table(name = "User")
public class User {

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String userFirstName;

    private String userLastName;

    private String userPassword;

    private String phNo;

    private String emailId;

   /* @Transient
    private List<Rating> ratings = new ArrayList<>();*/

    @Transient
    private BillingAdress billingAdresses = new BillingAdress();


    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

}
