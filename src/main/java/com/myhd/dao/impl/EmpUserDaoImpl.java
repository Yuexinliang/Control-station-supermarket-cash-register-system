package com.myhd.dao.impl;

import com.myhd.dao.IEmpUserDao;
import com.myhd.pojo.EmpUser;
import com.myhd.util.BaseDao;

import java.util.List;
/**
 * className EmpUserDaoImpl
 * packageName com.mod.dao.impl
 * Description 用户Dao层的实现类，完成底层功能的实现
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/18 21:00
 * @version 1.0
 */
public class EmpUserDaoImpl implements IEmpUserDao {
    /**
     * @description: 判断用户是否存在，用于用户登录之前。
     * @param empUserId 引用自登陆时输入的id，判断用户是否存在，不存在可直接提供注册跳转，存在再进行登录判断
     * @return: java.lang.Integer
     * i=0-----用户不存在
     * i=1-----用户存在
     * @author CYQH
     * @date: 2023/08/18 21:01
     */
    @Override
    public Integer countEmpUserById(Integer empUserId) {
        String sql = "SELECT count(*) AS count FROM tb_empuser WHERE empUserId = ?";
        Long l = (Long) BaseDao.countInfo(sql,empUserId);
        return l.intValue();
    }

    /**
     * @description: 进行用户登录判断，如果能查询到，返回一个对应的用户信息对象，如果密码错误返回一个空对象。
     * @param empUser 将登录信息（用户id和密码）封装成一个EmpUser对象
     * @return: com.myhd.pojo.EmpUser 登录成功返回一个具有数据库信息的对象
     * @author CYQH
     * @date: 2023/08/18 21:04
     */
    @Override
    public EmpUser selectEmpUSerByInfo(EmpUser empUser) {
        String sql = "SELECT empUserId,empUserPwd,empUserName,empUserDuty FROM tb_empuser WHERE empUserId = ? AND empUserPwd = ?";
        List list = BaseDao.selectInfo(sql,empUser.getClass(),empUser.getEmpUserId(),empUser.getEmpUserPwd());
        if (!list.isEmpty()){
            return (EmpUser) list.get(0);
        }else {
            empUser = new EmpUser();
            return empUser;
        }
    }
    /**
     * @description: 进行用户的注册,内分两种情况，未指定id注册和指定id注册
     * @param empUser 将注册信息封装成一个EmpUser对象
     * @return: java.lang.Integer 用户id
     * @author CYQH
     * @date: 2023/08/18 21:07
     */
    @Override
    public Integer insertEmpUser(EmpUser empUser) {
        if (empUser.getEmpUserId() == null){
            String sql = "INSERT INTO tb_empuser(empUserName,empUserDuty,empUserPwd) VALUES(?,?,?)";
            BaseDao.updateInfo(sql,empUser.getEmpUserName(),empUser.getEmpUserDuty(),empUser.getEmpUserPwd());
            String sql2 = "SELECT MAX(empUserId) AS count from tb_empuser";
            return (Integer) BaseDao.countInfo(sql2);
//            return  BaseDao.updateInfo(sql,empUser.getEmpUserName(),empUser.getEmpUserDuty(),empUser.getEmpUserPwd());
        }else {
            String sql = "INSERT INTO tb_empuser(empUserId,empUserName,empUserDuty,empUserPwd) VALUES(?,?,?,?)";
            BaseDao.updateInfo(sql,empUser.getEmpUserId(),empUser.getEmpUserName(),empUser.getEmpUserDuty(),empUser.getEmpUserPwd());
            return  empUser.getEmpUserId();
        }
    }
}
