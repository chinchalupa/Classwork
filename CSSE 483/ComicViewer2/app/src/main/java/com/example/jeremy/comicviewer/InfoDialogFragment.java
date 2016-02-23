package com.example.jeremy.comicviewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Jeremy on 1/15/2016.
 */
public class InfoDialogFragment extends DialogFragment {

    public InfoDialogFragment() {}

    public static InfoDialogFragment newInstance(String header, String text) {
        InfoDialogFragment infoDialogFragment = new InfoDialogFragment();

        Bundle args = new Bundle();
        args.putString("Message", text);
        args.putString("Header", header);
        infoDialogFragment.setArguments(args);

        return infoDialogFragment;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        String message = getArguments().getString("Message");
        String header = getArguments().getString("Header");

        builder.setMessage(message);
        builder.setTitle(header);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
