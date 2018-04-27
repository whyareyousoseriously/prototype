package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.ServerResponse;


import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * Servlet implementation class ActiveServlet
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ActiveServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 接受激活码
		String mailCode = request.getParameter("mailCode");

		logger.info("接收激活码" + mailCode);
		// 根据激活码查询用户
		IUserService iUserService = new UserServiceImpl();
		ServerResponse<String> serverResponse = iUserService.active(mailCode);
		// 防止页面输出中文乱码,该设置必须在getWriter()前
		response.setContentType("text/html;charset=UTF-8");
		// 向页面输出提示信息
		PrintWriter out = response.getWriter();

		if (serverResponse.isSuccess()) {
			out.println("激活成功");
		} else {
			out.println("激活失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
