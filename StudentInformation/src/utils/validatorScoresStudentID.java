package utils;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validatorScoresStudentID")
public class validatorScoresStudentID implements Validator, ClientValidator {
	/*
	 * ��Scores��StudentID�ṩ��֤
	 * 1.ѧ����ʽ�Ƿ�Ϸ�
	 * 2.���ݿ����Ƿ��Ѵ���
	 * @author cz
	 * 2017-12-30
	 * */
	private Pattern pattern;
	private static final String STUDENTID_PATTERN ="^[0-9]{12}$";
	public validatorScoresStudentID() {
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
		return "validatorScoresStudentID";
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		
		if(value==null) {
			return;
		}
		if(!pattern.matcher(value.toString()).matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",value+"���Ϸ�����Ϊ12λ����"));
		}else if(DataSearchUtils.duplicateCheckingData("Scores",value.toString())) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",value+"�Ѵ���"));
		}
	}

}
