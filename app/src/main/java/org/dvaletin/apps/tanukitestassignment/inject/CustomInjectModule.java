package org.dvaletin.apps.tanukitestassignment.inject;

import com.google.inject.AbstractModule;

import org.dvaletin.apps.tanukitestassignment.impl.CategoryManager;
import org.dvaletin.apps.tanukitestassignment.impl.Prod500pxRestConfiguration;
import org.dvaletin.apps.tanukitestassignment.service.ICategoryManager;
import org.dvaletin.apps.tanukitestassignment.service.IRestConfiguration;

/**
 * Created by dvaletin on 20.08.15.
 */
public class CustomInjectModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(ICategoryManager.class).to(CategoryManager.class);
        bind(IRestConfiguration.class).to(Prod500pxRestConfiguration.class);
    }
}
