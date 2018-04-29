/**
 * 下午5:16:45
 * power
 */
package common;

/**
 * 常量类
 * @author cz
 * 2018年4月25日下午5:16:45
 */
public class Const {
	public static final String CURRENT_USER="currentUser";
	public static final String CURRENT_MANAGER="currentManager";
	public static final String USERNAME="username";
	public static final String MANAGERNAME="managerName";
	public static final String EMAIL="email";
	public static final String ACCOUNTID="accountId";
	public static final Integer UN_ACTIVE=0;
	public static final Integer ACTIVE=1;
	public static final Integer MAN = 1;
	public static final Integer FEMALE = 0;
	
	public enum AccountStatus{
		ACTIVE(1,"已激活"),
		UNACTIVE(0,"未激活");
		AccountStatus(int code,String value){
			this.code=code;
			this.value=value;
		}
		private String value;
		private int code;
		public String getValue() {
			return value;
		}
		
		public int getCode() {
			return code;
		}
	}
	public enum PayStatus{
		NO_PAY(10,"未支付"),
		PAID(20,"已付款"),
		UNKNOWN(0,"未知");
		PayStatus(int code,String value){
			this.code=code;
			this.value=value;
		}
		private String value;
		private int code;
		public String getValue() {
			return value;
		}
		public int getCode() {
			return code;
		}
	}
	public enum ItemStatus{
		ACTIVE(1,"已上线"),
		UNACTIVE(0,"未上线");
		
		ItemStatus(int code,String value){
			this.code=code;
			this.value=value;
		}
		private String value;
		private int code;
		public String getValue() {
			return value;
		}
		public int getCode() {
			return code;
		}
		
	}
	public enum PayPlatformEnum{
        
		ALIPAY(1,"支付宝"),
		WECHAT(0,"微信");
		
        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

}
