/**
 * 下午5:37:33
 * power
 */
package dao;

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
}
