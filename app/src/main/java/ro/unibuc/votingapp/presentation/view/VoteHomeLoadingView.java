package ro.unibuc.votingapp.presentation.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.animation.PathInterpolatorCompat;

import ro.unibuc.votingapp.R;

public final class VoteHomeLoadingView extends View {
    private RectF rectangle;
    private Paint paint;
    private AnimatorSet animatorSet;
    private ValueAnimator rotationAnimator;
    private float sweepAngle1;
    private float sweepAngle2;
    private float sweepAngle3;
    private ValueAnimator pathAnimator;

    public VoteHomeLoadingView( Context context ) {
        super( context );
        initialize( context, null );
    }

    public VoteHomeLoadingView( Context context, @Nullable AttributeSet attrs ) {
        super( context, attrs );
        initialize( context, attrs );
    }

    public VoteHomeLoadingView( Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
        initialize( context, attrs );
    }

    private void initialize( Context context, AttributeSet attrs ) {
        TypedArray typedArray = context.obtainStyledAttributes( attrs, R.styleable.VoteHomeLoadingView );
        int valueColor = typedArray.getColor( R.styleable.VoteHomeLoadingView_loadingColor,
                ContextCompat.getColor( context, android.R.color.black ) );
        typedArray.recycle();

        rectangle = new RectF();

        paint = new Paint();
        paint.setAntiAlias( true );
        paint.setStyle( Paint.Style.STROKE );
        paint.setStrokeCap( Paint.Cap.ROUND );
        paint.setStrokeWidth( 5 );
        paint.setColor( valueColor );

        animatorSet = new AnimatorSet();

        rotationAnimator = ValueAnimator.ofFloat( 0f, 360f );
        rotationAnimator.setDuration( 1600 );
        rotationAnimator.setInterpolator( new LinearInterpolator() );
        rotationAnimator.setRepeatCount( ValueAnimator.INFINITE );
        rotationAnimator.setRepeatMode( ValueAnimator.RESTART );
        rotationAnimator.addUpdateListener( animation -> {
            float newValue = ( float ) animation.getAnimatedValue();
            setRotation( newValue );
        } );

        pathAnimator = ValueAnimator.ofFloat( 5f, 105f );
        pathAnimator.setDuration( 800 );
        pathAnimator.setInterpolator( PathInterpolatorCompat.create( 1f, 0f, 0f, 1f ) );
        pathAnimator.setRepeatCount( ValueAnimator.INFINITE );
        pathAnimator.setRepeatMode( ValueAnimator.RESTART );
        pathAnimator.addUpdateListener( animation -> {
            sweepAngle1 = sweepAngle2 = sweepAngle3 = ( float ) animation.getAnimatedValue();
            invalidate();
        } );
    }

    @Override
    protected void onRestoreInstanceState( Parcelable state ) {
        super.onRestoreInstanceState( state );
    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
        int width = MeasureSpec.getSize( widthMeasureSpec );
        int height = MeasureSpec.getSize( heightMeasureSpec );

        int minSize = Math.min( width, height );

        rectangle.set(
                getPaddingLeft(),
                getPaddingTop(),
                ( minSize - getPaddingRight() ),
                Math.abs( minSize - getPaddingBottom() )
        );

        setMeasuredDimension( minSize, minSize );

        animatorSet.cancel();
        animatorSet.playTogether( rotationAnimator, pathAnimator );
        animatorSet.start();
    }

    @Override
    protected void onDraw( Canvas canvas ) {
        super.onDraw( canvas );

        canvas.drawArc( rectangle, 0f, sweepAngle1, false, paint );
        canvas.drawArc( rectangle, 120f, sweepAngle2, false, paint );
        canvas.drawArc( rectangle, 240f, sweepAngle3, false, paint );
    }
}
