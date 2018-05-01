/**
 * 下午4:21:52
 * power
 */
package pojo;

/**
 * 
 * @author cz
 * 2018年5月1日下午4:21:52
 */
public class QRCode {
	Long orderNo; //订单号
	String qrCodeUrl;//订单二维码
	public Long getOrderNo() {
		return orderNo;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
}
