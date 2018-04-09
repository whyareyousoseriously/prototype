/**
 * 下午6:42:08
 * power
 */
package dao;

import java.util.List;

import entity.Item;
import entity.Root;

/**
 * 支付条目的操作接口
 * @author cz
 *
 * 2018年3月10日下午6:42:08
 */
public interface ItemDAO {
	 /**
	 * 增加支付条目
	 * @param item 增加的条目
	 * @return 函数执行的结果
	 * @author cz
	 * @time 2018年3月11日下午6:33:12
	 */
	public String addItem(Item item);
	
	/**
	 * 条件删除
	 * @param searchCondition 条件删除的条件
	 * @param searchValue 条件删除的条件的值
	 * @return 函数执行的结果
	 * @author cz
	 * @time 2018年3月11日下午6:35:50
	 */
	public String deleteItemByCondition(String SearchCondition, String SearchValue);
	
	
	/**
	 * 条件更新
	 * 先查找 再更新
	 * @param searchCondition 条件更新的查找条件
	 * @param searchValue 条件更新的查找条件的值
	 * @param updateCondition 条件更新的条件
	 * @param updateValue 条件更新的值
	 * @return
	 * @author cz
	 * @time 2018年3月11日下午7:48:01
	 */
	public String updateItemByCondition(String searchCondition, String searchValue, String updateCondition,String updateValue);
	
	/**
	 * 条件查询
	 * @param searchCondition
	 * @param searchValue
	 * @return 满足条件的集合
	 * @author cz
	 * @time 2018年3月11日下午6:40:22
	 */
	public List<Item> getItemByCondition(String searchCondition, String searchValue); 
	
}
