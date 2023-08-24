package com.myhd.dao.impl;

import com.myhd.dao.IActivityDao;
import com.myhd.pojo.Activity;
import com.myhd.pojo.OrderItem;
import com.myhd.util.BaseDao;

import java.time.LocalDateTime;
import java.util.List;
/**
 * className ActivityDaoImpl
 * packageName com.myhd.dao.impl
 * Description 活动Dao的实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/19 11:19
 * @version 1.0
 */
public class ActivityDaoImpl implements IActivityDao {
    /**
     * @description: 由总经理添加活动, 活动id一般不需要特殊指定, 活动id从500001开始自增,
     * @param activity 具体要插入的活动对象
     * @return: java.lang.Integer 插入成功返回1
     * @author CYQH
     * @date: 2023/08/19 11:19
     */
    @Override
    public Integer insertActivity(Activity activity) {
        String sql = "INSERT INTO tb_activity(activityName,criticalTotal,startDate,endDate,discount) VALUES(?,?,?,?,?)";
        return BaseDao.updateInfo(sql,activity.getActivityName(),activity.getCriticalTotal(),
                activity.getStartDate(),activity.getEndDate(),activity.getDiscount());
    }

    /**
     * @description: 分页查询活动页面
     * @param start 查看的页数
     * @param rows 每页的行数
     * @return: java.util.List<com.myhd.pojo.Activity> 从数据库中获取的每页活动列表
     * @author CYQH
     * @date: 2023/08/19 11:21
     */
    @Override
    public List<Activity> selectActivity(Integer start, Integer rows) {
         String sql = "SELECT * FROM tb_activity LIMIT ?,?";
        return (List<Activity>) BaseDao.selectInfo(sql, Activity.class,(start-1)*rows,rows);
    }

    /**
     * @description: 统计总活动数据量，为了计算活动分页页数
     * @return: Integer 活动总数据量
     * @author CYQH
     * @date: 2023/08/20 12:26
     */
    @Override
    public Integer countAllPages() {
        String sql =  "SELECT COUNT(*) AS count FROM tb_activity";
        Long l = (Long) BaseDao.countInfo(sql);
        return l.intValue();
    }

    /**
     * @description: 进行活动更新
     * @param activity 要更新的活动信息
     * @return: java.lang.Integer 更新成功返回1
     * @author CYQH
     * @date: 2023/08/19 11:22
     */
    @Override
    public Integer updateActivity(Activity activity) {
        String sql = "UPDATE tb_activity SET activityName = ?, criticalTotal = ?, startDate = ?,endDate = ?,discount = ? where activityId = ?";
        return BaseDao.updateInfo(sql,activity.getActivityName(),activity.getCriticalTotal(),activity.getStartDate()
        ,activity.getEndDate(),activity.getDiscount(),activity.getActivityId());
    }

    /**
     * @description: 进行活动删除
     * @param activityId 要删除的活动的id
     * @return: java.lang.Integer 删除成功返回1
     * @author CYQH
     * @date: 2023/08/19 11:24
     */
    @Override
    public Integer deleteActivity(Integer activityId) {
        String sql = "DELETE FROM tb_activity WHERE activityId = ?";
        return BaseDao.updateInfo(sql,activityId);
    }
    /**
     * @description: 根据当前日期和消费金额进行折扣的选择并返回,
     * @param nowDate 当前时间
     * @param money 返回的折扣，没有符合活动返回1
     * @return: java.lang.Double
     * @author CYQH
     * @date: 2023/08/19 11:25
     */
    @Override
    public Double selectDiscount(LocalDateTime nowDate, Double money) {
        String sql = "SELECT MIN(discount) AS count FROM tb_activity WHERE criticalTotal <= ? AND (? BETWEEN startDate AND endDate)";
        if (!((Double) BaseDao.countInfo(sql,money,nowDate) == null)){
            return (Double) BaseDao.countInfo(sql,money,nowDate);
        }
        return 1.0;
    }

    /**
     * @description: 通过活动id获取活动信息
     * @param activityId
     * @return: com.myhd.pojo.Activity
     * @author CYQH
     * @date: 2023/08/20 23:02
     */
    @Override
    public Activity selectOneActivity(Integer activityId) {
        String sql = "SELECT * FROM tb_activity WHERE activityId = ?";
        List list = BaseDao.selectInfo(sql, Activity.class, activityId);
        if (!list.isEmpty()){
            return (Activity) list.get(0);
        }
        return new Activity();
    }
}
