package org.dvaletin.apps.tanukitestassignment.service;

import org.dvaletin.apps.tanukitestassignment.model.Picture;

import java.util.List;

/**
 * Created by dvaletin on 26.06.15.
 */
public interface ICategoryManager {

    public List<Picture> getImagesByCategory(int categoryId);
}
