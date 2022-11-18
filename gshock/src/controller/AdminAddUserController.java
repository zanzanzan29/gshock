package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;
import model.bean.User;
import model.dao.UserDAO;


public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;   

    public AdminAddUserController() {
        super();
        userDAO = new UserDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null ){
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null ){
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String roles = request.getParameter("roles");
		ArrayList<User> listU = userDAO.getItems();
		if(listU != null && listU.size() > 0){
			for(User itemN: listU){
				if(itemN.getUsername().equals(username)){
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?err=2");
					rd.forward(request, response);
					return;
				}
			}
		}
		password = StringUtil.md5(password);
		User item = new User(0, username, password, fullname, roles);
		if(userDAO.addItem(item) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=1");
			return;
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
