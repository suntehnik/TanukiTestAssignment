package org.dvaletin.apps.tanukitestassignment.impl;

import android.text.TextUtils;

import com.google.inject.Inject;

import org.dvaletin.apps.tanukitestassignment.service.IRestConfiguration;

/**
 * Created by dvaletin on 20.08.15.
 */
public class Prod500pxRestConfiguration implements IRestConfiguration {


    private static final String REST_PROTOCOL = "https";
    private static final String REST_SERVER_NAME = "api.500px.com";
    private static final String REST_PORT = "";
    private static final String REST_BASE_PATH = "v1";

    @Inject
    public Prod500pxRestConfiguration() {}

    @Override
    public String getProtocol() {
        return REST_PROTOCOL;
    }

    @Override
    public String getServerName() {
        return REST_SERVER_NAME;
    }

    @Override
    public String getServerPort() {
        return REST_PORT;
    }

    @Override
    public String getUriBasePath() {
        return REST_BASE_PATH;
    }

    @Override
    public String getServerUrl() {
        final StringBuilder serverUrlBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(getProtocol()))
            serverUrlBuilder.append(getProtocol());
        else
            serverUrlBuilder.append("http");
        serverUrlBuilder.append("://");
        serverUrlBuilder.append(getServerName());
        if (!TextUtils.isEmpty(getServerPort()))
            serverUrlBuilder.append(":".concat(getServerPort()));
        serverUrlBuilder.append('/');
        if (!TextUtils.isEmpty(getUriBasePath()))
            serverUrlBuilder.append(getUriBasePath());
        return serverUrlBuilder.toString();
    }
}
