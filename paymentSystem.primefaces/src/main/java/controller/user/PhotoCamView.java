/**
 * 下午9:34:40
 * power
 */
package controller.user;

/**
 * 
 * @author cz
 * 2018年4月27日下午9:34:40
 */
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import org.primefaces.event.CaptureEvent;
 
@ManagedBean
@ViewScoped
public class PhotoCamView implements Serializable{
     
    /**
	 * 下午12:46:16
	 * power
	 */
	private static final long serialVersionUID = -7338190175798254307L;
	private String filename;
     
    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
         
        return String.valueOf(i);
    }
 
    public String getFilename() {
        return filename;
    }
     
    public void oncapture(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
                                    File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";
        String newFileName2 = "e:" + File.separator + "resources" + File.separator + "demo" +
                File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";
        String newFilepath2 = "d:\\ftpFile\\img\\demo\\images\\photocam\\";
        File fileDir = new File(newFilepath2);
		if (!fileDir.exists()) {
			fileDir.setWritable(true);
			fileDir.mkdirs();
		}
		File tragetFile = new File(newFilepath2,filename+".jpeg");
		
        FileImageOutputStream imageOutput;
        try {
            //imageOutput = new FileImageOutputStream(new File(newFileName2));
            imageOutput = new FileImageOutputStream(tragetFile);
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }
}
