package ro.unibuc.votingapp.presentation.view.databinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Timestamp;
import java.util.List;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.domain.VoteDependencyProvider;

public final class CandidatAdapter extends RecyclerView.Adapter < CandidatAdapter.CandidatViewHolder > {

    private List < Candidat > mCandidat;
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
        if ( mCandidat != null ) {
            Candidat candidat = mCandidat.get( i );
            candidatViewHolder.mTextViewName.setText( String.format( "id alegere:%s", candidat.getIdAlegere() ) );
            candidatViewHolder.mTextViewResult.setText( String.format( "id candidat: %s", candidat.getIdCandidat() ) );
            candidatViewHolder.mTextViewType.setText( String.format( "Nume candidat: %s", candidat.getNumeCandidat() ) );
            candidatViewHolder.mTextViewTotalPoints.setText( String.format( "Nr voturi: %s", candidat.getObservatii() ) );

            candidatViewHolder.mTextViewName.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mTextViewType.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mTextViewResult.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mTextViewTotalPoints.setTextColor( itemView.getResources().getColor( R.color.colorAccent, context.getTheme() ) );
            candidatViewHolder.mCard.setCardBackgroundColor( itemView.getResources().getColor( R.color.colorPrimary, context.getTheme() ) );


            candidatViewHolder.mCard.setOnClickListener( view -> {
                VoteDependencyProvider voteDependencyProvider = new VoteDependencyProvider( context );
                String timeStamp = new Timestamp( System.currentTimeMillis() ).toString();
                voteDependencyProvider.provideUseCase().insertVot( new VotAnonim( candidat.getIdAlegere(), candidat.getIdCandidat(), timeStamp ) );
            } );

        } else {
            candidatViewHolder.mTextViewName.setText( R.string.noText );
            candidatViewHolder.mTextViewResult.setText( R.string.noText );
            candidatViewHolder.mTextViewType.setText( R.string.noText );
            candidatViewHolder.mTextViewTotalPoints.setText( R.string.noText );
        }
    }

    @Override
    public int getItemCount() {
        if ( mCandidat != null )
            return mCandidat.size();
        else
            return 0;
    }

    public void setGames( List < Candidat > candidati ) {
        mCandidat = candidati;
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
