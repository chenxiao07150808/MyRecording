package cn.edu.gdmec.s07150808.myrecording;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class RecordActivity extends  SingleFragmentActivity {
    private static final String EXTRA_RECORD_ID =
            "cn.edu.gdmec.s07150808.myrecording.record_id";
    public static Intent newIntent(Context packageContext, UUID recordId){
        Intent intent = new Intent(packageContext,RecordActivity.class);
        intent.putExtra(EXTRA_RECORD_ID, recordId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
      /*  return new RecordFragment();*/
        UUID recordId = (UUID)getIntent().getSerializableExtra(EXTRA_RECORD_ID);
        return RecordFragment.newInstance(recordId);
    }
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment=
                fm.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            fragment = new RecordFragment();//只在此处指定了具体类型
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }*/

}