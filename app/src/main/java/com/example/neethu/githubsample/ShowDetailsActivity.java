package com.example.neethu.githubsample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by neethu on 22/1/16.
 */
public class ShowDetailsActivity extends AppCompatActivity {


    public static final String ROOT_URL = "https://api.github.com";
    private static final String TAG = "SHOWDETAIL";
    ListView listvw;
    TextView tid, tname, turl;
    ImageView photo;
    TextView thtml;
    //    TextView reponame,repourl;
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetails_view);

        photo = (ImageView) findViewById(R.id.imageView);
        tid = (TextView) findViewById(R.id.viewid);
        tname = (TextView) findViewById(R.id.viewname);
        turl = (TextView) findViewById(R.id.viewurl);
//        listvw = (ListView) findViewById(R.id.repos_list);
//        reponame= (TextView) findViewById(R.id.rname);
//        repourl= (TextView) findViewById(R.id.rurl);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GithubPerson githubPerson = new GithubPerson();


        Intent intent = getIntent();
        githubPerson.id = intent.getIntExtra(MainActivity.PERSON_ID, 0);
        githubPerson.login = intent.getStringExtra(MainActivity.PERSON_NAME);
        githubPerson.avatar_url = intent.getStringExtra(MainActivity.PERSON_URL);


//        Picasso.with(this).
//                load("https://avatars.githubusercontent.com/u/16776337?v=3")
//                .into(photo);
        Glide.with(this).
                load(githubPerson.avatar_url)
                .into(photo);


        setPerson(githubPerson);
        //getUrl(githubPerson.login);
        getMockURL();
    }

    private void setPerson(GithubPerson person) {
        Log.d("DETAIL", person.toString());
        tid.setText(String.format("ID: %s", String.valueOf(person.id)));
        tname.setText(String.format("Name: %s", person.login));
        turl.setText(String.format("URL: %s", person.avatar_url));
    }


    public void getUrl(String user) {
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait",
                false, false);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        GithubAPI api = adapter.create(GithubAPI.class);
//       String user = String.valueOf(textname.getText());

        api.getUrl(user, new Callback<List<GithubRepo>>() {
            @Override
            public void success(List<GithubRepo> githubRepos, Response response) {
                loading.dismiss();
                Log.d(TAG, "Got repos=" + githubRepos.size());
                showList(githubRepos);
            }

            @Override
            public void failure(RetrofitError error) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void getMockURL() {
        String mock = "[{\"id\":23354546,\"name\":\"android-application-pause\",\"full_name\":\"vishrayne/android-application-pause\",\"owner\":{\"login\":\"vishrayne\",\"id\":985709,\"avatar_url\":\"https://avatars.githubusercontent.com/u/985709?v=3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/vishrayne\",\"html_url\":\"https://github.com/vishrayne\",\"followers_url\":\"https://api.github.com/users/vishrayne/followers\",\"following_url\":\"https://api.github.com/users/vishrayne/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/vishrayne/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/vishrayne/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/vishrayne/subscriptions\",\"organizations_url\":\"https://api.github.com/users/vishrayne/orgs\",\"repos_url\":\"https://api.github.com/users/vishrayne/repos\",\"events_url\":\"https://api.github.com/users/vishrayne/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/vishrayne/received_events\",\"type\":\"User\",\"site_admin\":false},\"private\":false,\"html_url\":\"https://github.com/vishrayne/android-application-pause\",\"description\":\"Inspired from https://github.com/curioustechizen/android-app-pause. Just playing around with it\",\"fork\":false,\"url\":\"https://api.github.com/repos/vishrayne/android-application-pause\",\"forks_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/forks\",\"keys_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/teams\",\"hooks_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/hooks\",\"issue_events_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/events\",\"assignees_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/tags\",\"blobs_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/languages\",\"stargazers_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/stargazers\",\"contributors_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/contributors\",\"subscribers_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/subscribers\",\"subscription_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/subscription\",\"commits_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/merges\",\"archive_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/downloads\",\"issues_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/deployments\",\"created_at\":\"2014-08-26T14:27:47Z\",\"updated_at\":\"2014-08-26T14:28:23Z\",\"pushed_at\":\"2014-08-26T14:29:44Z\",\"git_url\":\"git://github.com/vishrayne/android-application-pause.git\",\"ssh_url\":\"git@github.com:vishrayne/android-application-pause.git\",\"clone_url\":\"https://github.com/vishrayne/android-application-pause.git\",\"svn_url\":\"https://github.com/vishrayne/android-applicationpause\",\"homepage\":null,\"size\":224,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"Java\",\"has_issues\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"open_issues_count\":0,\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"master\"}, {\"id\":23354546,\"name\":\"android-application-pause\",\"full_name\":\"vishrayne/android-application-pause\",\"owner\":{\"login\":\"vishrayne\",\"id\":985709,\"avatar_url\":\"https://avatars.githubusercontent.com/u/985709?v=3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/vishrayne\",\"html_url\":\"https://github.com/vishrayne\",\"followers_url\":\"https://api.github.com/users/vishrayne/followers\",\"following_url\":\"https://api.github.com/users/vishrayne/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/vishrayne/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/vishrayne/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/vishrayne/subscriptions\",\"organizations_url\":\"https://api.github.com/users/vishrayne/orgs\",\"repos_url\":\"https://api.github.com/users/vishrayne/repos\",\"events_url\":\"https://api.github.com/users/vishrayne/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/vishrayne/received_events\",\"type\":\"User\",\"site_admin\":false},\"private\":false,\"html_url\":\"https://github.com/vishrayne/android-application-pause\",\"description\":\"Inspired from https://github.com/curioustechizen/android-app-pause. Just playing around with it\",\"fork\":false,\"url\":\"https://api.github.com/repos/vishrayne/android-application-pause\",\"forks_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/forks\",\"keys_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/teams\",\"hooks_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/hooks\",\"issue_events_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/events\",\"assignees_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/tags\",\"blobs_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/languages\",\"stargazers_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/stargazers\",\"contributors_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/contributors\",\"subscribers_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/subscribers\",\"subscription_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/subscription\",\"commits_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/merges\",\"archive_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/downloads\",\"issues_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/vishrayne/android-application-pause/deployments\",\"created_at\":\"2014-08-26T14:27:47Z\",\"updated_at\":\"2014-08-26T14:28:23Z\",\"pushed_at\":\"2014-08-26T14:29:44Z\",\"git_url\":\"git://github.com/vishrayne/android-application-pause.git\",\"ssh_url\":\"git@github.com:vishrayne/android-application-pause.git\",\"clone_url\":\"https://github.com/vishrayne/android-application-pause.git\",\"svn_url\":\"https://github.com/vishrayne/android-applicationpause\",\"homepage\":null,\"size\":224,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"Java\",\"has_issues\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"open_issues_count\":0,\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"master\"}]";
        List<GithubRepo> githubRepos = new Gson().fromJson(mock, new TypeToken<List<GithubRepo>>() {
        }.getType());
        showList(githubRepos);
    }

    private void showList(final List<GithubRepo> githubRepos) {
//        GithubRepoAdapter githubRepoAdapter = new GithubRepoAdapter(this, repos);
//        Log.d(TAG, "adapter count=" + githubRepoAdapter.getCount());
//
////        String[] items = new String[repos.size()];
////        for (int i = 0; i < repos.size(); i++)
////            items[i] = repos.get(i).url;
////        listvw.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items));
//
//        listvw.setAdapter(githubRepoAdapter);
//    }
//
//

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        GithubRepoAdapter githubRepoAdapter = new GithubRepoAdapter(githubRepos);
        recyclerView.setAdapter(githubRepoAdapter);

        githubRepoAdapter.setOnItemClickListener(new GithubRepoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(ShowDetailsActivity.this, "GITHUBREPO:" + githubRepos.get(position).getname(), Toast.LENGTH_LONG).show();
            }
        });
    }
}