package imageVerification;

import java.awt.image.BufferedImage;

/**
* ͼƬ��װ��
* 
*
*/
public class BufferedImageWrap {
	/** �Ƿ�Ϊ���� */
	private boolean key;
	/** ͼƬ������ */
	private BufferedImage bufferedImage;

	public BufferedImageWrap() {
	}

	public BufferedImageWrap(boolean key, BufferedImage bufferedImage) {
		this.key = key;
		this.bufferedImage = bufferedImage;
	}

	@Override
	public String toString() {
		return "BufferedImageWrap [key=" + key + ", bufferedImage=" + bufferedImage + "]";
	}

	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
}

