package com.zyj.dialogfragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DialogFragment mDialogFragment ;
    private FragmentTransaction ft ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getFragmentManager().beginTransaction();
        mDialogFragment = BaseDialogFragment.newInstance( "");

        findViewById( R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                mDialogFragment.show(ft, "dialog");
            }
        });

        //不能取消
        findViewById( R.id.cancle_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogFragment.setCancelable( false );
            }
        });
    }
}
