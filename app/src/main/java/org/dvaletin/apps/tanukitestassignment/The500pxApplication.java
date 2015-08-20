package org.dvaletin.apps.tanukitestassignment;

import android.app.Application;

import com.google.inject.util.Modules;

import org.dvaletin.apps.tanukitestassignment.inject.CustomInjectModule;

import roboguice.RoboGuice;
import roboguice.config.DefaultRoboModule;

/**
 * Created by dvaletin on 26.06.15.
 */
public class The500pxApplication extends Application {

    public void onCreate() {
        super.onCreate();
        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(this), new CustomInjectModule());
    }

}
