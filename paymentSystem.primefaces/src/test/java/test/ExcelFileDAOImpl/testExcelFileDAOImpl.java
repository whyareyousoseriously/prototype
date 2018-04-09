/**
 * 下午5:54:54
 * power
 */
package test.ExcelFileDAOImpl;

import java.util.Date;

import org.junit.Test;

import dao.ExcelFileDAO;
import dao.impl.ExcelFileDAOImpl;
import entity.ExcelFile;

/**
 * 
 * @author cz
 * 2018年4月8日下午5:54:54
 */
public class testExcelFileDAOImpl {

	@Test
	public void testAddFile() {
		ExcelFile excelFile = new ExcelFile();
		excelFile.setCreateTime(new Date());
		ExcelFileDAO efdao = new ExcelFileDAOImpl();
		efdao.addFile(excelFile);
	}
}
