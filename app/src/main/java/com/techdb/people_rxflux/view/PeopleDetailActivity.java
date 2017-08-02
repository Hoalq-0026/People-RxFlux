package com.techdb.people_rxflux.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techdb.people_rxflux.R;
import com.techdb.people_rxflux.model.People;


public class PeopleDetailActivity extends AppCompatActivity {

    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";

    private CardView mAddressCard;

    private TextView mFullNameTextView;
    private TextView mPhoneTextView;
    private TextView mEmailTextView;

    private TextView mGenderTextView;
    private TextView mAddressTextView;
    private ImageView mPictureProfile;

    public static Intent launchDetail(Context context, People people) {
        Intent intent = new Intent(context, PeopleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mAddressCard = (CardView) findViewById(R.id.address_card);

        mFullNameTextView = (TextView) findViewById(R.id.textview_fullname);
        mPhoneTextView = (TextView) findViewById(R.id.textview_phone);
        mEmailTextView = (TextView) findViewById(R.id.textview_email);
        mGenderTextView = (TextView) findViewById(R.id.textview_gender);
        mAddressTextView = (TextView) findViewById(R.id.textview_address);
        mPictureProfile = (ImageView) findViewById(R.id.picture_profile);

        setSupportActionBar(toolbar);
        displayHomeUpEnabled();
        getExtrasFromIntent();
    }

    private void displayHomeUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

    }

    private void getExtrasFromIntent() {
        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        if (people != null) {
            people.setFullName(String.format("%s.%s %s", people.getName().getTitle(), people.getName().getFirst(), people.getName().getLast()));
            mFullNameTextView.setText(people.getFullName());
            mPhoneTextView.setText(people.getPhone());
            mEmailTextView.setText(people.getEmail());
            mGenderTextView.setText(people.getGender());

            mAddressTextView.setText(String.format("%s - %s, %s", people.getLocation().getStreet(), people.getLocation().getCity(),
                    people.getLocation().getState()));
            Glide.with(this).load(people.getPicture().getLarge()).into(mPictureProfile);

            if (TextUtils.isEmpty(people.getGender()) && TextUtils.isEmpty(people.getLocation().getCity())) {
                mAddressCard.setVisibility(View.GONE);
            } else {
                mAddressCard.setVisibility(View.VISIBLE);
            }
            setTitle(people.getFullName());
        } else {
            setTitle(this.getResources().getString(R.string.app_name));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
