package imageVerification;

import java.util.List;

/**
 * ������֤ʹ��ͼƬ��
 * 
 *
 */
public class GenerateImageGroup {
	/** ��Ҫѡ�е�ͼƬ�� */
	private ImageGroup keyGroup;
	
	/** ���ɴ�ͼ��СͼͼƬ�� */
	private List<ImageGroup> groups;

	public GenerateImageGroup(ImageGroup keyGroup, List<ImageGroup> groups) {
		this.keyGroup = keyGroup;
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "GenerateImageGroup [keyGroup=" + keyGroup + ", groups=" + groups + "]";
	}

	public ImageGroup getKeyGroup() {
		return keyGroup;
	}

	public void setKeyGroup(ImageGroup keyGroup) {
		this.keyGroup = keyGroup;
	}

	public List<ImageGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ImageGroup> groups) {
		this.groups = groups;
	}
	
	
}