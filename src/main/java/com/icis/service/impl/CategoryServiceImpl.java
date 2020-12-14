package com.icis.service.impl;

import com.icis.dao.CategoryDao;
import com.icis.dao.impl.CategoryDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.Route;
import com.icis.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> getCategoryName() {
        return categoryDao.getCategoryName();
    }

    @Override
    public List<Route> getRouteListByCategoryCid(Integer cid) {
        return categoryDao.getRouteListByCategoryCid(cid);
    }

    @Override
    public List<Route> getRouteListByCategoryCid2(Integer cid) {
        return categoryDao.getRouteListByCategoryCid2(cid);
    }

    @Override
    public List<Route> getRouteListByCategoryCid3(Integer cid) {
        return categoryDao.getRouteListByCategoryCid3(cid);
    }

    @Override
    public Category getCategoryNameByCid(Integer cid) {
        return categoryDao.getCategoryNameByCid(cid);
    }
}
