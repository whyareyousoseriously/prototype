package utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by power on 2018/4/6.
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties properties;

    static{
        String fileName = "paymentSystem.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }
    public static String getProperty(String key){
        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key, String defalutValue){
        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return value = defalutValue;
        }
        return value.trim();
    }
    public PropertiesUtil(){

    }
}
