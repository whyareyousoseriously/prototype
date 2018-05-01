/**
 * 下午4:25:02
 * power
 */
package vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author cz
 * 2018年5月1日下午4:25:02
 */
@ManagedBean
@SessionScoped
public class QRCodeVo {
	String orderNo;
	String qrCode;
	public String getOrderNo() {
		return orderNo;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	
}
