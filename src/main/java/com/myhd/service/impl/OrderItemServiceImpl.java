package com.myhd.service.impl;

import com.myhd.dao.IOrderItemDao;
import com.myhd.dao.impl.OrderItemDaoImpl;
import com.myhd.pojo.OrderItem;
import com.myhd.pojo.Product;
import com.myhd.service.IOrderItemService;
import java.util.List;
/**
 * className OrderItemServiceImpl
 * packageName com.myhd.service.impl
 * Description 订单项服务层实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 15:09
 * @version 1.0
 */
public class OrderItemServiceImpl implements IOrderItemService {
    IOrderItemDao oid = new OrderItemDaoImpl();
    /**
     * @description: 进行将生成的订单项表对象插入到数据库的方法
     * @param orderItem 根据方法生成的订单项对象
     * @return: java.lang.Boolean
     * true 插入成功
     * @author CYQH
     * @date: 2023/08/20 15:16
     */
    @Override
    public Boolean insertOrderItem(OrderItem orderItem) {
        if (oid.insertOrderItem(orderItem) == 1){
            return true;
        }
        return false;
    }

    /**
     * @description: 根据订单id和产品id查询唯一的订单项表
     * @param orderId 订单id
     * @param productId 产品id
     * @return: com.myhd.pojo.OrderItem
     * 返回唯一一个订单项
     * 未查询到返回一个空对象
     * @author CYQH
     * @date: 2023/08/20 15:22
     */
    @Override
    public OrderItem selectOrderItem(Integer orderId, Integer productId) {
        return oid.selectOrderItem(orderId,productId);
    }

    /**
     * @description: 根据订单id查询全部订单项，用于汇总同订单id订单项的金额，更新订单表
     * @param orderId 订单id
     * @return: java.util.List<com.myhd.pojo.OrderItem> 同一订单id的全部订单项,订单编号不存在返回一个空集合
     * @author CYQH
     * @date: 2023/08/20 15:25
     */
    @Override
    public List<OrderItem> selectAllOrderItem(Integer orderId) {
        return oid.selectAllOrderItem(orderId);
    }

    /**
     * @description: 用于退货时的订单更新，别忘了更新完后重新更新一次订单表
     * @param orderItem 将更新数据封装进一个订单项对象
     * @return: java.lang.Boolean 返回值为true更新成功
     * @author CYQH
     * @date: 2023/08/20 15:28
     */
    @Override
    public Boolean updateOrderItem(OrderItem orderItem) {
        if (oid.updateOrderItem(orderItem) == 1){
            return true;
        }
        return false;
    }

    /**
     * @description: 根据商品id返回所有符合的订单项，统计同种商品的销售额
     * @param product 将要使用的参数封装为一个产品对象
     * @return: java.lang.Double 返回一个同种商品总销售额
     * 如果订单项没有该商品销售记录，返回0.0
     * @author CYQH
     * @date: 2023/08/20 15:44
     */
    @Override
    public Double countByProductId(Product product) {
        if (oid.countByProductId(product) != null ){
            return oid.countByProductId(product);
        }
        return 0.0;
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
        return oid.selectByProductId(product);
    }
}
