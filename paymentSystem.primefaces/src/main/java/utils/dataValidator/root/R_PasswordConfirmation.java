/**
 * 下午3:21:29
 * power
 */
package utils.dataValidator.root;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

/**
 * @author cz
 *
 * 2018年3月10日下午3:21:29
 */
@FacesValidator("R_PasswordConfirmation")
public class R_PasswordConfirmation implements Validator, ClientValidator{

	private Pattern pattern;
	private static final String STUDENTID_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";

	public R_PasswordConfirmation() {
		pattern = Pattern.compile(STUDENTID_PATTERN);
	}
	/* (non-Javadoc)
	 * @see org.primefaces.validate.ClientValidator#getMetadata()
	 * @author cz
	 * @time 2018年3月10日下午3:22:21
	 */
	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.validate.ClientValidator#getValidatorId()
	 * @author cz
	 * @time 2018年3月10日下午3:22:21
	 */
	@Override
	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "R_PasswordConfirmation";
	}

	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 * @author cz
	 * @time 2018年3月10日下午3:22:21
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub

		if (arg2 == null) {
			return;
		}
		if (!pattern.matcher(arg2.toString()).matches()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", arg2 + "密码不合规范必须为6-12位字母+数字组合"));
		}
		
	}

}
