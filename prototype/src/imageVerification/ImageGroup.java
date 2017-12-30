package imageVerification;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ԭʼͼƬ����
 * @author ZhangCheng on 2017-07-09
 *
 */
public class ImageGroup {
	/** ͼƬ������ */
	private String name;
	/** ͼƬ���� */
	private int count;
	/** ͼƬURL�� */
	private Set<String> images;

	public ImageGroup(String name, int count, String... imageNames) {
		this.name = name;
		this.count = count;
		this.images = new HashSet<String>();
		this.images.addAll(Arrays.asList(imageNames));
	}

	@Override
	public String toString() {
		return "ImageGroup [name=" + name + ", count=" + count + ", images=" + images + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}
	
	
	
}

