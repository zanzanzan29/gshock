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

import model.bean.Clock;
import model.bean.User;
import model.dao.ClockDAO;


public class AdminIndexClockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClockDAO clockDAO;   

    public AdminIndexClockController() {
        super();
        clockDAO = new ClockDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null ){
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		ArrayList<Clock> listClock = clockDAO.getItems();
		request.setAttribute("listClock", listClock);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/clock/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
