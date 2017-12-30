package dao;

import java.util.List;

import model.Scores;

public interface ScoresDAO {
	/*
	 * @author cz
	 * 2017-12-29
	 * */
	
	/*
	 * 查询所有分数
	 * 参数：无
	 * 返回：数据库中所哟scores的集合
	 * */
	public List<Scores> getAllScores();
	
	
	/*
	 * 分数添加
	 * 参数：添加的对象scores;
	 * 返回：函数执行的结果
	 * */
	public String addScores(Scores scores);
	
	
	
	/*
	 * 分数查询,
	 * 参数：查询对象的属性之一condition 
	 * 参数：查询的条件的属性值conditonValue
	 * 返回：一个存有所有满足条件的List
	 * 补充：这样设计是为了查询的多样化。
	 * */
	public List<Scores> searchByCondition(String condition,String conditionValue);
	
	
	/*
	 * 分数更新
	 * 参数：更新对象的所有属性Scores
	 * 参数：更新那一个条记录which
	 * 参数：更新一条记录的什么，初步设定，all代表更新所有属性，
	 * 返回：函数的执行的结果
	 * */
	public String updateScores(Scores scores,String which,String what);
	
	
	
	/*
	 * 分数删除
	 * 参数：删除的条件的属性conditon
	 * 参数：删除的条件的属性的值conditionValue
	 * 返回：函数执行的结果
	 * @author cz
	 * 更新日期:2017-12-30
	 * */
	public String deleteScores(String condition,String conditionValue);
}
