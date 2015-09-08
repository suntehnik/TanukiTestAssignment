package org.dvaletin.apps.tanukitestassignment.service;

/**
 * Created by dvaletin on 20.08.15.
 */
public interface IRestConfiguration {

    public String getProtocol();
    public String getServerName();
    public String getServerPort();
    public String getUriBasePath();

    public String getServerUrl();

    public String getConsumerKey();
}
