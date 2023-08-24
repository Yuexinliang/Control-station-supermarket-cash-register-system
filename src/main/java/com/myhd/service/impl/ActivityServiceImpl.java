package com.myhd.service.impl;

import com.myhd.dao.IActivityDao;
import com.myhd.dao.impl.ActivityDaoImpl;
import com.myhd.pojo.Activity;
import com.myhd.service.IActivityService;
import java.time.LocalDateTime;
import java.util.List;

/**
 * className ActivityServiceImpl
 * packageName com.myhd.service.impl
 * Description 活动业务层的实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 10:55
 * @version 1.0
 */
public class ActivityServiceImpl implements IActivityService {
    IActivityDao ad = new ActivityDaoImpl();
    /**
     * @description: 插入新活动
     * @param activity
     * @return: java.lang.Boolean
     * 插入成功返回true，可以插入内容相同的活动
     * @author CYQH
     * @date: 2023/08/20 10:32
     */
    @Override
    public Boolean insertActivity(Activity activity) {
        boolean b = false;
        if (ad.insertActivity(activity) == 1){
            b = true;
        }
        return b;
    }

    /**
     * @description: 分页查询活动
     * @param start 要查看的页数
     * @param rows 每页展示几行
     * @return: java.util.List<com.myhd.pojo.Activity>
     * 查看页数具有活动返回一个有数据的集合
     * 查看页数没有活动，返回一个空集合
     * @author CYQH
     * @date: 2023/08/20 10:24
     */
    @Override
    public List<Activity> selectActivity(Integer start, Integer rows) {
        return ad.selectActivity(start, rows);
    }
    /**
     * @description: 更新活动
     * @param activity
     * @return: java.lang.Boolean
     * ture表示活动更新成功
     * false表示更新失败，可能是活动id不存在
     * @author CYQH
     * @date: 2023/08/20 10:19
     */
    @Override
    public Boolean updateActivity(Activity activity) {
        boolean b = false;
        if (ad.updateActivity(activity) == 1){
            b = true;
        }
        return b;
    }
    /**
     * @description: 活动删除
     * @param activityId 要删除的活动id
     * @return: java.lang.Boolean
     * true 活动删除成功
     * false 活动删除失败，可能是活动id不存在
     * @author CYQH
     * @date: 2023/08/20 10:22
     */
    @Override
    public Boolean deleteActivity(Integer activityId) {
        boolean b = false;
        if (ad.deleteActivity(activityId) == 1){
            b = true;
        }
        return b;
    }

    /**
     * @description: 根据输入时间（一般为当前时间），金额查询是否有对应活动折扣.
     * @param nowDate 查询日期
     * @param money 订单总金额
     * @return: java.lang.Double
     * 成功查询返回对应最大活动折扣
     * 未查到符合活动返回1.0
     * @author CYQH
     * @date: 2023/08/20 10:51
     */
    @Override
    public Double selectDiscount(LocalDateTime nowDate, Double money) {
        return ad.selectDiscount(nowDate,money);
    }

    /**
     * @description: 根据活动id返回活动信息
     * @param activityId
     * @return: com.myhd.pojo.Activity
     * @author CYQH
     * @date: 2023/08/21 10:15
     */
    @Override
    public Activity selectOneActivity(Integer activityId) {
        return ad.selectOneActivity(activityId);
    }

    /**
     * @description: 统计所有活动数量
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/08/21 10:14
     */
    @Override
    public Integer countAllPages() {
        return ad.countAllPages();
    }

}
