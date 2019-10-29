package com.example.steakhouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SteakListFragment extends Fragment {
    private RecyclerView mSteakRecyclerView;
    private SteakAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steak_list, container, false);
        mSteakRecyclerView = (RecyclerView) view
                .findViewById(R.id.steak_recycler_view);
        mSteakRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        SteakLab steakLab = SteakLab.get(getActivity());
        List<Steak> steaks = steakLab.getSteaks();
        if (mAdapter == null) {
        mAdapter = new SteakAdapter(steaks);
        mSteakRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class SteakHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mAddressTextView;
        private TextView mDateTextView;
        private ImageView mRecmedImageView;

        private Steak mSteak;

        public SteakHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_steak, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.steak_title);
            mAddressTextView = (TextView) itemView.findViewById(R.id.steak_address);
            mDateTextView = (TextView) itemView.findViewById(R.id.steak_date);
            mRecmedImageView = (ImageView) itemView.findViewById(R.id.steak_recmed);
        }

        public void bind(Steak steak) {
            mSteak = steak;
            mTitleTextView.setText(mSteak.getTitle());
            mAddressTextView.setText(mSteak.getAddress());
            mDateTextView.setText(mSteak.getDate().toString());
            mRecmedImageView.setVisibility(steak.isRecmed() ? View.VISIBLE : View.GONE);
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mSteak.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = SteakActivity.newIntent(getActivity(), mSteak.getId());
            startActivity(intent);
        }
    }

    private class SteakAdapter extends RecyclerView.Adapter<SteakHolder> {
        private List<Steak> mSteaks;
        public SteakAdapter(List<Steak> steaks) {
            mSteaks = steaks;
        }
        @Override
        public SteakHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SteakHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(SteakHolder holder, int position) {
            Steak steak = mSteaks.get(position);
            holder.bind(steak);
        }
        @Override
        public int getItemCount() {
            return mSteaks.size();
        }
    }

}
