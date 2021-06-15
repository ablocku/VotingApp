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
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.presentation.view.RecyclerViewActivity;

public final class LocationAdapter extends RecyclerView.Adapter < LocationAdapter.LocationViewHolder > {

    private List < Locatie > mLocatie;
    private final LayoutInflater mInflater;
    private View itemView;
    private final Context context;

    public LocationAdapter( Context context ) {
        mInflater = LayoutInflater.from( context );
        this.context = context;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        itemView = mInflater.inflate( R.layout.contact_item, parent, false );
        return new LocationViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull LocationViewHolder locationViewHolder, int i ) {
        if ( mLocatie != null ) {
            Locatie locatie = mLocatie.get( i );
            locationViewHolder.mTextViewName.setText( locatie.getAdresa() );
            locationViewHolder.mTextViewResult.setText( locatie.getIdLocatie() );
            locationViewHolder.mTextViewType.setText( locatie.getOras() );
            locationViewHolder.mTextViewTotalPoints.setText( locatie.getTara() );

            locationViewHolder.mTextViewName.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            locationViewHolder.mTextViewType.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            locationViewHolder.mTextViewResult.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            locationViewHolder.mTextViewTotalPoints.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            locationViewHolder.mCard.setCardBackgroundColor( itemView.getResources().getColor( R.color.colorPrimary, context.getTheme() ) );


            locationViewHolder.mCard.setOnClickListener( view -> {
                Intent intent = new Intent( context, RecyclerViewActivity.class );
                Bundle bundle = new Bundle();
                bundle.putString( "tip", "orice" );
                bundle.putString( "specificLocation", locatie.getIdLocatie() );
                intent.putExtras( bundle ); //Put your id to your next Intent
                context.startActivity( intent );//cream o noua activitate pt utilizatorul specific
            } );

        } else {
            locationViewHolder.mTextViewName.setText( R.string.noText );
            locationViewHolder.mTextViewResult.setText( R.string.noText );
            locationViewHolder.mTextViewType.setText( R.string.noText );
            locationViewHolder.mTextViewTotalPoints.setText( R.string.noText );
        }
    }

    @Override
    public int getItemCount() {
        if ( mLocatie != null )
            return mLocatie.size();
        else
            return 0;
    }

    public void setGames( List < Locatie > locatii ) {
        mLocatie = locatii;
        notifyDataSetChanged();
    }

    static final class LocationViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewName;
        private final TextView mTextViewResult;
        private final TextView mTextViewType;
        private final TextView mTextViewTotalPoints;
        private final CardView mCard;

        private LocationViewHolder( @NonNull View itemView ) {
            super( itemView );

            mTextViewName = itemView.findViewById( R.id.textview_name );
            mTextViewResult = itemView.findViewById( R.id.textview_result );
            mTextViewType = itemView.findViewById( R.id.textview_gameType );
            mTextViewTotalPoints = itemView.findViewById( R.id.textView_totalPoints );
            mCard = itemView.findViewById( R.id.cardViewResults );
        }
    }
}
