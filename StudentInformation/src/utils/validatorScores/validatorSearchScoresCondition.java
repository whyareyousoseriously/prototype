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
	 * 对Scores表查询提供验证
	 * 1.查询条件condition必须为表中字段，如：name studentID gender department major math english java c os javaEE
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
		//给condition进行赋值
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
					value+"不是name studentID gender department major math english java c os javaEE中的一个"));
		}
	}

}