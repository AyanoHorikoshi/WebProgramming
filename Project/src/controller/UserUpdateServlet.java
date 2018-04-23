package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
    	int id = Integer.parseInt(request.getParameter("id"));
		UserDao userDao = new UserDao();
		User user = userDao.userReference(id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password").trim();
		String passwordConfirm = request.getParameter("passwordConfirm").trim();
		String name = request.getParameter("name").trim();
		String birthDate = request.getParameter("birthDate");

		if(!password.equals(passwordConfirm) || name.equals("")) {
			request.setAttribute("errMsg", "入力された内容は正しくありません");
			User user = new User(id, loginId, name, Common.convertDate(birthDate), passwordConfirm, null, null);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp");
			dispatcher.forward(request, response);
			return;
		}
		UserDao usd = new UserDao();
		usd.userUpdate(password, name, birthDate,id);
		response.sendRedirect("UserListServlet");
	}

}
