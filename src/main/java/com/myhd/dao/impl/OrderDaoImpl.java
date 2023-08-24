package com.myhd.dao.impl;

import com.myhd.dao.IOrderDao;
import com.myhd.pojo.Order;
import com.myhd.util.BaseDao;
import java.time.LocalDateTime;
import java.util.List;
/**
 * className OrderDaoImpl
 * packageName com.myhd.dao.impl
 * Description 订单表Dao层的实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/19 10:03
 * @version 1.0
 */
public class OrderDaoImpl implements IOrderDao {
    /**
     * @description: 插入数据库一个提前定义的空订单表
     * @param order 一个自动生成编号的已定义时间的对象
     * @return: java.lang.Integer 返回一个订单编号
     * @author CYQH
     * @date: 2023/08/19 10:03
     */
    @Override
    public Integer insertOrder(Order order) {
        String sql = "INSERT INTO tb_order(orderDateTime) VALUES(?) ";
        BaseDao.updateInfo(sql,order.getOrderDateTime());
        List<?> objects = BaseDao.selectInfo(" select * from tb_order where orderId = (SELECT max(orderId) from tb_order)", Order.class);
        Order o = (Order)objects.get(0);
        return o.getOrderId();
    }
    /**
     * @description: 更新订单表的金额
     * @param order 具有金额的订单表
     * @return: java.lang.Integer
     * i=0-----更新失败
     * i=1-----更新成功
     * @author CYQH
     * @date: 2023/08/19 10:07
     */
    @Override
    public Integer updateOrder(Order order) {
        String sql2 = "UPDATE tb_order SET orderTotal = ?, orderTrueTotal = ? WHERE orderId = ?";
        return BaseDao.updateInfo(sql2,order.getOrderTotal(),order.getOrderTrueTotal(),order.getOrderId());
    }

    /**
     * @description: 根据订单id查询数据库一个订单数据
     * @param orderId 订单id
     * @return: com.myhd.pojo.Order 返回一个订单对象
     * @author CYQH
     * @date: 2023/08/19 10:11
     */
    @Override
    public Order selectByOrderId(Integer orderId) {

        String sql = "SELECT * FROM tb_order WHERE orderId = ?";
        List list =  BaseDao.selectInfo(sql,Order.class,orderId);
        if (list.isEmpty()){
            return new Order();
        }
        return (Order) list.get(0);
    }

    /**
     * @description: 根据起止时间计算时间内订单表进账总金额
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return: java.lang.Double  ----时间内总金额，未查询到或时间错误返回0.0
     * @author CYQH
     * @date: 2023/08/19 10:12
     */
    @Override
    public Double countMoneyByDate(LocalDateTime startTime, LocalDateTime endTime) {
        String sql = "SELECT SUM(orderTrueTotal) AS count FROM tb_order WHERE orderDateTime between ? and ?";
        return (Double) BaseDao.countInfo(sql,startTime,endTime);
    }


}
