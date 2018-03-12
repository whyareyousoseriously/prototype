/**
 * 
 */
package dao;

import entity.Item;
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
	
	/**
	 * root创建一条支付条目
	 * @param root 创建者
	 * @param item 被创建的一条条目
	 * @return
	 * @author cz
	 * @time 2018年3月12日下午2:28:01
	 */
	public String addSingleItem(Root root, Item item);
}
