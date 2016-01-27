package com.example.neethu.githubsample;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by neethu on 21/1/16.
 */
public interface GithubAPI {

    @GET("/users/{user}")
    void getPerson(@Path("user") String user, Callback<GithubPerson> personCallback);

    @GET("/users/{user}/repos")
    void getUrl(@Path("user") String user, Callback<List<GithubRepo>> urlCallback);


}
