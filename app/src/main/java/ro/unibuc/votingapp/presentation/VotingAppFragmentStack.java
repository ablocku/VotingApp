package ro.unibuc.votingapp.presentation;

import androidx.fragment.app.Fragment;

import java.util.Stack;

import ro.unibuc.votingapp.presentation.fragments.OfficialPollsFragment;

public final class VotingAppFragmentStack extends Stack < Fragment > {
    private boolean containsGame = false;
    private int co = 0;

    public boolean isGameInStack() {
        return containsGame;
    }

    @Override
    public Fragment push( Fragment item ) {
        if ( item.getClass() == OfficialPollsFragment.class )
            containsGame = true;
        co++;
        return super.push( item );
    }

    @Override
    public synchronized Fragment pop() {
        if ( !this.empty() ) {
            if ( this.peek().getClass() == OfficialPollsFragment.class )
                containsGame = false;
            co--;
        }
        return super.pop();
    }

    public int getNrOfItems() {
        return co;
    }
}
