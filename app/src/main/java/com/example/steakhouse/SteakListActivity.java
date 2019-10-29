package com.example.steakhouse;

import androidx.fragment.app.Fragment;

public class SteakListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SteakListFragment();
    }
}
