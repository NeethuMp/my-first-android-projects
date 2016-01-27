package com.example.neethu.githubsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by neethu on 25/1/16.
 */
public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.ViewHolder> {

    //    public GithubRepoAdapter(Context context, List<GithubRepo> repos) {
//        super(context, R.layout.text, repos);
//        this.context = context;
//        this.repos = repos;
//        this.mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
    private static OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<GithubRepo> githubRepos;
    private Context mContext;

    public GithubRepoAdapter(List<GithubRepo> githubRepos) {
        this.githubRepos = githubRepos;
    }


//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = mInflator.inflate(R.layout.text, parent, false);
//            ViewHolder viewHolder = new ViewHolder();
//            viewHolder.repoName = (TextView) convertView.findViewById(R.id.rname);
//            viewHolder.repoURL = (TextView) convertView.findViewById(R.id.rurl);
//            convertView.setTag(viewHolder);
//        }
//
//        ViewHolder holder = (ViewHolder) convertView.getTag();
//        GithubRepo repo = repos.get(position);
//        holder.repoName.setText(repo.name);
//        holder.repoURL.setText(repo.url);
//        return convertView;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.text, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GithubRepo repo = githubRepos.get(position);
        viewHolder.repoName.setText(repo.getname());
        viewHolder.repoURL.setText(repo.getUrl());

    }

    @Override
    public int getItemCount() {
        return githubRepos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView repoName;
        TextView repoURL;


        public ViewHolder(final View itemLayoutView) {
            super(itemLayoutView);

            repoName = (TextView) itemLayoutView.findViewById(R.id.rname);
            repoURL = (TextView) itemLayoutView.findViewById(R.id.rurl);
            itemLayoutView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "on click");
                    if(listener!=null)
                        listener.onItemClick(itemLayoutView, getLayoutPosition());
                }
            });
        }
    }
}
