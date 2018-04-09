/**
 * 
 */
package dao;


import java.util.Set;

import entity.ExcelFile;
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
	Root rootLogin(Root root);
	
	/**
	 * @param root
	 * @return
	 * @author cz
	 * @time 2018年3月9日下午9:28:10
	 */
	boolean rootRegister(Root root);
	
	/**
	 * @param mailCode
	 * @return
	 * @author cz
	 * @time 2018年3月9日下午9:28:12
	 */
	Root findByMailCode(String mailCode);
	
	/**
	 * @param root
	 * @author cz
	 * @time 2018年3月9日下午9:28:15
	 */
	void update(Root root);
	
	/**
	 * 拿到表中的属于自己的Root
	 * 然后遍历Root.getItem(),即可得到自己所有的Item
	 * @return Root
	 * @author cz
	 * @time 2018年3月13日下午7:19:10
	 */
	Root getOwnRoot(String id);
	
	/**
	 * 由于hibernate接管session,而且属于懒加载，所以在bean中进行双表查询出现，nosession异常
	 * 所以在一次交互中拿到items的数据
	 * @param id
	 * @return
	 * @author cz
	 * @time 2018年3月14日下午7:28:18
	 */
	Set<Item> getOwnItem(String id);
	
	/**
	 * 拿到root用户从属的所有文件
	 * @param rootID
	 * @return
	 * @author cz
	 * @time 2018年4月8日下午5:25:23
	 */
	Set<ExcelFile> getOwnFile(String rootID);
}
