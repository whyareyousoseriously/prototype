/**
 * 下午5:17:39
 * power
 */
package dao;

import pojo.Donation;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:17:39
 */
public interface IDonationDao {
	/**
	 * 顾名思义，这就不用注释了吧
	 * @param donation
	 * @return
	 * @author cz
	 * @time 2018年4月13日下午5:56:29
	 */
	Donation saveOrUpdateDonation(Donation donation);

}
