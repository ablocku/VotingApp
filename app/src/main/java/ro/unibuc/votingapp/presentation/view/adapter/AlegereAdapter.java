package ro.unibuc.votingapp.presentation.view.adapter;

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
import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.presentation.view.RecyclerViewActivity;

public final class AlegereAdapter extends RecyclerView.Adapter < AlegereAdapter.AlegereViewHolder > {

    private List < Alegere > mAlegere;
    private final LayoutInflater mInflater;
    private View itemView;
    private final Context context;
    private final boolean setOnClickListenerOnViewCards;

    public AlegereAdapter( Context context, boolean setOnClickListenerOnViewCards ) {
        mInflater = LayoutInflater.from( context );
        this.context = context;
        this.setOnClickListenerOnViewCards = setOnClickListenerOnViewCards;
    }

    @NonNull
    @Override
    public AlegereViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        itemView = mInflater.inflate( R.layout.contact_item, parent, false );
        return new AlegereViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull AlegereViewHolder alegereViewHolder, int i ) {
        if ( mAlegere != null ) {
            Alegere alegere = mAlegere.get( i );
            alegereViewHolder.mTextViewName.setText( alegere.getData() );
            alegereViewHolder.mTextViewResult.setText( alegere.getIdAlegere() );
            alegereViewHolder.mTextViewType.setText( alegere.getTipVot() );
            alegereViewHolder.mTextViewTotalPoints.setText( alegere.getTitlu() );

            alegereViewHolder.mTextViewName.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            alegereViewHolder.mTextViewType.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            alegereViewHolder.mTextViewResult.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            alegereViewHolder.mTextViewTotalPoints.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            alegereViewHolder.mCard.setCardBackgroundColor( itemView.getResources().getColor( R.color.colorPrimary, context.getTheme() ) );


            if ( setOnClickListenerOnViewCards ) {
                alegereViewHolder.mCard.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {
                        Intent intent = new Intent( context, RecyclerViewActivity.class );
                        Bundle bundle = new Bundle();
                        bundle.putString( "specificLocation", alegere.getIdLocatie() );
                        bundle.putString( "tipVot", alegere.getTipVot() );
                        bundle.putString( "idAlegere", alegere.getIdAlegere() );
                        intent.putExtras( bundle ); //Put your id to your next Intent
                        context.startActivity( intent );//cream o noua activitate pt utilizatorul specific
                    }
                } );
            }
        } else {
            alegereViewHolder.mTextViewName.setText( R.string.noText );
            alegereViewHolder.mTextViewResult.setText( R.string.noText );
            alegereViewHolder.mTextViewType.setText( R.string.noText );
            alegereViewHolder.mTextViewTotalPoints.setText( R.string.noText );
        }
    }

    @Override
    public int getItemCount() {
        if ( mAlegere != null )
            return mAlegere.size();
        else
            return 0;
    }

    public void setGames( List < Alegere > locatii ) {
        mAlegere = locatii;
        notifyDataSetChanged();
    }

    static final class AlegereViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewName;
        private final TextView mTextViewResult;
        private final TextView mTextViewType;
        private final TextView mTextViewTotalPoints;
        private final CardView mCard;

        private AlegereViewHolder( @NonNull View itemView ) {
            super( itemView );

            mTextViewName = itemView.findViewById( R.id.textview_name );
            mTextViewResult = itemView.findViewById( R.id.textview_result );
            mTextViewType = itemView.findViewById( R.id.textview_gameType );
            mTextViewTotalPoints = itemView.findViewById( R.id.textView_totalPoints );
            mCard = itemView.findViewById( R.id.cardViewResults );
        }
    }
}
