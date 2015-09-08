package org.dvaletin.apps.tanukitestassignment.inject;

import com.google.inject.AbstractModule;

import org.dvaletin.apps.tanukitestassignment.impl.CategoryPresenterImpl;
import org.dvaletin.apps.tanukitestassignment.impl.PhotoManagerImpl;
import org.dvaletin.apps.tanukitestassignment.impl.Prod500pxRestConfiguration;
import org.dvaletin.apps.tanukitestassignment.presenters.ICategoryPresenter;
import org.dvaletin.apps.tanukitestassignment.service.IPhotoManager;
import org.dvaletin.apps.tanukitestassignment.service.IRestConfiguration;

/**
 * Created by dvaletin on 20.08.15.
 */
public class CustomInjectModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(IPhotoManager.class).to(PhotoManagerImpl.class);
        bind(IRestConfiguration.class).to(Prod500pxRestConfiguration.class);
        bind(ICategoryPresenter.class).to(CategoryPresenterImpl.class);
    }
}
