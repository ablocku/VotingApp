package ro.unibuc.votingapp.presentation.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.core.content.FileProvider;

import ro.unibuc.votingapp.VotingApplication;
import ro.unibuc.votingapp.R;

import static android.os.Environment.getExternalStoragePublicDirectory;

public final class CreditsActivity extends AppCompatActivity {
    private final static String github = "https://github.com/ReksioCroft/";
    private final static String google = "https://events.withgoogle.com/atelierul-digital-pentru-programatori/";


    private Button captureImageBtn, detectTextBtn;
    private ImageView imageView;
    private TextView textView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    String currentPhotoPath;
    String text;

    private File createImageFile() throws IOException {
        // https://stackoverflow.com/questions/51115991/action-image-capture-returns-poor-image-quality-bitmap-where-can-i-get-the-hi-r
        // https://developer.android.com/training/camera/photobasics
        // Create an image file name
        String timeStamp = new SimpleDateFormat( "yyyyMMdd_HHmmss" ).format( new Date() );
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // open the phone camera to make the photo
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        // Ensure that there's a camera activity to handle the intent
        if ( takePictureIntent.resolveActivity( getPackageManager() ) != null ) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch ( IOException ex ) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if ( photoFile != null ) {
                Uri photoURI = FileProvider.getUriForFile( this,
                        "com.example.android.fileprovider",
                        photoFile );
                takePictureIntent.putExtra( MediaStore.EXTRA_OUTPUT, photoURI );
                startActivityForResult( takePictureIntent, REQUEST_IMAGE_CAPTURE );
            }
        }

    }

    //
    private void displayTextFromImage( Text firebaseVisionText ) {
        List < Text.TextBlock > blockList = firebaseVisionText.getTextBlocks();
        if ( blockList.size() == 0 ) {
            Toast.makeText( this, "The text isn't clear. Try again.", Toast.LENGTH_LONG ).show();
        } else {
            text = "";
            for ( Text.TextBlock block : firebaseVisionText.getTextBlocks() ) {
                text = text.concat( block.getText() );
            }
            textView.setText( text );
            Log.d( "De debug", text );
        }
    }

    private void detectTextFromImage() {
        if ( imageBitmap == null ) {
            Toast.makeText( this, "You need to take a photo first.", Toast.LENGTH_LONG ).show();
        } else {
            TextRecognizer textRecognizer = TextRecognition.getClient( TextRecognizerOptions.DEFAULT_OPTIONS );
            InputImage image = InputImage.fromBitmap( imageBitmap, 0 );
//            FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
//            FirebaseVisionTextRecognizer firebaseVisionTextDetector = FirebaseVision.getInstance().getCloudTextRecognizer();

            textRecognizer.process( image ).addOnSuccessListener( new OnSuccessListener < Text >() {
                @Override
                public void onSuccess( Text visionText ) {
                    displayTextFromImage( visionText );
                }
            } )
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure( @NonNull Exception e ) {
                                    Toast.makeText( CreditsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT ).show();
                                    Log.d( "Error: ", e.getMessage() );
                                }
                            } );

        }

    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_credits );

        captureImageBtn = findViewById( R.id.capture_image );
        detectTextBtn = findViewById( R.id.detect_text_image );
        imageView = findViewById( R.id.image_view );
        textView = findViewById( R.id.text_display );
        captureImageBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                dispatchTakePictureIntent();
                textView.setText( "" );
            }
        } );

        detectTextBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                detectTextFromImage();
            }
        } );
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent( Intent.ACTION_MEDIA_SCANNER_SCAN_FILE );
        File f = new File( currentPhotoPath );
        Uri contentUri = Uri.fromFile( f );
        mediaScanIntent.setData( contentUri );
        this.sendBroadcast( mediaScanIntent );
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile( currentPhotoPath, bmOptions );

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max( 1, Math.min( photoW / targetW, photoH / targetH ) );

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        imageBitmap = BitmapFactory.decodeFile( currentPhotoPath, bmOptions );
        imageView.setImageBitmap( imageBitmap );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );
        if ( requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ) {
            galleryAddPic();
            setPic();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VotingApplication.getApplication().removeActivity( this );
    }
}
