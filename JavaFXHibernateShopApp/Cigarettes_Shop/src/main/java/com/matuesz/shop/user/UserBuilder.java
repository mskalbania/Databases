package com.matuesz.shop.user;

public class UserBuilder {

    private int id;
    private String nick;
    private String time_joined;
    private String email;

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withNick(String nick) {
        this.nick = nick;
        return this;
    }

    public UserBuilder withTimeJoined(String time) {
        this.time_joined = time;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public User get() {
        return new User(id, nick,time_joined, email);
    }
}

