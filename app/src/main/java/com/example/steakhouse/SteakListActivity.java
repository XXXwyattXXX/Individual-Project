package com.example.steakhouse;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class SteakListActivity extends SingleFragmentActivity
        implements SteakListFragment.Callbacks, SteakFragment.Callbacks{
    @Override
    protected Fragment createFragment() {
        return new SteakListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }
    @Override
    public void onSteakSelected(Steak steak) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = SteakPagerActivity.newIntent(this, steak.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = SteakFragment.newInstance(steak.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    public void onSteakUpdated(Steak steak) {
        SteakListFragment listFragment = (SteakListFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}
