package cn.edu.gdmec.s07150808.myrecording;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by chen on 2017.
 */
public class RecordListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){ //实现父类的抽象方法
        return new RecordListFragment();
    }
}