/**
 * 
 */
package dao;

import entity.Root;

/**
 * @author cz
 *
 * 2018年3月9日下午9:06:26
 */
public interface RootDAO {
	
	/**
	 * @param root
	 * @return
	 * @author cz
	 * @time 2018年3月9日下午9:28:06
	 */
	public Root rootLogin(Root root);
	
	/**
	 * @param root
	 * @return
	 * @author cz
	 * @time 2018年3月9日下午9:28:10
	 */
	public boolean rootRegister(Root root);
	
	/**
	 * @param mailCode
	 * @return
	 * @author cz
	 * @time 2018年3月9日下午9:28:12
	 */
	public Root findByMailCode(String mailCode);
	
	/**
	 * @param root
	 * @author cz
	 * @time 2018年3月9日下午9:28:15
	 */
	public void update(Root root);
	
}
