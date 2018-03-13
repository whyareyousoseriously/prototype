/**
 * 
 */
package dao;

import java.util.List;

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
	 * 拿到表中的属于自己的Root
	 * 然后遍历Root.getItem(),即可得到自己所有的Item
	 * @return Root
	 * @author cz
	 * @time 2018年3月13日下午7:19:10
	 */
	public Root getOwnRoot(String id);
}
