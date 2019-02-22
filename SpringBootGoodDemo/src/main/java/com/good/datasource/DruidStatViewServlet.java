package com.good.datasource;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @update 2018/8/27
 * @since 1.7
 */

import javax.servlet.annotation.WebInitParam;
import com.alibaba.druid.support.http.StatViewServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
/**
 * druid数据源状态监控.
 * @author Administrator
 */
@WebServlet(urlPatterns="/druid/*",
        initParams={
                @WebInitParam(name="allow",value="192.168.226.128,127.0.0.1"),// IP白名单(没有配置或者为空，则允许所有访问)
                @WebInitParam(name="deny",value="192.168.1.73"),// IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="admin"),// 用户名
                @WebInitParam(name="loginPassword",value="admin"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        }
)
public class DruidStatViewServlet  extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}
