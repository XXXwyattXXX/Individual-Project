package com.example.steakhouse;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class SteakPagerActivity extends AppCompatActivity {
    private static final String EXTRA_STEAK_ID =
            "com.example.steakhouse.steak_id";

    private ViewPager mViewPager;
    private List<Steak> mSteaks;

    public static Intent newIntent(Context packageContext, UUID steakId) {
        Intent intent = new Intent(packageContext, SteakPagerActivity.class);
        intent.putExtra(EXTRA_STEAK_ID, steakId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steak_pager);
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_STEAK_ID);

        mViewPager = (ViewPager) findViewById(R.id.steak_view_pager);
        mSteaks = SteakLab.get(this).getSteaks();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Steak steak = mSteaks.get(position);
                return SteakFragment.newInstance(steak.getId());
            }
            @Override
            public int getCount() {
                return mSteaks.size();
            }
        });
    }

}
