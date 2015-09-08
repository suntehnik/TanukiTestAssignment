package org.dvaletin.apps.tanukitestassignment.presenters;

import org.dvaletin.apps.tanukitestassignment.views.ICategoryView;

/**
 * Created by dvaletin on 25.08.15.
 */
public interface ICategoryPresenter {
    void onCategoryItemSelected(int itemPosition);

    void setView(ICategoryView view);
}
