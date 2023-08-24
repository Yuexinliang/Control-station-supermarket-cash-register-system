package com.myhd.service.impl;

import com.myhd.dao.IEmpUserDao;
import com.myhd.dao.impl.EmpUserDaoImpl;
import com.myhd.exception.LoginException;
import com.myhd.exception.RegisterException;
import com.myhd.pojo.EmpUser;
import com.myhd.service.IEmpUserService;

public class EmpUserServiceImpl implements IEmpUserService {
    IEmpUserDao eud = new EmpUserDaoImpl();

    /**
     * @description: 登录功能，先判断用户id存不存在，不存在返回提示，存在
     * 再进行密码验证。
     * @param empUser 将登录信息（用户id和密码）封装成一个EmpUser对象
     * @return: java.lang.Boolean
     * true-----返回登录成功
     * false ----密码不正确或id不存在
     * @author CYQH
     * @date: 2023/08/20 13:26
     */
    @Override
    public Boolean LoginService(EmpUser empUser) {
        if (eud.countEmpUserById(empUser.getEmpUserId()) == 0 ){
            try {
                throw new LoginException("该用户id不存在");
            }catch (LoginException e){
                e.printStackTrace();
            }
            return false;
        }else {
            /*判断用户密码是否正确*/
            if (eud.selectEmpUSerByInfo(empUser).getEmpUserId() == null){
                return false;
            }else {
                return true;
            }
        }
    }

    /**
     * @description: 用户注册功能，可根据注册id是否已存在进行指定id注册或进行
     * 默认id注册
     * @param empUser 将注册信息（姓名，密码，职位，有可能包含id）封装成一个对象
     * @return: java.lang.Boolean
     * true----注册成功
     * false----注册失败，可能是用户id已存在
     * @author CYQH
     * @date: 2023/08/20 13:57
     */
    @Override
    public Boolean RegisterService(EmpUser empUser) {
        if (empUser.getEmpUserId() == null){
            Integer empUserId = eud.insertEmpUser(empUser);
            System.out.println("***************************************");
            System.out.println("*                注册成功               *");
            System.out.println("*您的用户id为：   (☞ﾟヮﾟ)☞"+empUserId+"☜(ﾟヮﾟ☜)*");
            System.out.println("****************************************");
            return true;
        }else {
            if (eud.countEmpUserById(empUser.getEmpUserId()) == 1 ){
                try {
                    throw new RegisterException("该用户id已存在");
                }catch (RegisterException e){
                    e.printStackTrace();
                }
                return false;
            }else {
                System.out.println("您的用户id为："+eud.insertEmpUser(empUser));
                    return true;
            }
        }
    }

    /**
     * @description: 根据登录信息查询用户具体信息
     * @param empUser 登录信息封装为一个用户对象
     * @return: com.myhd.pojo.EmpUser 返回登录用户的所有信息
     * @author CYQH
     * @date: 2023/08/20 20:46
     */
    @Override
    public EmpUser QueryUser(EmpUser empUser) {
        return eud.selectEmpUSerByInfo(empUser);
    }
}
