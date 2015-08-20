package org.dvaletin.apps.tanukitestassignment.service;

import org.dvaletin.apps.tanukitestassignment.model.Picture;

import java.util.List;

import bolts.Task;

/**
 * Created by dvaletin on 26.06.15.
 */
public interface ICategoryManager {

    Task<List<Picture>> getImagesByCategory(int categoryId);
}
