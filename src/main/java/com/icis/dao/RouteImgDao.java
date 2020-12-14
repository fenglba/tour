package com.icis.dao;

import com.icis.pojo.RouteImg;

import java.util.List;

public interface RouteImgDao {
    //    根据旅游线路景点id  获得对应的宣传图片们
    public List<RouteImg> getImgListByRid(Integer rid);
}
