package com.icis.service.impl;

import com.icis.dao.RouteImgDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.dao.impl.RouteImgDaoImpl;
import com.icis.pojo.RouteImg;
import com.icis.service.RouteImgService;

import java.util.List;

public class RouteImgServiceImpl implements RouteImgService {
//    创建一个Dao层对象
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    @Override
    public List<RouteImg> getImgListByRid(Integer rid) {
        return routeImgDao.getImgListByRid(rid);
    }
}
