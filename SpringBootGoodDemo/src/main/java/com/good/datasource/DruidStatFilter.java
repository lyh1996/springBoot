package com.good.datasource;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @update 2018/8/27
 * @since 1.7
 */

import javax.servlet.annotation.WebFilter;
import com.alibaba.druid.support.http.WebStatFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器.
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
        }
)
public class DruidStatFilter  extends WebStatFilter {
}

