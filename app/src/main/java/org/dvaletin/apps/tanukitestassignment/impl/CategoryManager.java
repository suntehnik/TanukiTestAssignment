package org.dvaletin.apps.tanukitestassignment.impl;

import android.text.TextUtils;

import com.google.inject.Inject;

import org.dvaletin.apps.tanukitestassignment.model.Picture;
import org.dvaletin.apps.tanukitestassignment.service.ICategoryManager;
import org.dvaletin.apps.tanukitestassignment.service.IRestConfiguration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by dvaletin on 26.06.15.
 */
public class CategoryManager implements ICategoryManager {


    private static final String GET_CATEGORY_PATH = "category";
    private final String serverUrl;
    private final RestTemplate restTemplate;

    @Inject
    public CategoryManager(IRestConfiguration config) {
        final StringBuilder serverUrlBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(config.getProtocol()))
            serverUrlBuilder.append(config.getProtocol());
        else
            serverUrlBuilder.append("http");
        serverUrlBuilder.append("://");
        serverUrlBuilder.append(config.getServerName());
        if (!TextUtils.isEmpty(config.getServerPort()))
            serverUrlBuilder.append(":".concat(config.getServerPort()));
        serverUrlBuilder.append('/');
        if (!TextUtils.isEmpty(config.getUriBasePath()))
            serverUrlBuilder.append(config.getUriBasePath());
        serverUrl = serverUrlBuilder.toString();

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

    }

    @Override
    public Task<List<Picture>> getImagesByCategory(int categoryId) {
        return Task.callInBackground(new Callable<Picture[]>() {
            @Override
            public Picture[] call() throws Exception {
                return restTemplate.getForObject(getUrlForService(GET_CATEGORY_PATH), Picture[].class);
            }
        }).onSuccess(new Continuation<Picture[], List<Picture>>() {
            @Override
            public List<Picture> then(Task<Picture[]> task) throws Exception {
                return Arrays.asList(task.getResult());
            }
        });
    }

    private String getServerUrl() {
        return serverUrl;
    }

    private String getUrlForService(String service) {
        return getServerUrl().concat("/").concat(service);
    }

    private RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
