package cn.edu.gdmec.s07150808.myrecording;

/**
 * Created by chen on 2017/5/3.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

public class RecordFragment extends Fragment {
    private static final String ARG_RECORD_ID = "record_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private Record mRecord;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public static RecordFragment newInstance(UUID recordId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECORD_ID, recordId);
        RecordFragment fragment = new RecordFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       /* mRecord = new Record();*/
        /*UUID recordId = (UUID)getActivity().getIntent()
                .getSerializableExtra(RecordActivity.EXTRA_RECORD_ID);*/
        UUID recordId = (UUID)getArguments().getSerializable(ARG_RECORD_ID);
        mRecord = RecordLab.get(getActivity()).getRecord(recordId);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_record,container,false);
        mTitleField = (EditText)v.findViewById(R.id.record_title);
        mTitleField.setText(mRecord.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() { //以下自动创建
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRecord.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.record_solved);
        mSolvedCheckBox.setChecked(mRecord.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new  CompoundButton.OnCheckedChangeListener() {//自动引入 CompoundButto
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 mRecord.setSolved(isChecked);
             }
        });
        mDateButton = (Button)v.findViewById(R.id.record_date);
        mDateButton.setText(mRecord.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = getFragmentManager();
               /* DatePickerFragment datePickerFragment = new DatePickerFragment();*/
                DatePickerFragment datePickerFragment = DatePickerFragment
                        .newInstance(mRecord.getDate());
                datePickerFragment.setTargetFragment(RecordFragment.this,
                        REQUEST_DATE);
                datePickerFragment.show(fragmentManager, DIALOG_DATE);
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date)intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mRecord.setDate(date);
            mDateButton.setText(mRecord.getDate().toString());
        }
    }
}
