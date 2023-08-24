package com.myhd.dao.impl;

import com.myhd.dao.IOrderItemDao;
import com.myhd.pojo.OrderItem;
import com.myhd.pojo.Product;
import com.myhd.util.BaseDao;
import java.util.List;

/**
 * className OrderItemDaoImpl
 * packageName com.myhd.dao.impl
 * Description  订单项的Dao实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/19 22:43
 * @version 1.0
 */
public class OrderItemDaoImpl implements IOrderItemDao {
    /**
     * @description: 进行将生成的订单项表对象插入到数据库的方法
     * @param orderItem 根据方法生成的订单项对象
     * @return: java.lang.Integer  返回值为1插入成功
     * @author CYQH
     * @date: 2023/08/19 22:45
     */
    @Override
    public Integer insertOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO tb_orderitem VALUES(?,?,?,?,?,?) ";
        return BaseDao.updateInfo(sql,orderItem.getOrderId(),orderItem.getProductId(),orderItem.getProductName(),
                orderItem.getProductCount(),orderItem.getProductPrice(),orderItem.getProductTotal());
    }

    /**
     * @description: 根据订单id和产品id查询唯一的订单项表
     * @param orderId 订单id
     * @param productId 产品id
     * @return: com.myhd.pojo.OrderItem
     * 返回唯一一个订单项
     * 未查询到返回一个空对象
     * @author CYQH
     * @date: 2023/08/19 22:51
     */
    @Override
    public OrderItem selectOrderItem(Integer orderId, Integer productId) {
        String sql = "SELECT * FROM tb_orderitem WHERE orderId = ? AND productId = ?";
        List list = BaseDao.selectInfo(sql,OrderItem.class,orderId,productId);
        if (!list.isEmpty()){
            return (OrderItem) list.get(0);
        }
        return new OrderItem();
    }

    /**
     * @description: 根据订单id查询全部订单项，用于汇总同订单id订单项的金额，更新订单表
     * @param orderId 订单id
     * @return: java.util.List<com.myhd.pojo.OrderItem> 同一订单id的全部订单项
     * @author CYQH
     * @date: 2023/08/19 22:54
     */
    @Override
    public List<OrderItem> selectAllOrderItem(Integer orderId) {
        String sql = "SELECT * FROM tb_orderitem WHERE orderId = ?";
        return (List<OrderItem>) BaseDao.selectInfo(sql,OrderItem.class,orderId);
    }

    /**
     * @description: 用于退货时的订单更新，别忘了更新完后重新更新一次订单表
     * @param orderItem 将更新数据封装进一个订单项对象
     * @return: java.lang.Integer 返回值为1更新成功
     * @author CYQH
     * @date: 2023/08/19 22:56
     */
    @Override
    public Integer updateOrderItem(OrderItem orderItem) {
        String sql = "UPDATE tb_orderitem SET productCount = ?,productTotal = ? WHERE orderId = ? AND productId = ?";
        return BaseDao.updateInfo(sql,orderItem.getProductCount(),orderItem.getProductTotal(),orderItem.getOrderId(),orderItem.getProductId());
    }

    /**
     * @description: 根据商品id返回所有符合的订单项，统计同种商品的销售额
     * @param product 将要使用的参数封装为一个产品对象
     * @return: java.lang.Double 返回一个同种商品总销售额
     * @author CYQH
     * @date: 2023/08/19 23:00
     */
    @Override
    public Double countByProductId(Product product) {
        String sql = "SELECT SUM(productTotal) AS count FROM tb_orderitem WHERE productId = ?";
        return (Double) BaseDao.countInfo(sql,product.getProductId());
    }

    /**
     * @description: 根据产品id查询产品销售详情
     * @param product
     * @return: java.util.List<com.mod.pojo.OrderItem>
     * @author CYQH
     * @date: 2023/08/22 9:15
     */
    @Override
    public List<OrderItem> selectByProductId(Product product) {
        String sql = "SELECT * FROM tb_orderitem WHERE productId = ?";
        return (List<OrderItem>) BaseDao.selectInfo(sql,OrderItem.class,product.getProductId());
    }
}
