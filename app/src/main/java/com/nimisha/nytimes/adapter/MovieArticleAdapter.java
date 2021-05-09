package com.nimisha.nytimes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nimisha.nytimes.R;
import com.nimisha.nytimes.model.Article;

import java.util.ArrayList;

public class MovieArticleAdapter extends RecyclerView.Adapter<MovieArticleAdapter.ViewHolder> implements Filterable {
    private Context context;
    ArrayList<Article> articleArrayList;
    ArrayList<Article> articleFiltered;

    public MovieArticleAdapter(Context context, ArrayList<Article> articleArrayList) {
        this.context = context;
        if(articleArrayList==null) {
            this.articleArrayList = articleArrayList;
            this.articleFiltered = articleArrayList;
            notifyItemChanged(0, articleFiltered.size());
        }
        else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return articleArrayList.size();
                }

                @Override
                public int getNewListSize() {
                    return articleArrayList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MovieArticleAdapter.this.articleArrayList.get(oldItemPosition).getSource() ==
                            articleArrayList.get(newItemPosition).getSource();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Article newMovie = MovieArticleAdapter.this.articleArrayList.get(oldItemPosition);
                    Article oldMovie = articleArrayList.get(newItemPosition);
                    return newMovie.getLead_paragraph() == oldMovie.getLead_paragraph() ;
                }
            });
            this.articleArrayList = articleArrayList;
            this.articleFiltered = articleArrayList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row_movie_article,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Article article=articleFiltered.get(i);
        viewHolder.tvTitle.setText(article.getSource());
        //  viewHolder.tvAuthorAndPublishedAt.setText("-"+article.getAuthor() +" | "+"Published At: "+article.getPublishedAt());
        viewHolder.tvDescription.setText(article.getLead_paragraph());
        String value = article.getArticleThumbnailUrl();
        Glide.with(context)
                .load(value)
                .into(viewHolder.imgViewCover);
    }
    @Override
    public int getItemCount() {
        if(articleArrayList != null){
            return articleFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();

            if (charString.isEmpty()) {
                articleFiltered = articleArrayList;
            } else {
                ArrayList<Article> filteredList = new ArrayList<>();

                for (Article movie: articleArrayList) {
                    if (movie.getLead_paragraph().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(movie);
                    }
                    articleFiltered = filteredList;
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = articleFiltered;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            articleFiltered = (ArrayList<Article>) filterResults.values;
            if(articleFiltered.size()>0)
                notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgViewCover;
        private final TextView tvTitle;
        private final TextView tvAuthorAndPublishedAt;
        private final TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthorAndPublishedAt=(TextView) itemView.findViewById(R.id.tvAuthorAndPublishedAt);
            tvDescription=(TextView) itemView.findViewById(R.id.tvDescription);
        }
    }
}
