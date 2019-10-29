package com.example.steakhouse;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SteakLab {

    private static SteakLab sSteakLab;

    private List<Steak> mSteaks;

    public static SteakLab get(Context context) {
        if (sSteakLab == null) {
            sSteakLab = new SteakLab(context);
        }
        return sSteakLab;
    }
    private SteakLab(Context context) {
        mSteaks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Steak steak = new Steak();
            steak.setTitle("Steak #" + i);
            steak.setAddress("Main Street" + i);
            steak.setRecmed(i % 2 == 0); // Every other one
            mSteaks.add(steak);
        }
    }

    public List<Steak> getSteaks() {
        return mSteaks;
    }
    public Steak getSteak(UUID id) {
        for (Steak steak : mSteaks) {
            if (steak.getId().equals(id)) {
                return steak;
            }
        }
        return null;
    }
}
