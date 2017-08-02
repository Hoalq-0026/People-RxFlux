package com.techdb.people_rxflux.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.techdb.people_rxflux.PeopleApp;
import com.techdb.people_rxflux.R;
import com.techdb.people_rxflux.action.Actions;
import com.techdb.people_rxflux.model.People;
import com.techdb.people_rxflux.store.PeopleStore;
import com.techdb.rxflux.action.RxError;
import com.techdb.rxflux.dispatcher.RxViewDispatch;
import com.techdb.rxflux.store.RxStore;
import com.techdb.rxflux.store.RxStoreChange;

import java.util.Arrays;
import java.util.List;


public class PeopleActivity extends AppCompatActivity implements RxViewDispatch, PeopleAdapter.OnPeopleClickListener {

    private PeopleAdapter mPeopleAdapter;
    private PeopleStore mPeopleStore;

    private CoordinatorLayout mCoordinator;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_people);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_people);
        TextView statusLabel = (TextView) findViewById(R.id.label_status);
        FloatingActionButton fabButton = (FloatingActionButton) findViewById(R.id.fab);
        RecyclerView peopleList = (RecyclerView) findViewById(R.id.list_people);

        peopleList.setHasFixedSize(true);
        mPeopleAdapter = new PeopleAdapter();
        mPeopleAdapter.setCallback(this);

        peopleList.setLayoutManager(new LinearLayoutManager(this));
        peopleList.setAdapter(mPeopleAdapter);

        fabButton.setOnClickListener(view -> {
            showLoadingFrame(true);
            refresh();
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onPeopleClicked(People people) {
        startActivity(PeopleDetailActivity.launchDetail(this, people));
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        showLoadingFrame(false);

        switch (change.getStoreId()) {
            case PeopleStore.ID:
                switch (change.getRxAction().getType()) {
                    case Actions.FETCH_PEOPLE:
                        mPeopleAdapter.setPeopleList(mPeopleStore.getPeopleList());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {
        showLoadingFrame(false);
        Throwable throwable = error.getThrowable();
        if (throwable != null) {
            Snackbar.make(mCoordinator, "An error occur :", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", view -> {
                        PeopleApp.get(this).getActionCreator().getPeopleList();
                    });
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRxViewRegistered() {

    }

    @Override
    public void onRxViewUnRegistered() {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        mPeopleStore = PeopleStore.getInstance(PeopleApp.get(this).getRxFlux().getDispatcher());
        return Arrays.asList(mPeopleStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return null;
    }

    private void showLoadingFrame(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void refresh() {
        showLoadingFrame(true);
        PeopleApp.get(this).getActionCreator().getPeopleList();
    }
}
