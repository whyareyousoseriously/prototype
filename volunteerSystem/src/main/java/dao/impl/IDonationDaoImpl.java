/**
 * 下午5:18:39
 * power
 */
package dao.impl;

import dao.IDonationDao;
import pojo.Donation;
import utils.db.DBOperation;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:18:39
 */
public class IDonationDaoImpl implements IDonationDao {

	/* (non-Javadoc)
	 * @see dao.IDonationDao#saveOrUpdateDonation(pojo.Donation)
	 * @author cz
	 * @time 2018年4月13日下午5:56:52
	 */
	@Override
	public Donation saveOrUpdateDonation(Donation donation) {
		//就是这么easy这么简单
		return (Donation) DBOperation.saveOrUpdateData("Donation", donation);
	}

}
