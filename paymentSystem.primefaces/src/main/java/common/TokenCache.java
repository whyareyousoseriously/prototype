/**
 * 下午12:36:09
 * power
 */
package common;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 用guava包中的方法，操纵缓存cache,用于邮箱验证码修改密码的过期检验
 * @author cz
 * 2018年4月26日下午12:36:09
 */
public class TokenCache {
	public static final String TOKEN_PREFIX = "token_";
    private static Logger  logger = LoggerFactory.getLogger(TokenCache.class);

    //LRU算法
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                //默认的数据实现，当调用的get取值的时候，如果没有key对应的值，就调用这个方法进行加载。
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){

        String value = null;
        try{
            value =localCache.get(key);
            if("null".equals(value)){
                return null;
            }
            return value;

        }catch (Exception e){
            logger.error("localCache get error",e);

        }
        return null;
    }
}
