/**
 * 下午5:37:33
 * power
 */
package dao;

import java.util.Set;

import entity.ExcelFile;

/**
 * 文件操作的接口，文件量小，存数据库中
 * @author cz
 * 2018年4月8日下午5:37:33
 */
public interface ExcelFileDAO {
	/**
	 * 向数据库插入ExcelFile
	 * @param excelFile
	 * @return
	 * @author cz
	 * @time 2018年4月8日下午5:57:02
	 */
	String addFile(ExcelFile excelFile);
	
	/**
	 * 查看当前的root所属的所有文件
	 * @param rootId 当前root用户的id
	 * @return Set<ExcelFile> set集合
	 * @author cz
	 * @time 2018年4月10日上午11:28:11
	 */
	Set<ExcelFile> ListExcelFilesByCurrentRootID(String rootId);
}
