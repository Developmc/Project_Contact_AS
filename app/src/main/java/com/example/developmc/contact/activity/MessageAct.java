package com.example.developmc.contact.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

import com.example.developmc.contact.R;

/**
 * Created by developmc on 15/10/21.
 */
public class MessageAct extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_message);
    }
}
