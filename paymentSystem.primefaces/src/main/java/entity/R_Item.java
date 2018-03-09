/**
 * 
 */
package entity;

/**
 * @author cz
 *
 * 2018年3月9日下午9:00:19
 */
public class R_Item {
	private int uid;
	private String payment_name;
	private String payment_statuts;
	private Root root;
	public R_Item() {
		super();
	}
	public R_Item(int uid, String payment_name, String payment_statuts, Root root) {
		super();
		this.uid = uid;
		this.payment_name = payment_name;
		this.payment_statuts = payment_statuts;
		this.root = root;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getPayment_name() {
		return payment_name;
	}
	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}
	public String getPayment_statuts() {
		return payment_statuts;
	}
	public void setPayment_statuts(String payment_statuts) {
		this.payment_statuts = payment_statuts;
	}
	public Root getRoot() {
		return root;
	}
	public void setRoot(Root root) {
		this.root = root;
	}
	@Override
	public String toString() {
		return "R_Item [uid=" + uid + ", payment_name=" + payment_name + ", payment_statuts=" + payment_statuts
				+ ", root=" + root + "]";
	}
	
}
