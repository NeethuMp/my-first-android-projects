package com.example.neethu.githubsample;


/**
 * Created by neethu on 21/1/16.
 */
public class GithubPerson {
    public int id;
    public String login;
    public String avatar_url;

    @Override
    public String toString() {
        return this.id + "|" + this.login + "|" + this.avatar_url;
    }
}

    //Getters and setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }




