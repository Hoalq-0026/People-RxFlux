package com.techdb.people_rxflux.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techdb.people_rxflux.R;
import com.techdb.people_rxflux.model.People;

import java.util.ArrayList;
import java.util.List;


public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<People> mPeopleList;
    private OnPeopleClickListener mCallback;

    public PeopleAdapter() {
        mPeopleList = new ArrayList<>();
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_people, parent, false);

        return new PeopleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        final People people = mPeopleList.get(position);
        Context context = holder.itemView.getContext();

        holder.mNameTextView.setText(String.format("%s.%s %s", people.getName().getFirst(), people.getName().getFirst(), people.getName().getLast()));
        holder.mPhoneTextView.setText(people.getPhone());
        holder.mEmailTextView.setText(people.getEmail());

        Glide.with(context).load(people.getPicture().getThumbnail()).fitCenter().into(holder.mThumbnailView);

        holder.mParentView.setOnClickListener(view -> {
                    if (mCallback != null) mCallback.onPeopleClicked(people);
                }
        );
    }

    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }

    public void setPeopleList(List<People> peopleList) {
        mPeopleList = peopleList;
        notifyDataSetChanged();
    }

    public void setCallback(OnPeopleClickListener callback) {
        mCallback = callback;
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mPhoneTextView;
        private TextView mEmailTextView;

        private ImageView mThumbnailView;
        private ViewGroup mParentView;

        public PeopleViewHolder(View itemView) {
            super(itemView);

            mNameTextView = (TextView) itemView.findViewById(R.id.label_name);
            mPhoneTextView = (TextView) itemView.findViewById(R.id.label_phone);
            mEmailTextView = (TextView) itemView.findViewById(R.id.label_email);

            mThumbnailView = (ImageView) itemView.findViewById(R.id.image_people);
            mParentView = (ViewGroup) itemView.findViewById(R.id.item_people);
        }
    }

    public interface OnPeopleClickListener {

        void onPeopleClicked(People people);
    }
}
