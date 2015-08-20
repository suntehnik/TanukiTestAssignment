package org.dvaletin.apps.tanukitestassignment.impl;

import android.test.ApplicationTestCase;
import android.text.TextUtils;

import org.dvaletin.apps.tanukitestassignment.The500pxApplication;
import org.dvaletin.apps.tanukitestassignment.service.IRestConfiguration;

import java.net.URI;

import roboguice.RoboGuice;

/**
 * Created by dvaletin on 20.08.15.
 */
public class Prod500pxRestConfigurationTest extends ApplicationTestCase<The500pxApplication> {

    public Prod500pxRestConfigurationTest() {
        super(The500pxApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetServerUrlNotNull() throws Exception {
        assertNotNull(getApplication());
        final IRestConfiguration configuration = RoboGuice.getInjector(getApplication()).getInstance(IRestConfiguration.class);
        assertFalse(TextUtils.isEmpty(configuration.getServerUrl()));
    }

    public void testGetServerUrlValid() throws Exception {
        assertNotNull(getApplication());
        final IRestConfiguration configuration = RoboGuice.getInjector(getApplication()).getInstance(IRestConfiguration.class);
        URI uri = new URI(configuration.getServerUrl());
        assertNotNull(uri);
    }
}