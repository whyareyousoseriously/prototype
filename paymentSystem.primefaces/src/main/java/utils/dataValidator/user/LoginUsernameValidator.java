package utils.dataValidator.user;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import utils.DataSearchUtil;
/* 用户登陆用户名检查，是否符合用户名规范，是否存在于数据库中
 * @author cz
 * @date 2018-1-23
 * */
@FacesValidator("LoginUsernameValidator")
public class LoginUsernameValidator implements Validator, ClientValidator {

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "LoginUsernameValidator";
	}
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		if(arg2 == null) {
			return;
		}
		if((arg2.toString()).length()<4||(arg2.toString()).length()>8) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",arg2+"用户名长度必须为4-8字符"));
		}else if(!DataSearchUtil.duplicateCheckingData("User","username",arg2.toString())) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",arg2+"不存在"));
		}
	}
}
