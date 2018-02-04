package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;

/**
 * Servlet implementation class ActiveServlet
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//接受激活码
		String mailCode = request.getParameter("mailCode");
		System.out.println("接收激活码"+mailCode);
		//根据激活码查询用户
		UserDAO udao = new UserDAOImpl();
		User user = udao.findByMailCode(mailCode);
		if(user!=null) {
			//已经查到用户了
			System.out.println("正在执行激活操作");
			user.setActive("已激活");
			user.setMailCode(null);
			udao.update(user);
		}else {
			//没有查到，跳转页面激活码错误
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
