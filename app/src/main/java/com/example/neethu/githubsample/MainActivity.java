package com.example.neethu.githubsample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    public static final String ROOT_URL = "https://api.github.com";
    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_ID = "person_id";
    public static final String PERSON_URL = "person_url";

    Button click;
    EditText textname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        click = (Button) findViewById(R.id.button);
        textname = (EditText) findViewById(R.id.editText);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMockData();
            }
        });


    }


    public void getData() {

        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait",
                false, false);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        GithubAPI api = adapter.create(GithubAPI.class);
        String user = String.valueOf(textname.getText());


        api.getPerson(user, new Callback<GithubPerson>() {
            @Override
            public void success(GithubPerson githubPerson, Response response) {
                loading.dismiss();

                Intent intent = new Intent(MainActivity.this, ShowDetailsActivity.class);
                intent.putExtra(PERSON_NAME, githubPerson.login);
                intent.putExtra(PERSON_ID, githubPerson.id);
                intent.putExtra(PERSON_URL, githubPerson.avatar_url);

                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getMockData() {
        String mock = "{\"login\":\"vishrayne\",\"id\":985709,\"avatar_url\":\"https://avatars.githubusercontent.com/u/985709?v=3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/vishrayne\",\"html_url\":\"https://github.com/vishrayne\",\"followers_url\":\"https://api.github.com/users/vishrayne/followers\",\"following_url\":\"https://api.github.com/users/vishrayne/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/vishrayne/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/vishrayne/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/vishrayne/subscriptions\",\"organizations_url\":\"https://api.github.com/users/vishrayne/orgs\",\"repos_url\":\"https://api.github.com/users/vishrayne/repos\",\"events_url\":\"https://api.github.com/users/vishrayne/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/vishrayne/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Vishnu Ravi\",\"company\":\"MobME Wireless\",\"blog\":null,\"location\":\"Cochin\",\"email\":\"vishrayne@gmail.com\",\"hireable\":null,\"bio\":null,\"public_repos\":28,\"public_gists\":6,\"followers\":8,\"following\":5,\"created_at\":\"2011-08-17T09:36:17Z\",\"updated_at\":\"2016-01-09T14:44:13Z\"}";
        GithubPerson githubPerson = new Gson().fromJson(mock, GithubPerson.class);
        Intent intent = new Intent(MainActivity.this, ShowDetailsActivity.class);
        intent.putExtra(PERSON_NAME, githubPerson.login);
        intent.putExtra(PERSON_ID, githubPerson.id);
        intent.putExtra(PERSON_URL, githubPerson.avatar_url);

        startActivity(intent);
    }


}
