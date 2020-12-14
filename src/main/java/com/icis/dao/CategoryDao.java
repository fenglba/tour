package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.Route;

import java.util.List;

public interface CategoryDao {
//    获得排名前三的分类
    public List<Category> getCategoryName();

//    指定分类id获取旅游线路数据 cid为1 要4条数据
    public List<Route> getRouteListByCategoryCid(Integer cid);

    //    指定分类id获取旅游线路数据 cid为2 要6条数据
    public List<Route> getRouteListByCategoryCid2(Integer cid);
    //    指定分类id获取旅游线路数据 cid为3 要6条数据
    public List<Route> getRouteListByCategoryCid3(Integer cid);
    //    根据cid获得cname
    public Category getCategoryNameByCid(Integer cid);
}
