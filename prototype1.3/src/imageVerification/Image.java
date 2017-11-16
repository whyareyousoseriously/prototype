package imageVerification;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ������֤��ͼƬ������
 * @author ZhangCheng on 2017-07-09
 *
 */
public class Image {
	
	private static final Logger log= LoggerFactory.getLogger(Image.class);

    private static Map<String,ImageGroup> imageGroupMap=new HashMap<>();
    private static Map<Integer,Map<String,ImageGroup>> countGroupMap=new HashMap<>();
	
//	private static Map<String,ImageGroup> imageGroupMap = new HashMap<String,ImageGroup>();
//	private static Map<Integer,Map<String,ImageGroup>> countGroupMap = new HashMap<Integer,Map<String,ImageGroup>>();
//	
	/**
	 * ���ܣ���Сͼ����һ�ִ�ͼ
	 * @return
	 * @throws IOException
	 */
	public static ImageResult generateImage()throws IOException{
		// ��ʼ��
		initImageGroup();
		log.debug("��ʼ�����");
		GenerateImageGroup generateImageGroup = randomImageGroups();
		List<BufferedImageWrap> images = new ArrayList<BufferedImageWrap>();
		// �ҵ�ͼƬ������
		for (ImageGroup group : generateImageGroup.getGroups()) {
			for (String imgName : group.getImages()) {
				images.add(new BufferedImageWrap(false,getBufferedImage(imgName)));
			}
		}
		// �ҵ�ͼƬ����
		for(String imgName : generateImageGroup.getKeyGroup().getImages()){
			images.add(new BufferedImageWrap(true,getBufferedImage(imgName)));
		}
		return mergeImage(images,generateImageGroup.getKeyGroup().getName());
	}

	/**
	 * ���ܣ�����ͼƬ���ƻ��ͼƬ������
	 * @param imgName
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage getBufferedImage(String imgName)throws IOException {
		String rootPath = Image.class.getClassLoader().getResource("sourceImage/").getPath();
		String imgPath = rootPath + imgName;
		File file = new File(imgPath);
		return ImageIO.read(file);
	}
	
	/**
	 * ���ܣ���Сͼ�ϲ���һ�ִ�ͼ
	 * @param images
	 * @param name
	 * @return
	 */
	private static ImageResult mergeImage(List<BufferedImageWrap> imageWraps, String tip) {
		Collections.shuffle(imageWraps);
		// ԭʼͼƬ��200���أ���200����
		int width = 200;
		int high = 200;
		int totalWidth = width * 4;
		
		BufferedImage destImage = new BufferedImage(totalWidth,400,BufferedImage.TYPE_INT_RGB);
		int x1 = 0;
		int x2 = 0;
		int order = 0;
		List<Integer> keysOrderList = new ArrayList<Integer>();
		StringBuilder keysOrder = new StringBuilder();
		Set<Integer> keySet = new HashSet<Integer>();
		for(BufferedImageWrap image : imageWraps){
			int[] rgb = image.getBufferedImage().getRGB(0, 0, width, high, null, 0, width);
			if(image.isKey()){
				keysOrderList.add(order);
				int x = (order % 4) * 200;
				int y = order < 4 ? 0:200;
				keySet.add(order);
				keysOrder.append(order).append("(").append(x).append(",").append(y).append(")|");
			}
			if(order < 4 ){
				// �����ϰ벿�ֵ�RGB
				destImage.setRGB(x1, 0, width,high,rgb,0,width);
				x1 += width;
			}else{
				destImage.setRGB(x2, high, width,high,rgb,0,width);
				x2 += width;
			}
			order++;
		}
		
		keysOrder.deleteCharAt(keysOrder.length() - 1);
		System.out.println("��λ�ã�" + keysOrder);
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".jpeg";
		String rootPath = Image.class.getClassLoader().getResource("static/targetImage/").getPath();
		//String rootPath = Image.class.getClassLoader().getResource("sourceImage/").getPath();
		log.info("��·����{}",rootPath);
		String fileUrl = rootPath + fileName;
		// ����ͼƬ
		saveImage(destImage,fileUrl,"png");
		
		ImageResult ir = new ImageResult();
		ir.setName(fileName);
		ir.setKeySet(keySet);
		ir.setUniqueKey(fileName);
		ir.setTip(tip);
		return ir;
	}
	
	/**
	 * ���ܣ���ͼƬд��ָ����·��
	 * @param destImage
	 * @param fileUrl
	 * @param string
	 */
	private static void saveImage(BufferedImage destImage, String fileUrl, String format) {
		File file=new File(fileUrl);
        log.debug(file.getAbsolutePath());
        try {
			ImageIO.write(destImage,format,file);
		} catch (IOException e) {
			log.info("ͼƬд��ʧ��");
			e.printStackTrace();
		}
	}

	/**
	 * ���ܣ��������ͼƬ�𰸺͸�����
	 * @return
	 */
	private static GenerateImageGroup randomImageGroups(){
		
		List<ImageGroup> result = new ArrayList<ImageGroup>();
		int num = random(0, imageGroupMap.size() - 1);
		
		String name = new ArrayList<String>(imageGroupMap.keySet()).get(num);
		ImageGroup keyGroup = imageGroupMap.get(name);
		
		Map<Integer,Map<String,ImageGroup>> thisCountGroupMap = new HashMap<>(countGroupMap);
		thisCountGroupMap.get(keyGroup.getCount()).remove(name);
		
		// ��������8����ÿ������ͼƬֻ��2����4����Ϊ���߼���Щ
		int leftCount = 8 - keyGroup.getCount();
		if(leftCount == 4){
			if(new Random().nextInt() % 2 == 0){
				List<ImageGroup> groups = new ArrayList<ImageGroup>(thisCountGroupMap.get(4).values());
				if(groups.size() > 1){
					num = random(0, groups.size() - 1);
				}else{
					num = 0;
				}
				result.add(groups.get(num));
			}else{
				List<ImageGroup> groups = new ArrayList<ImageGroup>(thisCountGroupMap.get(2).values());
				int num1 = random(0, groups.size() - 1);
				result.add(groups.get(num1));
				
				int num2 = random(0, groups.size() - 1,num1);
				result.add(groups.get(num2));
			}
		}else if(leftCount == 6){
			if(new Random().nextInt() % 2 == 0){
				List<ImageGroup> groups1 = new ArrayList<ImageGroup>(thisCountGroupMap.get(4).values());
				int num1 = random(0, groups1.size() - 1);
				result.add(groups1.get(num1));
				
				List<ImageGroup> groups2 = new ArrayList<ImageGroup>(thisCountGroupMap.get(2).values());
				int num2 = random(0, groups2.size() - 1);
				result.add(groups2.get(num2));
			}else{
				List<ImageGroup> groups = new ArrayList<ImageGroup>(thisCountGroupMap.get(2).values());
				int num1 = random(0, groups.size() - 1);
				result.add(groups.get(num1));
				
				int num2 = random(0, groups.size() - 1,num1);
				result.add(groups.get(num2));
				
				int num3 = random(0, groups.size() - 1,num1,num2);
				result.add(groups.get(num3));
			}
		}
		
		return new GenerateImageGroup(keyGroup, result);
		
	}
	
	/**
	 * ���ܣ���ʼ��ͼƬ�顣�����Ż��ɴ����ݿ��ȡ
	 */
	private static void initImageGroup(){
		ImageGroup group1 = new ImageGroup("����",4,"bao/1.jpg","bao/2.jpg","bao/3.jpg","bao/4.jpg");
		ImageGroup group2 = new ImageGroup("�ϻ�",4,"laohu/1.jpg","laohu/2.jpg","laohu/3.jpg","laohu/4.jpg");
		ImageGroup group3 = new ImageGroup("�Ǻ�«",4,"tanghulu/1.jpg","tanghulu/2.jpg","tanghulu/3.jpg","tanghulu/4.jpg");
		ImageGroup group4 = new ImageGroup("СĽ",4,"xiaomu/1.jpg","xiaomu/2.jpg","xiaomu/3.jpg","xiaomu/4.jpg");
		ImageGroup group5 = new ImageGroup("����",4,"youzi/1.jpg","youzi/2.jpg","youzi/3.jpg","youzi/4.jpg");
		ImageGroup group6 = new ImageGroup("�����",2,"dingshuji/1.jpg","dingshuji/2.jpg");
		ImageGroup group7 = new ImageGroup("Ģ��",2,"mogu/1.jpg","mogu/2.jpg");
		ImageGroup group8 = new ImageGroup("����",2,"citie/1.jpg","citie/2.jpg");
		ImageGroup group9 = new ImageGroup("����",4,"tudou/1.jpg","tudou/2.jpg","tudou/3.jpg","tudou/4.jpg");
		ImageGroup group10 = new ImageGroup("����",4,"tuzi/1.jpg","tuzi/2.jpg","tuzi/3.jpg","tuzi/4.jpg");
		ImageGroup group11 = new ImageGroup("������",4,"xianrenqiu/1.jpg","xianrenqiu/2.jpg","xianrenqiu/3.jpg","xianrenqiu/4.jpg");
		
		initMap(group1,group2,group3,group4,group5,group6,group7,group8,group9,group10,group11);
	}
	
	/**
	 * ���ܣ���ʼ������ͼƬ��
	 * @param groups
	 */
	private static void initMap(ImageGroup... groups) {
		for (ImageGroup group : groups) {
			imageGroupMap.put(group.getName(),group);
			if(!countGroupMap.containsKey(group.getCount())){
				countGroupMap.put(group.getCount(),new HashMap<String,ImageGroup>());
			}
			countGroupMap.get(group.getCount()).put(group.getName(),group);
		}
	}
	
	/**
	 * ���ܣ������������
	 * @param min
	 * @param max
	 * @return
	 */
	private static int random(int min,int max){
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * ���ܣ����������������ָ������������
	 * @param min
	 * @param max
	 * @param not
	 * @return
	 */
	private static int random(int min,int max,Integer... not){
		int num = random(min,max);
		List<Integer> notList = Arrays.asList(not);
		while(notList.contains(num)){
			num = random(min,max);
		}
		return num;
	}
}