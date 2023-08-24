package com.myhd.service.impl;

import com.myhd.dao.IProductDao;
import com.myhd.dao.impl.ProductDaoImpl;
import com.myhd.pojo.Product;
import com.myhd.service.IProductService;
import java.util.List;

/**
 * className ProductServiceImpl
 * packageName com.myhd.service.impl
 * Description 商品业务类的实现类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 10:56
 * @version 1.0
 */
public class ProductServiceImpl implements IProductService {
    IProductDao pd = new ProductDaoImpl();
    /**
     * @description: 调用Dao方法查询对应商品id的商品信息，一般用于生成订单项
     * @param productId 商品id
     * @return: com.myhd.pojo.Product 将商品信息封装为商品对象
     * 查询成功返回一个具有商品id对应信息的商品对象
     * 查询失败返回一个属性全默认为null的商品对象，可能是此id不存在
     * @author CYQH
     * @date: 2023/08/20 9:48
     */
    @Override
    public Product selectByproductId(Integer productId) {
        return pd.selectByproductId(productId);
    }

    @Override
    public List<Product> selectAllProduct() {
        return pd.selectAllProduct();
    }
}
