package imageVerification;

import java.util.Set;

/**
 * ͼƬ�����
 * @author ZhangCheng on 2017-07-09
 *
 */
public class ImageResult {
	/** ͼƬ���� */
	private String name;
	/**  */
	private Set<Integer> keySet;
	/** Ψһ��ʶ */
	private String uniqueKey;
	/** ��Ҫѡ�е�ͼƬ��ʾ */
	private String tip;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Integer> getKeySet() {
		return keySet;
	}
	public void setKeySet(Set<Integer> keySet) {
		this.keySet = keySet;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	@Override
	public String toString() {
		return "ImageResult [name=" + name + ", keySet=" + keySet + ", uniqueKey=" + uniqueKey + ", tip=" + tip + "]";
	}
	
}

