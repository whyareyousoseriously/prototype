/**
 * 下午3:17:51
 * power
 */
package utils.dataValidator.root;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import utils.DataSearchUtil;

/**
 * @author cz
 *
 * 2018年3月10日下午3:17:51
 */
@FacesValidator("R_LoginUsernameValidator")
public class R_LoginUsernameValidator implements Validator, ClientValidator{

	/* (non-Javadoc)
	 * @see org.primefaces.validate.ClientValidator#getMetadata()
	 * @author cz
	 * @time 2018年3月10日下午3:18:34
	 */
	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.validate.ClientValidator#getValidatorId()
	 * @author cz
	 * @time 2018年3月10日下午3:18:34
	 */
	@Override
	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "R_LoginUsernameValidator";
	}

	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 * @author cz
	 * @time 2018年3月10日下午3:18:34
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		if(arg2 == null) {
			return;
		}
		if((arg2.toString()).length()<4||(arg2.toString()).length()>8) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",arg2+"用户名长度必须为4-8字符"));
		}else if(!DataSearchUtil.duplicateCheckingData("Root","username",arg2.toString())) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",arg2+"不存在"));
		}
			
		
	}

}
