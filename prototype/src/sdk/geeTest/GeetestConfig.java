package sdk.geeTest;

public class GeetestConfig {
	// 填入自己的captcha_id和private_key
	private static final String geetest_id = "aaa5bca096fbb413322b49e9e8935eda";
	private static final String geetest_key = "bd21f4270485f235b40e9d60e24111d7";
	private static final boolean newfailback = true;

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}

	public static final boolean isnewfailback() {
		return newfailback;
	}

}
