package com.prescripxion.www.prescripxion2module;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;


public class ProfileActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;
    Uri imageUri = Uri.parse("http://i.imgur.com/VIlcLfg.jpg");

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsing;
    private ImageView coverImage;
    private FrameLayout framelayoutTitle;
    private LinearLayout linearlayoutTitle;
    private Toolbar toolbar;
    private TextView textviewTitle;
    private SimpleDraweeView avatar;

    /**
     * Find the Views in the layout
     * Auto-created on 2016-03-03 11:32:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        appbar = (AppBarLayout)findViewById( R.id.appbar );
        collapsing = (CollapsingToolbarLayout)findViewById( R.id.ctoolBar );
        //coverImage = (ImageView)findViewById( R.id.circleImageView );
        framelayoutTitle = (FrameLayout)findViewById( R.id.framelayout_title );
        linearlayoutTitle = (LinearLayout)findViewById( R.id.linearlayout_title );
        toolbar = (Toolbar)findViewById( R.id.toolbar );
        textviewTitle = (TextView)findViewById( R.id.textview_title );
        avatar = (SimpleDraweeView)findViewById(R.id.avatar);
    }


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] profile = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_profile);
        findViews();

        /*ImageView imageView = (ImageView) findViewById(R.id.circleImageView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);*/

        toolbar.setTitle("");
        appbar.addOnOffsetChangedListener(this);

        setSupportActionBar(toolbar);
        startAlphaAnimation(textviewTitle, 0, View.INVISIBLE);

        avatar.setImageURI(imageUri);
        //coverImage.setImageResource(R.drawable.profile_pic);

        /*SharedPreferences sharedPreferences1 = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString("userName", "Mr. Someone");
        editor.putString("userMobile", "01234567890");
        editor.putString("userAge", "20");
        editor.putString("userGender", "Male");
        editor.putString("userBloodGroup", "B+");
        editor.putString("userAddress", "ECE Building, Palashi, BUET, Dhaka.");
        editor.apply();*/

        SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
        String[] info = new String[6];
        info[0]=sharedPreferences.getString("userName","");
        info[1]=sharedPreferences.getString("userAddress","");
        info[2]=sharedPreferences.getString("userMobile","");
        info[3]=sharedPreferences.getString("userBloodGroup","");
        info[4]=sharedPreferences.getString("userGender","");
        info[5]=sharedPreferences.getString("userAge","")+" Years";

        if(sharedPreferences.getBoolean("userImageExists", false)){
            imageUri = Uri.parse(sharedPreferences.getString("userImagePath",""));
            avatar.setImageURI(imageUri);
        }


        TextView textView1 = (TextView) findViewById(R.id.textview1);
        textView1.setText(info[0]);
        TextView textView2 = (TextView) findViewById(R.id.textview2);
        textView2.setText(info[2]);
        TextView textView3 = (TextView) findViewById(R.id.textview3);
        textView3.setText(info[4]);
        TextView textView4 = (TextView) findViewById(R.id.textview4);
        textView4.setText(info[3]);
        TextView textView5 = (TextView) findViewById(R.id.textview5);
        textView5.setText(info[5]);
        TextView textView6 = (TextView) findViewById(R.id.textview6);
        textView6.setText(info[1]);
        /*CollapsingToolbarLayout cToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctoolBar);
        cToolbar.setTitle(getString(R.string.user_name));*/

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(textviewTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(textviewTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearlayoutTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearlayoutTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }



    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    //TODO: GO TO PROFILE ACTIVITY;
                    return true;
                case R.id.navigation_home:
                    //TODO: GO TO HOME ACTIVITY;
                    Intent mainIntent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    return true;
                case R.id.navigation_medication:
                    //TODO: GO TO MEDICINE ACTIVITY;
                    Intent medicineIntent = new Intent(ProfileActivity.this, MedicationActivity.class);
                    startActivity(medicineIntent);
                    finish();
                    return true;
            }
            return false;
        }
    };
}

