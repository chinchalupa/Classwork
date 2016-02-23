package edu.rosehulman.moviequotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockDialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt Boutell on 12/15/2015.
 */
public class MovieQuoteAdapter extends RecyclerView.Adapter<MovieQuoteAdapter.ViewHolder> {

    public static final String QUOTES_PATH = "https://wrightjt-movie-quotes.firebaseio.com/quotes";
    private List<MovieQuote> mMovieQuotes;
    private Callback mCallback;
    private Firebase mMovieQuotesRef;


    public MovieQuoteAdapter(Callback callback, Context context) {
        mCallback = callback;
        mMovieQuotes = new ArrayList<>();
        Firebase.setAndroidContext(context);
        mMovieQuotesRef = new Firebase(QUOTES_PATH);
        mMovieQuotesRef.addChildEventListener(new QuotesChildEventListener());
    }

    class QuotesChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            MovieQuote quote = dataSnapshot.getValue(MovieQuote.class);
            quote.setKey(dataSnapshot.getKey());
            mMovieQuotes.add(0, quote);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            MovieQuote newMovieQuote = dataSnapshot.getValue(MovieQuote.class);
            for(MovieQuote movieQuote : mMovieQuotes) {
                if (movieQuote.getKey().equals(key)) {
                    movieQuote.setValues(newMovieQuote);
                    break;
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for(MovieQuote movieQuote : mMovieQuotes) {
                if(key.equals(movieQuote.getKey())) {
                    mMovieQuotes.remove(movieQuote);
                    notifyDataSetChanged();
                    break;
                }
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
            Log.e("Permission error", "You do not have permission to access this database");
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_quote_row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MovieQuote movieQuote = mMovieQuotes.get(position);
        holder.mQuoteTextView.setText(movieQuote.getQuote());
        holder.mMovieTextView.setText(movieQuote.getMovie());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onEdit(movieQuote);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(mMovieQuotes.get(position));
                return true;
            }
        });
    }

    public void remove(MovieQuote movieQuote) {
        //TODO: Remove the next line(s) and use Firebase instead
        mMovieQuotesRef.child(movieQuote.getKey()).removeValue();
//        mMovieQuotes.remove(movieQuote);
//        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mMovieQuotes.size();
    }

    public void add(MovieQuote movieQuote) {
//        TODO: Remove the next line(s) and use Firebase instead
        mMovieQuotesRef.push().setValue(movieQuote);
//        mMovieQuotes.add(0, movieQuote);
//        notifyDataSetChanged();
    }

    public void update(MovieQuote movieQuote, String newQuote, String newMovie) {
        //TODO: Remove the next line(s) and use Firebase instead
        movieQuote.setQuote(newQuote);
        movieQuote.setMovie(newMovie);
        mMovieQuotesRef.child(movieQuote.getKey()).setValue(movieQuote);
//        notifyDataSetChanged();
    }

    public interface Callback {
        public void onEdit(MovieQuote movieQuote);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mQuoteTextView;
        private TextView mMovieTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mQuoteTextView = (TextView) itemView.findViewById(R.id.quote_text);
            mMovieTextView = (TextView) itemView.findViewById(R.id.movie_text);
        }
    }


}
