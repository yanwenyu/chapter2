package org.smart4j.chapter2.happper;

import org.smart4j.chapter2.constant.ConfigConstant;
import org.smart4j.chapter2.utils.PropsUtil;

import java.util.Properties;

public class ConfigHapper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    private String getDriver(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }
    private String getURL(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }
    private String getUser(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }
    private String getPassWord(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }
    private String getBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.BASEPACKAGE);
    }
    private String getJSPPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JSPPATH,"/WEB-INF/view/");
    }
    private String getAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JSPPATH,"/asset/");
    }

}
