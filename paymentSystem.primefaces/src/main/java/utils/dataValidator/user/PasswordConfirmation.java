/**
 *暂时不用 
 */
package utils.dataValidator.user;

/**
 * @author cz
 *
 * 2018年2月5日下午3:50:28
 */
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("passwordConfirmation")
public class PasswordConfirmation implements Validator, ClientValidator {

	/*
	 * 用户密码规范性检查֤
	 * 1.必修为6-12位字母加数字的组合
	 * 
	 * @author cz
	 * @data 2017-1-23
	 */
	private Pattern pattern;
	private static final String STUDENTID_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";

	public PasswordConfirmation() {
		pattern = Pattern.compile(STUDENTID_PATTERN);
	}

	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "passwordConfirmation";
	}

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub

		if (value == null) {
			return;
		}
		if (!pattern.matcher(value.toString()).matches()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", value + "密码不合规范必须为6-12位字母+数字组合"));
		}
	}

}

