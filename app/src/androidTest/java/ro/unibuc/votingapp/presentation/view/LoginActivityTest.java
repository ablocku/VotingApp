package ro.unibuc.votingapp.presentation.view;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ro.unibuc.votingapp.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith ( AndroidJUnit4.class )
public class LoginActivityTest {

    @Rule
    public ActivityTestRule < LoginActivity > mActivityTestRule = new ActivityTestRule <>( LoginActivity.class );

    @Test
    public void loginActivityTest() {
        ViewInteraction appCompatEditText = onView(
                allOf( withId( R.id.email ),
                        childAtPosition(
                                childAtPosition(
                                        withClassName( is( "android.widget.ScrollView" ) ),
                                        0 ),
                                1 ) ) );
        appCompatEditText.perform( scrollTo(), replaceText( "reksio.imaginatie@gmail.com" ), closeSoftKeyboard() );

        ViewInteraction appCompatEditText2 = onView(
                allOf( withId( R.id.password ),
                        childAtPosition(
                                childAtPosition(
                                        withClassName( is( "android.widget.ScrollView" ) ),
                                        0 ),
                                2 ) ) );
        appCompatEditText2.perform( scrollTo(), replaceText( "tav713" ), closeSoftKeyboard() );

        ViewInteraction appCompatButton = onView(
                allOf( withId( R.id.logInButton ), withText( "Intrati" ),
                        childAtPosition(
                                childAtPosition(
                                        withClassName( is( "android.widget.ScrollView" ) ),
                                        0 ),
                                3 ) ) );
        appCompatButton.perform( scrollTo(), click() );
    }

    private static Matcher < View > childAtPosition(
            final Matcher < View > parentMatcher, final int position ) {

        return new TypeSafeMatcher < View >() {
            @Override
            public void describeTo( Description description ) {
                description.appendText( "Child at position " + position + " in parent " );
                parentMatcher.describeTo( description );
            }

            @Override
            public boolean matchesSafely( View view ) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches( parent )
                        && view.equals( ( ( ViewGroup ) parent ).getChildAt( position ) );
            }
        };
    }
}
