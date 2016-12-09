package com.zyj.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by ${zyj} on 2016/12/9.
 */

public class BaseDialogFragment extends DialogFragment implements View.OnClickListener{

    String mParameter ;
    Button button_cancle ;

    public BaseDialogFragment(){
    }

    static BaseDialogFragment newInstance( String parameter) {
        BaseDialogFragment f = new BaseDialogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("parameter", parameter );
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Activity像DialogFragment传递参数
        mParameter = getArguments().getString("parameter");
        int  style = DialogFragment.STYLE_NO_TITLE;
        int theme = android.R.style.Theme_Holo;
        setStyle(style, theme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);

        button_cancle = (Button) view.findViewById( R.id.cancle_dialog );
        button_cancle.setOnClickListener( this );
        builder.setView(view) ;
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.cancle_dialog :
                dismiss();
                break;
        }
    }
}
