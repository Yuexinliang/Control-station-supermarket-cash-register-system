package com.myhd.dao.impl;

import com.myhd.dao.IProductDao;
import com.myhd.pojo.Product;
import com.myhd.util.BaseDao;
import java.util.List;
/**
 * className ProductDaoImpl
 * packageName com.myhd.dao.impl
 * Description 商品Dao层的实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/18 21:43
 * @version 1.0
 */
public class ProductDaoImpl implements IProductDao {
    /**
     * @description: 根据商品id查询商品信息用于填入订单项
     * @param productId 要添加的商品id
     * @return: com.myhd.pojo.Product  返回一个商品对象用于订单项填入信息
     * @author CYQH
     * @date: 2023/08/18 21:39
     */
    @Override
    public Product selectByproductId(Integer productId) {
        String sql = "SELECT productId, productName, productPrice FROM tb_product WHERE productId = ?";
        List list = BaseDao.selectInfo(sql,Product.class, productId);
        if (!list.isEmpty()){
            return (Product) list.get(0);
        }
        return new Product();
    }

    @Override
    public List<Product> selectAllProduct() {
        String sql = "SELECT * FROM tb_product";
        return (List<Product>) BaseDao.selectInfo(sql, Product.class);
    }
}
