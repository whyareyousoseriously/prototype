package utils.validatorScores;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validatorScoresName")
public class validatorScoresName implements Validator, ClientValidator {
	/*
	 * 对Scores表Name提供验证 1.名字长度4-8
	 * 
	 * @author cz 2017-12-30
	 */

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "validatorScoresName";
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub

		if (value == null) {
			return;
		}
		if ((value.toString()).length() < 4 || (value.toString()).length() > 8) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", value + "不合法，必须为4-8个字符"));
		}
	}

}
