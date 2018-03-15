/**
 * 下午3:55:33
 * power
 */
package utils;

import entity.Root;

/**
 * 
 * @author cz
 * 2018年3月13日下午3:55:33
 */
public class CurrentRoot {

	private static Root currentRoot;

	public CurrentRoot(Root current) {
		currentRoot = current;
	}
	public static Root getCurrentRoot() {
		return currentRoot;
	}
}
