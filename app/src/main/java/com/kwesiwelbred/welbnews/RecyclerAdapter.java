package com.kwesiwelbred.welbnews;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;

    private final ArrayList<NewsData> items;

    public RecyclerAdapter(Context context, ArrayList<NewsData> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.newsdata, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        final NewsData item = items.get(position);
        String title = item.getTitle();
        String author = item.getAuthor();
        String date = item.getDate();
        String urlToImage = item.getUrlToImage();
        final String url = item.getUrl();



        if(title!=null)
            holder.editTitle.setText(title);
        if(author!=null)
            holder.editauthor.setText(String.format("Author  %s", author));
        if(date!=null)
            holder.editdate.setText(String.format("%s%s", context.getString(R.string.published_date), date));
        if(urlToImage!=null)
            Picasso.get().load(urlToImage).into(holder.editUrlImage);


        holder.mybutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(context,DetailsActivity.class);
                intent.putExtra("url",url);
                context.startActivity(intent);
            }
        });

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView editTitle,editauthor,editdate;
        ImageView editUrlImage;
        Button mybutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editTitle = itemView.findViewById(R.id.tittle1);
            editUrlImage = itemView.findViewById(R.id.image1);
            editauthor = itemView.findViewById(R.id.author);
            editdate = itemView.findViewById(R.id.date);
            mybutton = itemView.findViewById(R.id.details);

        }
    }
}
