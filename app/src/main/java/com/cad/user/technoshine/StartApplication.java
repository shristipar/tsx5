package com.cad.user.technoshine;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by sony on 28-09-2015.
 */
public class StartApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "6j66hlaWyvLXD6uqFJabvXzuo2dJILljklsUfqcg", "BqV2hgKiiSIvqY9b3jm7uYAYHgP2GLbBXfLKaxLg");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL=new ParseACL();

        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                String msg = "";
                if (e == null) {
                    msg = "SUBSCRIBED";
                } else {
                    msg = "UN_SUBSCRIBED";
                }
               Log.d("push",msg );
            }
        });
    }
}
