/**
 * 下午4:34:41
 * power
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import entity.Root;

/**
 * 
 * @author cz
 * 2018年3月12日下午4:34:41
 */
@ManagedBean(name = "currentRoot")
@SessionScoped
public class CurrentRoot {
	private Root currentRoot;
	
	public CurrentRoot() {
		super();
	}

	
	public CurrentRoot(Root currentRoot) {
		super();
		this.currentRoot = currentRoot;
	}


	public Root getCurrentRoot() {
		return currentRoot;
	}

	public void setCurrentRoot(Root currentRoot) {
		this.currentRoot = currentRoot;
	}

	
	
}
