package org.smart4j.chapter2.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + "file not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            LOGGER.info("文件加载失败:{}", e);
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.info("文件关闭失败:{}", e);
                }
            }
        }
        return props;
    }

    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }




}
