package ro.unibuc.votingapp.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

//Because our ViewModel has a parameter in the constructor, we have to use this class with ViewModelProvider
public final class VotingAppViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public VotingAppViewModelFactory(Application application ) {
        this.application = application;
    }

    @NonNull
    @Override
    public < T extends ViewModel > T create( @NonNull Class < T > modelClass ) {
        return Objects.requireNonNull( modelClass.cast( new VotingAppViewModel( application ) ) );
    }
}
