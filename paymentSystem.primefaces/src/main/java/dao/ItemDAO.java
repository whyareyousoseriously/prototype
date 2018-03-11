/**
 * 下午6:42:08
 * power
 */
package dao;

import java.util.List;

import entity.Item;

/**
 * @author cz
 *
 * 2018年3月10日下午6:42:08
 */
public interface ItemDAO {
	 /**
	 * 增加支付条目
	 * @param item
	 * @return 函数执行的结果
	 * @author cz
	 * @time 2018年3月11日下午6:33:12
	 */
	public String addItem(Item item);
	
	/**
	 * 条件删除
	 * @param condition 条件删除的条件
	 * @param conditionValue 条件删除的条件的值
	 * @return 函数执行的结果
	 * @author cz
	 * @time 2018年3月11日下午6:35:50
	 */
	public String deleteItemByCondition(String condition, String conditionValue);
	
	/**
	 * 条件更新
	 * @param condition
	 * @param conditionValue
	 * @return
	 * @author cz
	 * @time 2018年3月11日下午6:37:20
	 */
	public String updateItemByCondition(String condition, String conditionValue);
	
	/**
	 * 条件查询
	 * @param condition
	 * @param conditionValue
	 * @return
	 * @author cz
	 * @time 2018年3月11日下午6:40:22
	 */
	public List<Item> getItemByCondition(String condition, String conditionValue); 
	
}
