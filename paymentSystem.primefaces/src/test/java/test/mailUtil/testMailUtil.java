/**
 * 
 */
package test.mailUtil;

import org.junit.Test;

import utils.MailUtil;

/**
 * @author cz
 *
 * 2018年2月2日下午2:44:39
 */
public class testMailUtil {
	@Test
	public void testMailUtilSendMail() {
		MailUtil mailUtil = new MailUtil();
		mailUtil.sendMail("826033956@qq.com", "test123456");
	}
}
