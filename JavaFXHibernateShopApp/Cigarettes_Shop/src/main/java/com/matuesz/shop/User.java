package com.matuesz.shop;

public class User {

    private String id;
    private String nick;
    private String time_joined;
    private String email;

    private UserExtendedInfo userExtendedInfo;

    public User(String id, String nick, String time_joined, String email) {
        this.id = id;
        this.nick = nick;
        this.time_joined = time_joined;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getTime_joined() {
        return time_joined;
    }

    public String getEmail() {
        return email;
    }

    public UserExtendedInfo getUserExtendedInfo() {
        return userExtendedInfo;
    }

    public void setExtendedInfo(UserExtendedInfo extendedInfo) {
        this.userExtendedInfo = extendedInfo;
    }

    public static UserBuilder build() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        if (userExtendedInfo == null) {
            return "ID: " + id + "\nNICK: " + nick + "\nJOINED: " + time_joined + "\nEMAIL: " + email;
        } else {
            return "ID: " + id + "\nNICK: " + nick + "\nJOINED: " + time_joined + "\nEMAIL: " + email +
                    "\n    <EXTRA INFO>    \n" + "IS ADMIN: " + userExtendedInfo.getIsAdmin()
                    + "\nADDRESS: " + userExtendedInfo.getAddress() + "\nPHONE NUMBER: "
                    + userExtendedInfo.getPhoneNumber() + "\nGENDER: " + userExtendedInfo.getGender();
        }
    }

}
