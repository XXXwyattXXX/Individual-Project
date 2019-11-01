package com.example.steakhouse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

public class SteakFragment extends Fragment {

    private static final String ARG_STEAK_ID = "steak_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Steak mSteak;
    private EditText mTitleField;
    private EditText mAddressField;
    private EditText mCommentsField;
    private Button mDateButton;
    private CheckBox mRecmedCheckBox;
    private Callbacks mCallbacks;
    /**
     * Required interface for hosting activities
     */
    public interface Callbacks {
        void onSteakUpdated(Steak steak);
    }

    public static SteakFragment newInstance(UUID steakId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_STEAK_ID, steakId);
        SteakFragment fragment = new SteakFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID steakId = (UUID) getArguments().getSerializable(ARG_STEAK_ID);
        mSteak = SteakLab.get(getActivity()).getSteak(steakId);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_steak, container, false);
        mTitleField = (EditText) v.findViewById(R.id.steak_name);
        mTitleField.setText(mSteak.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSteak.setTitle(s.toString());
                updateSteak();
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }

        });

        mAddressField = (EditText) v.findViewById(R.id.steak_address);
        mAddressField.setText(mSteak.getAddress());
        mAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSteak.setAddress(s.toString());
                updateSteak();
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }

        });

        mCommentsField = (EditText) v.findViewById(R.id.steak_comments);
        mCommentsField.setText(mSteak.getComments());
        mCommentsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSteak.setComments(s.toString());
                updateSteak();
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }

        });

        mDateButton = (Button) v.findViewById(R.id.steak_date);
        updateDate();
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mSteak.getDate());
                dialog.setTargetFragment(SteakFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mRecmedCheckBox = (CheckBox)v.findViewById(R.id.steak_recommended);
        mRecmedCheckBox.setChecked(mSteak.isRecmed());
        mRecmedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mSteak.setRecmed(isChecked);
                updateSteak();
            } });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return; }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mSteak.setDate(date);
            updateSteak();
            updateDate();
        }
    }

    private void updateSteak() {
        mCallbacks.onSteakUpdated(mSteak);
    }

    private void updateDate() {
        mDateButton.setText(mSteak.getDate().toString());
    }
}
