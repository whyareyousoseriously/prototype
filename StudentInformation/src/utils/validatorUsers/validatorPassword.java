package utils.validatorUsers;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validatorPassword")
public class validatorPassword implements Validator, ClientValidator {
	/*
	 * 对注册表password提供验证
	 * 1.长度为6-12位，字母加数字
	 * 
	 * @author cz
	 */
	private Pattern pattern;
	private static final String STUDENTID_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";

	public validatorPassword() {
		pattern = Pattern.compile(STUDENTID_PATTERN);
	}

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "validatorPassword";
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub

		if (value == null) {
			return;
		}
		if (!pattern.matcher(value.toString()).matches()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", value + "不合法必须为6-12位字母+数字"));
		}
	}

}
