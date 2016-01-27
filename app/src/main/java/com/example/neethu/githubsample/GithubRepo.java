package com.example.neethu.githubsample;

/**
 * Created by neethu on 22/1/16.
 */
public class GithubRepo {
    private String name;
    private String url;

    public GithubRepo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getname() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
