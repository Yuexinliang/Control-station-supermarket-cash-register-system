package com.myhd.service.impl;

import com.myhd.dao.IOrderDao;
import com.myhd.dao.impl.OrderDaoImpl;
import com.myhd.exception.TimeCheckException;
import com.myhd.pojo.Order;
import com.myhd.pojo.OrderItem;
import com.myhd.service.IAccountService;
import com.myhd.service.IActivityService;
import com.myhd.service.IOrderItemService;
import com.myhd.service.IOrderService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
/**
 * className OrderServiceImpl
 * packageName com.myhd.service.impl
 * Description 订单业务层实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 14:59
 * @version 1.0
 */
public class OrderServiceImpl implements IOrderService {
    IOrderDao od = new OrderDaoImpl();
    IOrderItemService ois = new OrderItemServiceImpl();
    IActivityService as = new ActivityServiceImpl();
    IAccountService ias =  new AccountServiceImpl();
    /**
     * @description: 生成初始订单
     * @param order 将初始订单信息（生成时间）封装成一个对象
     * @return: java.lang.Integer 返回生成的订单编号
     * @author CYQH
     * @date: 2023/08/20 15:00
     */
    @Override
    public Integer InsertOrder(Order order) {
        return od.insertOrder(order);
    }

    /**
     * @description: 根据订单id返回订单内容
     * @param orderId 订单id
     * @return: com.myhd.pojo.Order 订单对象
     * @author CYQH
     * @date: 2023/08/20 15:04
     */
    @Override
    public Order selectByOrderId(Integer orderId) {
        return od.selectByOrderId(orderId);
    }

    /**
     * @description: 退货功能, 应该把已经确认的退货订单项信息集合(必须含有订单编号)传入退货方法里, 再使用订单项
     * 服务层的更新订单项方法, 统计订单项金额，然后调用订单更新功能更新订单表,
     * @param order 退货后需要更新的订单表对象
     * @param orderItems 确认好退款的订单项集合
     * @return: com.myhd.pojo.Order 返回一个退货后的订单表
     * @author CYQH
     * @date: 2023/08/20 18:56
     */
    @Override
    public Order returnOfGoods(Order order, List<OrderItem> orderItems) {
        Double total = 0.0;
        Double trueTotal = 0.0;
        Iterator<OrderItem> oldItems = orderItems.iterator();
        while (oldItems.hasNext()){
            OrderItem next =  oldItems.next();
            ois.updateOrderItem(next);
            total += next.getProductTotal();
        }
        Double discount = as.selectDiscount(order.getOrderDateTime(),total);
        trueTotal = BigDecimal.valueOf(discount * total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        ias.updateAccount(trueTotal-order.getOrderTrueTotal());
        Order order1 = new Order(order.getOrderId(),total,trueTotal,order.getOrderDateTime());
        od.updateOrder(order1);
        return order1;
    }

    /**
     * @description:  结账功能, 把顾客确认的订单项集合(每一条订单项必须含有订单编号, 用来更新订单)传入结账功能,
     * 然后使用活动服务层查出相应的折扣进行总价格(总价格可以根据传入进来的订单项集合算出来)的运算,最后更新订单表
     * @param order 初始订单
     * @param orderItems 添加和移除完商品后的最终订单集合
     * @return: com.myhd.pojo.Order 返回一个结算时的订单表
     * @author CYQH
     * @date: 2023/08/20 19:00
     */
    @Override
    public Order billPlease(Order order, List<OrderItem> orderItems) {
        Iterator Items = orderItems.listIterator();
        Double total = 0.0;
        Double trueTotal = 0.0;
        while (Items.hasNext()){
            OrderItem next = (OrderItem) Items.next();
            total += next.getProductTotal();
        }
        Double discount = as.selectDiscount(order.getOrderDateTime(),total);
        trueTotal = BigDecimal.valueOf(discount * total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        Order order1 = new Order(order.getOrderId(),total,trueTotal,order.getOrderDateTime());
        od.updateOrder(order1);
        return order1;
    }

    /**
     * @description: 根据开始时间和结束时间来统计这段时间订单表的金额, 要求startTime必须小于等于endTime,
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return: java.lang.Double 返回时间段内查询到的订单的总收益，未查询到
     * @author CYQH
     * @date: 2023/08/20 19:03
     */
    @Override
    public Double countMoneyByDate(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)){
            try {
                throw new TimeCheckException("起始时间应早于终止时间");
            }catch (TimeCheckException e){
                e.printStackTrace();
            }
        }else {
            if (od.countMoneyByDate(startTime,endTime) == null){
                return 0.0;
            }else {
                return od.countMoneyByDate(startTime,endTime);
            }
        }
        return 0.0;
    }
}
