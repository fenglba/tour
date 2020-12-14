package com.icis.categorytest;

import com.icis.dao.CategoryDao;
import com.icis.dao.impl.CategoryDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.Route;
import org.junit.Test;

import java.util.List;

public class CategoryTest {
    private CategoryDao categoryDao = new CategoryDaoImpl();
//    获得排名前三的分类
    @Test
    public void getCategoryNameTset(){
        List<Category> categoryList = categoryDao.getCategoryName();
        for (Category category : categoryList) {
            System.out.println(category.getCid()+"----"+category.getCname());
        }
    }

    @Test
    public void getRouteListByCategoryCidTest(){
        List<Route> routeList = categoryDao.getRouteListByCategoryCid(1);
        for (Route route : routeList) {
            System.out.println(route.getRid()+"-----"+route.getRname());
        }
    }
    @Test
    public void getCategoryNameByCid(){
        Category category = categoryDao.getCategoryNameByCid(3);
        System.out.println(category.getCname());
    }
}
