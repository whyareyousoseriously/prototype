package utils.validatorScores;

import java.util.ArrayList;
import java.util.Map;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validatorSearchScoresCondition")
public class validatorSearchScoresCondition implements Validator, ClientValidator {
	/*
	 * ��Scores���ѯ�ṩ��֤
	 * 1.��ѯ����condition����Ϊ�����ֶΣ��磺name studentID gender department major math english java c os javaEE
	 * @author cz
	 * 2017-12-30
	 * */
	private ArrayList<String> conditions = new ArrayList<String>();
	private boolean mark=false;
	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {
		// TODO Auto-generated method stub
		return "validatorSearchScoresCondition";
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		//��condition���и�ֵ
		conditions.add("name");
		conditions.add("studentID");
		conditions.add("gender");
		conditions.add("department");
		conditions.add("major");conditions.add("math");conditions.add("english");conditions.add("java");
		conditions.add("c");conditions.add("os");conditions.add("javaEE");
		if(value==null) {
			return;
		}
		for(String condition :conditions) {
			if(condition.equals(value.toString())) {
				mark = true;
			}
		}
		if(!mark) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validation Error",
					value+"����name studentID gender department major math english java c os javaEE�е�һ��"));
		}
	}

}