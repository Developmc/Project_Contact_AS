package com.example.developmc.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.developmc.contact.activity.LoginAct;
import com.example.developmc.contact.activity.TimelineAtc;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = Config.getCachedToken(this) ;
        if(token!=null){
            Intent intent = new Intent(this, TimelineAtc.class) ;
            intent.putExtra(Config.KEY_TOKEN,token) ;
            startActivity(intent);
        }
        else{
            startActivity(new Intent(this, LoginAct.class));
        }
        finish();
    }
}
