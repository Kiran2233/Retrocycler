package com.example.retrocycler;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.MyViewHolder> {
    private List<Article> articleArrayList;
    private Activity activity;
    private final OnItemClickListener listener;
public MainArticleAdapter(Activity activity,List<Article> articleArrayList, OnItemClickListener listener){
    this.articleArrayList=articleArrayList;
    this.activity=activity;
    this.listener=listener;
}



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("onbindview","hihigh");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_main_article_adapter, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
viewHolder.bind(articleArrayList.get(i),listener);
        Log.d("onbindview",articleArrayList.get(i).getTitle());
   viewHolder.title.setText(articleArrayList.get(i).getTitle());
   viewHolder.description.setText(articleArrayList.get(i).getDescription());
   viewHolder.author.setText(articleArrayList.get(i).getAuthor());
   viewHolder.publishedAt.setText(articleArrayList.get(i).getPublishedAt());
        Picasso.with(activity).load(articleArrayList.get(i).getUrlToImage()).resize(300,200).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }
public class MyViewHolder extends RecyclerView.ViewHolder{
                ImageView img;
                TextView title,description,author,publishedAt;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d("onviewholder","entered");
        title= itemView.findViewById(R.id.article_adapter_tv_title);
        description=itemView.findViewById(R.id.article_adapter_tv_description);
        author=itemView.findViewById(R.id.author);
        publishedAt=itemView.findViewById(R.id.time);
        img=itemView.findViewById(R.id.img);

    }
    public void bind(final Article item,final OnItemClickListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item,getLayoutPosition());
            }
        });
    }

}
    public interface OnItemClickListener {
        void onItemClick(Article item,int position);
    }


}