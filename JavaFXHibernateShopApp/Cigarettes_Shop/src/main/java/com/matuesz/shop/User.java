package com.matuesz.shop;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String nick;
    private String time_joined;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserExtraInfo userExtraInfo;

    public User() {
    }

    public User(int id, String nick, String time_joined, String email) {
        this.id = id;
        this.nick = nick;
        this.time_joined = time_joined;
        this.email = email;
    }

    public void setUserExtraInfo(UserExtraInfo userExtraInfo) {
        this.userExtraInfo = userExtraInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setTime_joined(String time_joined) {
        this.time_joined = time_joined;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UserExtraInfo getUserExtraInfo() {
        return userExtraInfo;
    }

    public int getId() {
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

    public static UserBuilder build() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        if (userExtraInfo == null) {
            return "ID: " + id + "\nNICK: " + nick + "\nJOINED: " + time_joined + "\nEMAIL: " + email;
        } else {
            return "ID: " + id + "\nNICK: " + nick + "\nJOINED: " + time_joined + "\nEMAIL: " + email +
                    "\n\n<EXTRA INFO>" + "\nIS ADMIN: " + userExtraInfo.getIsAdmin() +
                    "\nADRESS: " + userExtraInfo.getAddress() +
                    "\nPHONE: " + userExtraInfo.getPhoneNumber();
        }
    }

}
