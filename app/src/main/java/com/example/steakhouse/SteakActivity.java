package com.example.steakhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class SteakActivity extends SingleFragmentActivity {

    private static final String EXTRA_STEAK_ID =
            "com.example.steakhouse.steak_id";
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, SteakActivity.class);
        intent.putExtra(EXTRA_STEAK_ID, crimeId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID steakId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_STEAK_ID);
        return SteakFragment.newInstance(steakId);
    }
}
