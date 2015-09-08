package org.dvaletin.apps.tanukitestassignment.impl;

import org.dvaletin.apps.tanukitestassignment.presenters.ICategoryPresenter;
import org.dvaletin.apps.tanukitestassignment.views.ICategoryView;

/**
 * Created by dvaletin on 26.08.15.
 */
public class CategoryPresenterImpl implements ICategoryPresenter {

    private ICategoryView mView;

    @Override
    public void onCategoryItemSelected(int itemPosition) {
        mView.onCategorySelected(itemPosition);
    }

    @Override
    public void setView(ICategoryView view) {
        mView = view;
    }
}
