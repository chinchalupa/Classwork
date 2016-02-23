package com.example.jeremy.exam2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 1/10/2016.
 */
public class SelectorFragment extends DialogFragment {

    private List<Equation> equationList;
    private OnCompleteListener mListener;
    private CheckBox squares;
    private CheckBox cubes;
    private CheckBox power2;
    private CheckBox power3;

    public SelectorFragment() {
        this.equationList = new ArrayList<>();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View view = getActivity().getLayoutInflater().inflate(R.layout.powers_layout, null, false);
        builder.setView(view);

        cubes = (CheckBox) view.findViewById(R.id.cubes);
        squares = (CheckBox) view.findViewById(R.id.squares);
        power2 = (CheckBox) view.findViewById(R.id.powers_2);
        power3 = (CheckBox) view.findViewById(R.id.powers_3);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean hasContent = false;

                if(squares.isChecked()) {
                    addNewList(10, 32, 2, false);
                    hasContent = true;
                }
                if(cubes.isChecked()) {
                    addNewList(2, 12, 3, false);
                    hasContent = true;
                }
                if(power3.isChecked()) {
                    addNewList(3, 6, 3, true);
                    hasContent = true;
                }
                if(power2.isChecked()) {
                    addNewList(4, 12, 2, true);
                    hasContent = true;
                }
                if(!hasContent) {
                    addNewList(4, 12, 2, true);
                }
                mListener = (OnCompleteListener) getActivity();
                mListener.onComplete(equationList);
                dismiss();
            }
        })
        .setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }

    public void addNewList(int start, int end, int staticVar, boolean isPowerChanging) {
        for(int i = start; i <= end; i++) {
            if(isPowerChanging) {
                this.equationList.add(new Equation(staticVar, i, (int) Math.pow(staticVar, i)));
            } else {
                this.equationList.add(new Equation(i, staticVar, (int) Math.pow(i, staticVar)));
            }
        }
    }

    public List<Equation> getEquationList() {
        return this.equationList;
    }

    public interface OnCompleteListener {
        public abstract void onComplete(List<Equation> e);
    }
}
