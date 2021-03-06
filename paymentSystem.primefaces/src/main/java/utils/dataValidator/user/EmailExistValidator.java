/**
 * 下午1:12:11
 * power
 */
package utils.dataValidator.user;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import utils.DataSearchUtil;

/**
 * 
 * @author cz 2018年4月26日下午1:12:11
 */
@FacesValidator("EmailExistValidator")
public class EmailExistValidator implements Validator, ClientValidator{
	private Pattern pattern;
	private static final String STUDENTID_PATTERN = "^\\w+([-_.]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,6})+$";

	public EmailExistValidator() {
		pattern = Pattern.compile(STUDENTID_PATTERN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.validate.ClientValidator#getMetadata()
	 * 
	 * @author cz
	 * 
	 * @time 2018年2月5日下午7:47:09
	 */
	@Override
	public Map<String, Object> getMetadata() {
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.validate.ClientValidator#getValidatorId()
	 * 
	 * @author cz
	 * 
	 * @time 2018年2月5日下午7:47:09
	 */
	@Override
	public String getValidatorId() {
		
		return "EmailExistValidator";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent, java.lang.Object)
	 * 
	 * @author cz
	 * 
	 * @time 2018年2月5日下午7:47:09
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		
		if (arg2 == null) {
			return;
		} else if (!pattern.matcher(arg2.toString()).matches()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "validator error", arg2 + "不合法"));
		} else if (!DataSearchUtil.duplicateCheckingData("User", "email", arg2.toString())) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "validator error", arg2 + "email不存在"));
		}
	}
}
