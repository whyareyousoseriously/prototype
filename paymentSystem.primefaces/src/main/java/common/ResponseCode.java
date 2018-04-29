/**
 * 下午5:19:21
 * power
 */
package common;

/**
 * 响应编码枚举类
 * @author cz
 * 2018年4月25日下午5:19:21
 */
public enum ResponseCode {
	SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
