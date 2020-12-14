package com.icis.service;

import com.icis.pojo.Category;
import com.icis.pojo.Route;

import java.util.List;

public interface CategoryService {
    //    获得排名前三的分类
    public List<Category> getCategoryName();
//    根据分类的cid获取旅游线路的数据
    public List<Route> getRouteListByCategoryCid(Integer cid);
    //    指定分类id获取旅游线路数据 cid为2 要6条数据
    public List<Route> getRouteListByCategoryCid2(Integer cid);
    //    指定分类id获取旅游线路数据 cid为3 要6条数据
    public List<Route> getRouteListByCategoryCid3(Integer cid);
    //    根据cid获得cname
    public Category getCategoryNameByCid(Integer cid);
}
