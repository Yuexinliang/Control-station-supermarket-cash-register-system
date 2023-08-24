package com.myhd.exception;

/**
 * className TimeCheckException
 * packageName com.myhd.exception
 * Description 时间判断异常类
 *
 * @author CYQH
 * @email 1660855825@qq.com
 * @Date 2023/08/20 18:38
 * @version 1.0
 */
public class TimeCheckException extends RuntimeException {
    /**
     * @description: 时间判断异常无参构造
     * @author CYQH
     * @date: 2023/08/20 18:38
     */
    public TimeCheckException() {

    }
    /**
     * @description: 时间判断异常有参构造
     * @param message 错误信息
     * @return:
     * @author CYQH
     * @date: 2023/08/20 18:39
     */
    public TimeCheckException(String message) {
        super(message);
    }
}
