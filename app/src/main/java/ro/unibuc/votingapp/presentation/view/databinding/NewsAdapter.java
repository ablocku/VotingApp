package ro.unibuc.votingapp.presentation.view.databinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.presentation.view.NewsActivity;

public final class NewsAdapter extends RecyclerView.Adapter < NewsAdapter.NewsViewHolder > {

    private List < Stire > mStire;
    private final LayoutInflater mInflater;
    private View itemView;
    private final Context context;

    public NewsAdapter( Context context ) {
        mInflater = LayoutInflater.from( context );
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        itemView = mInflater.inflate( R.layout.contact_item, parent, false );
        return new NewsViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull NewsViewHolder newsViewHolder, int i ) {
        if ( mStire != null ) {
            Stire stire = mStire.get( i );
            newsViewHolder.mTextViewName.setText( String.format( "Titlu stire: %s", stire.getTitluStire() ) );
            newsViewHolder.mTextViewResult.setText( String.format( "Data stire: %s", stire.getDataStire() ) );
            newsViewHolder.mTextViewType.setText( String.format( "Id alegere: %s", stire.getIdAlegere() ) );
            newsViewHolder.mTextViewTotalPoints.setText( String.format( "Continut: %s", stire.getContinut() ) );

            newsViewHolder.mTextViewName.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            newsViewHolder.mTextViewType.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            newsViewHolder.mTextViewResult.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            newsViewHolder.mTextViewTotalPoints.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            newsViewHolder.mCard.setCardBackgroundColor( itemView.getResources().getColor( R.color.colorPrimary, context.getTheme() ) );


            newsViewHolder.mCard.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View view ) {
                    Intent intent = new Intent( context, NewsActivity.class );
                    Bundle bundle = new Bundle();
                    bundle.putString( "titlu", stire.getTitluStire() );
                    bundle.putString( "content", stire.getContinut() );

                    intent.putExtras( bundle ); //Put your id to your next Intent
                    context.startActivity( intent );//cream o noua activitate pt utilizatorul specific
                }
            } );
        }
    }

    @Override
    public int getItemCount() {
        if ( mStire != null )
            return mStire.size();
        else
            return 0;
    }

    public void setGames( List < Stire > stiri ) {
        mStire = stiri;
        notifyDataSetChanged();
    }

    static final class NewsViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewName;
        private final TextView mTextViewResult;
        private final TextView mTextViewType;
        private final TextView mTextViewTotalPoints;
        private final CardView mCard;

        private NewsViewHolder( @NonNull View itemView ) {
            super( itemView );

            mTextViewName = itemView.findViewById( R.id.textview_name );
            mTextViewResult = itemView.findViewById( R.id.textview_result );
            mTextViewType = itemView.findViewById( R.id.textview_gameType );
            mTextViewTotalPoints = itemView.findViewById( R.id.textView_totalPoints );
            mCard = itemView.findViewById( R.id.cardViewResults );
        }
    }
}
