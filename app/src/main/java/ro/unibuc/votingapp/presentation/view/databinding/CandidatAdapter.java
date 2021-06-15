package ro.unibuc.votingapp.presentation.view.databinding;

import android.content.Context;
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

public final class CandidatAdapter extends RecyclerView.Adapter < CandidatAdapter.CandidatViewHolder > {

    private List < Alegere > mAlegere;
    private final LayoutInflater mInflater;
    private View itemView;
    private final Context context;

    public CandidatAdapter( Context context ) {
        mInflater = LayoutInflater.from( context );
        this.context = context;
    }

    @NonNull
    @Override
    public CandidatViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        itemView = mInflater.inflate( R.layout.contact_item, parent, false );
        return new CandidatViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull CandidatViewHolder candidatViewHolder, int i ) {
        if ( mAlegere != null ) {
            Alegere alegere = mAlegere.get( i );
            candidatViewHolder.mTextViewName.setText( alegere.getData() );
            candidatViewHolder.mTextViewResult.setText( alegere.getIdAlegere() );
            candidatViewHolder.mTextViewType.setText( alegere.getTipVot() );
            candidatViewHolder.mTextViewTotalPoints.setText( alegere.getTitlu() );

            candidatViewHolder.mTextViewName.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mTextViewType.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mTextViewResult.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mTextViewTotalPoints.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mCard.setCardBackgroundColor( itemView.getResources().getColor( R.color.colorPrimary, context.getTheme() ) );


//            candidatViewHolder.mCard.setOnClickListener( view -> {
//                Intent intent = new Intent( context, RecyclerViewActivity.class );
//                Bundle bundle = new Bundle();
//                bundle.putString( "specificLocation", alegere.getIdLocatie() );
//                bundle.putString( "tipVot", alegere.getTipVot() );
//                bundle.putString( "idAlegere", alegere.getIdAlegere() );
//                intent.putExtras( bundle ); //Put your id to your next Intent
//                context.startActivity( intent );//cream o noua activitate pt utilizatorul specific
//            } );

        } else {
            candidatViewHolder.mTextViewName.setText( R.string.noText );
            candidatViewHolder.mTextViewResult.setText( R.string.noText );
            candidatViewHolder.mTextViewType.setText( R.string.noText );
            candidatViewHolder.mTextViewTotalPoints.setText( R.string.noText );
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

    static final class CandidatViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewName;
        private final TextView mTextViewResult;
        private final TextView mTextViewType;
        private final TextView mTextViewTotalPoints;
        private final CardView mCard;

        private CandidatViewHolder( @NonNull View itemView ) {
            super( itemView );

            mTextViewName = itemView.findViewById( R.id.textview_name );
            mTextViewResult = itemView.findViewById( R.id.textview_result );
            mTextViewType = itemView.findViewById( R.id.textview_gameType );
            mTextViewTotalPoints = itemView.findViewById( R.id.textView_totalPoints );
            mCard = itemView.findViewById( R.id.cardViewResults );
        }
    }
}
