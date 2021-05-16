package ro.unibuc.votingapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ro.unibuc.votingapp.VotingApplication;
import ro.unibuc.votingapp.R;

public final class RegisterActivity extends AppCompatActivity {
    private EditText email, password;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        VotingApplication.getApplication().addActivity( this );

        TextView register_notLogedIn = findViewById( R.id.logInFromRegister );
        register_notLogedIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( RegisterActivity.this, LoginActivity.class );
                startActivity( intent );
            }
        } );

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById( R.id.emailReg );
        password = findViewById( R.id.passwordReg );

        Button registrationButton = findViewById( R.id.registrationButton );
        registrationButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String emailString = email.getText().toString().trim();
                String passwordString = password.getText().toString().trim();

                if ( emailString.isEmpty() ) {
                    email.setError( "Email is required" );
                    email.requestFocus();
                    return;
                }

                if ( !Patterns.EMAIL_ADDRESS.matcher( emailString ).matches() ) {
                    email.setError( "Invalid email" );
                    email.requestFocus();
                    return;
                }

                if ( passwordString.isEmpty() ) {
                    password.setError( "Password is required" );
                    password.requestFocus();
                    return;
                }

                if ( passwordString.length() < 6 ) {
                    password.setError( "Password should be atleast 6 character long" );
                    password.requestFocus();
                    return;
                }

                mFirebaseAuth.createUserWithEmailAndPassword( emailString, passwordString ).addOnCompleteListener( RegisterActivity.this, new OnCompleteListener < AuthResult >() {
                    @Override
                    public void onComplete( @NonNull Task < AuthResult > task ) {
                        if ( !task.isSuccessful() ) {
                            Toast.makeText( RegisterActivity.this, "Registration failed. Please try again", Toast.LENGTH_SHORT ).show();
                        } else {
                            finish();
                        }
                    }
                } );

            }

        } );
    }

    @Override
    protected void onStop() {
        super.onStop();
        email = findViewById( R.id.emailReg );
        email.setText( "" );
        password = findViewById( R.id.passwordReg );
        password.setText( "" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VotingApplication.getApplication().removeActivity( this );
    }
}
