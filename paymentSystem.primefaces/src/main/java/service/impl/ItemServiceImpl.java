/**
 * 下午10:59:36
 * power
 */
package service.impl;

import java.util.Date;
import java.util.List;

import common.Const;
import common.ServerResponse;
import dao.IItemDao;
import dao.impl.ItemDaoImpl;
import pojo.Item;
import service.IItemService;

/**
 * 
 * @author cz
 * 2018年4月28日下午10:59:36
 */
public class ItemServiceImpl implements IItemService {

	private IItemDao iItemDao = new ItemDaoImpl();
	/* (non-Javadoc)
	 * @see service.IItemService#saveItem(pojo.Item)
	 * @author cz
	 * @time 2018年4月28日下午11:00:29
	 */
	@Override
	public ServerResponse<Item> saveItem(Item item) {
		//不用检查了，一定是保存而不是更新，
		item.setCreateTime(new Date());
		item.setUpdateTime(new Date());
		Item item_feedback = iItemDao.saveOrUpdate(item);
		if(item_feedback!=null) {
			return ServerResponse.createBySuccess("保存支付条目成功", item_feedback);
		}else {
			return ServerResponse.createByErrorMessage("保存支付条目失败");
		}
	}
	/* (non-Javadoc)
	 * @see service.IItemService#listItemByManagerId(java.lang.String)
	 * @author cz
	 * @time 2018年4月28日下午11:27:37
	 */
	@Override
	public ServerResponse<List<Item>> listItemByManagerId(String managerId) {
		List<Item> itemList = iItemDao.listItemByManagerId(managerId);
		if(itemList.isEmpty()) {
			//获取详细失败没有存储详细信息
			return ServerResponse.createByErrorMessage("获取支付条目信息失败没有存储支付条目");
		}else {
			return ServerResponse.createBySuccess("获取支付条目信息成功", itemList);
		}
	}
	/* (non-Javadoc)
	 * @see service.IItemService#updateItem(pojo.Item)
	 * @author cz
	 * @time 2018年4月29日上午12:10:11
	 */
	@Override
	public ServerResponse<Item> updateItem(Item item) {
		//更新前进行检查，是否允许被更新
		//检查是否已发布
		Item check = iItemDao.selectItemByItemId(item.getItemId());
		if(check.getActive()==Const.ItemStatus.ACTIVE.getCode()) {
			//已经发布，不能再修改了
			return ServerResponse.createByErrorMessage("已经发布，不允许修改");
		}else {
			//未发布，还可以修改
			item.setUpdateTime(new Date());
			Item item_feedback = iItemDao.saveOrUpdate(item);
			if(item_feedback!=null) {
				return ServerResponse.createBySuccess("更新条目成功",item_feedback);
			}else {
				return ServerResponse.createByErrorMessage("更新支付条目失败");
			}
		}
	}
	/* (non-Javadoc)
	 * @see service.IItemService#deleteItem(pojo.Item)
	 * @author cz
	 * @time 2018年4月29日上午1:18:38
	 */
	@Override
	public ServerResponse<Item> deleteItemByItemId(Item item) {
		Item item_feedback = iItemDao.deleteItemByItemId(item.getItemId());
		if(item_feedback==null) {
			//删除失败
			return ServerResponse.createByErrorMessage("删除失败，未找到要删除的条目");
		}else {
			return ServerResponse.createBySuccess("删除成功", item_feedback);
		}
	}
	/* (non-Javadoc)
	 * @see service.IItemService#onLineItem(pojo.Item)
	 * @author cz
	 * @time 2018年4月29日下午12:25:26
	 */
	@Override
	public ServerResponse<Item> onLineItem(Item item) {
		item.setUpdateTime(new Date());
		Item item_feedback = iItemDao.saveOrUpdate(item);
		if(item_feedback!=null) {
			return ServerResponse.createBySuccess("上线条目成功",item_feedback);
		}else {
			return ServerResponse.createByErrorMessage("上线支付条目失败");
		}
	}
	/* (non-Javadoc)
	 * @see service.IItemService#outLineItem(pojo.Item)
	 * @author cz
	 * @time 2018年4月29日下午3:39:47
	 */
	@Override
	public ServerResponse<Item> outLineItem(Item item) {
		item.setUpdateTime(new Date());
		Item item_feedback = iItemDao.saveOrUpdate(item);
		if(item_feedback!=null) {
			return ServerResponse.createBySuccess("下线条目成功",item_feedback);
		}else {
			return ServerResponse.createByErrorMessage("下线支付条目失败");
		}
	}

}
