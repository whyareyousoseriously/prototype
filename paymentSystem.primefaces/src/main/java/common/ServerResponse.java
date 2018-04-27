/**
 * 下午5:20:48
 * power
 */
package common;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author cz
 * 2018年4月25日下午5:20:48
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候，如果是null的对象，key也会消失
public class ServerResponse<T> implements Serializable{
  private  int status;
  private String msg;
  private T data;

  private ServerResponse(int status){
      this.status = status;
  }
  private ServerResponse(int status,T data){
      this.status = status;
      this.data = data;
  }
  private ServerResponse(int status,String msg,T data){
      this.status = status;
      this.msg = msg;
      this.data = data;
  }
  private ServerResponse(int status,String msg){
      this.status = status;
      this.msg = msg;
  }

  @JsonIgnore
  //使之不再json序列化之中。
  public boolean isSuccess(){
      return this.status == ResponseCode.SUCCESS.getCode();
  }

  public int getStatus(){
      return status;
  }
  public T getData(){
      return data;
  }
  public String getMsg(){
      return msg;
  }

  public static <T> ServerResponse<T> createBySuccess(){
	  //加入primeface的panel的提示信息
	  FacesContext context = FacesContext.getCurrentInstance();
	  context.addMessage(null, new FacesMessage("提示", "操作成功"));
      return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
  }

  public static <T> ServerResponse<T> createBySuccessMessage(String msg){
	  FacesContext context = FacesContext.getCurrentInstance();
	  context.addMessage(null, new FacesMessage("提示", msg));
      return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
  }

  public static <T> ServerResponse<T> createBySuccess(T data){
      return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
  }

  public static <T> ServerResponse<T> createBySuccess(String msg,T data){
	  FacesContext context = FacesContext.getCurrentInstance();
	  context.addMessage(null, new FacesMessage("提示", msg));
      return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
  }

  public static <T> ServerResponse<T> createByError(){
	  FacesContext context = FacesContext.getCurrentInstance();
	  context.addMessage(null, new FacesMessage("提示", ResponseCode.ERROR.getDesc()));
      return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
  }

  public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
	  FacesContext context = FacesContext.getCurrentInstance();
	  context.addMessage(null, new FacesMessage("提示", errorMessage));
      return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
  }

  public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
	  FacesContext context = FacesContext.getCurrentInstance();
	  context.addMessage(null, new FacesMessage("提示", errorMessage));
      return new ServerResponse<T>(errorCode,errorMessage);
  }
}
