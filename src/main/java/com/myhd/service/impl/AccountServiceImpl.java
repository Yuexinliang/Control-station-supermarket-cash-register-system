package com.myhd.service.impl;

import com.myhd.dao.IAccountDao;
import com.myhd.dao.impl.AccountDaoImpl;
import com.myhd.pojo.Account;
import com.myhd.service.IAccountService;

/**
 * className AccountServiceImpl
 * packageName com.myhd.service.impl
 * Description 账户业务层实现类，包含查看账户资产和更新账户资产功能
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 14:30
 * @version 1.0
 */
public class AccountServiceImpl implements IAccountService {
    IAccountDao adi = new AccountDaoImpl();
    /**
     * @description: 更新账户资产信息
     * @param money 要变更的金额数
     * @return: java.lang.Boolean
     * true -----资产更新成功
     * false -----资产更新失败
     * @author CYQH
     * @date: 2023/08/20 14:31
     */
    @Override
    public Boolean updateAccount(Double money) {
        if (adi.updateAccount(money) == 1){
            return true;
        }
        return false;
    }

    /**
     * @description: 查看账户信息，需进行密码验证
     * @param account 将验证信息（密码）封装成一个账户对象
     * @return: java.lang.Double
     * 验证成功返回  账户金额
     * 验证失败返回  0.0
     * @author CYQH
     * @date: 2023/08/20 14:33
     */
    @Override
    public Double selectAccount(Account account) {
        account = adi.selectAccount(account);
        /*业务层对返回值进行判断*/
        if (account.getAccountId() == null){
            System.out.println("账户密码错误！");
            return 0.0;
        }else {
            return account.getMoney();
        }
    }
}
