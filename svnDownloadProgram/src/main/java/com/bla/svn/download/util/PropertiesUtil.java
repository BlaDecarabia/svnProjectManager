package com.bla.svn.download.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/9 15:59
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public class PropertiesUtil {
    private static Properties properties;

    private static void initProperties() throws IOException {
        properties = new Properties();
        properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("system.properties"));
    }

    public static String getValueByKey(String key) throws IOException {
        if (null == properties) {
            initProperties();
        }

        return properties.getProperty(key);
    }
}
