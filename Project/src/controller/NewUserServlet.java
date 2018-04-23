package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

/**
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("loginId").trim();
		String password = request.getParameter("password").trim();
		String passwordConfirm = request.getParameter("passwordConfirm").trim();
		String name = request.getParameter("name").trim();
		String birthDate = request.getParameter("birthDate");
		UserDao userDao = new UserDao();
		boolean pokemon = userDao.searchLoginId(loginId);

		if(!password.equals(passwordConfirm) || loginId.equals("") || password.length() == 0 || name.equals("") || !pokemon) {
			request.setAttribute("errMsg", "入力された内容は正しくありません");
			request.setAttribute("loginId", loginId);
			request.setAttribute("name", name);
			request.setAttribute("birthDate", birthDate);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");
			dispatcher.forward(request, response);
			return;
		}
		UserDao usd = new UserDao();
		usd.insertNewUser(loginId, password, name, birthDate);
		response.sendRedirect("UserListServlet");
	}

}
