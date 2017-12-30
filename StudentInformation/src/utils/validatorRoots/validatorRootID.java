package utils.validatorRoots;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import utils.DataSearchUtils;

@FacesValidator("validatorRootID")
public class validatorRootID implements Validator, ClientValidator {
	/*
	 * 对Roots表rootID提供验证
	 * 1.学号形式是否合法
	 * 2.数据库中是否已存在
	 * @author cz
	 * 2017-12-30
	 * */
	private Pattern pattern;
	private static final String STUDENTID_PATTERN ="^[0-9]{6}$";
	public validatorRootID() {
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
		return "validatorRootID";
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		
		if(value==null) {
			return;
		}
		if(!pattern.matcher(value.toString()).matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",value+"不合法必须为6位数字"));
		}else if(DataSearchUtils.duplicateCheckingData("Roots","rootID",value.toString())) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",value+"已存在"));
		}
	}

}
