package com.myhd.dao.impl;

import com.myhd.dao.IAccountDao;
import com.myhd.pojo.Account;
import com.myhd.util.BaseDao;
import java.util.List;

public class AccountDaoImpl implements IAccountDao {
    /**
     * @description: 根据传输进来的Account对象的password进行数据库查询
     * 如果密码正确就返回数据库中的数据到集合中，密码错误集合为空，再判断
     * 集合为空就返回一个全数据为空的account，如果集合有数据就将集合的唯一一个数据
     * 赋值给account，再返回account。
     * @param account 将用户输入的密码封装为一个Account对象
     * @return: com.myhd.pojo.Account 根据密码正确与否返回一个有数据或数据全为空的account
     * @author CYQH
     * @date: 2023/08/18 19:33
     */
    @Override
    public Account selectAccount(Account account) {
        String sql = "SELECT accountId, accountPwd, money FROM tb_account WHERE accountPwd = ?";
        List list = BaseDao.selectInfo(sql,account.getClass(),account.getAccountPwd());
        if (!list.isEmpty()){
            return account = (Account) list.get(0);
        }else {
            account.setAccountPwd(null);
            return account;
        }
    }
    /**
     * @description: 根据传进来的参数对账户金额进行增减。
     * @param money 进行订单结算或订单退款时的增减金额。
     * @return: java.lang.Integer
     * 1------账户金额更新成功
     * -1/0---账户金额更新失败
     * @author CYQH
     * @date: 2023/08/18 19:42
     */
    @Override
    public Integer updateAccount(Double money) {
        String sql = "UPDATE tb_account SET money = money + ?";
        return BaseDao.updateInfo(sql,money);
    }
}
