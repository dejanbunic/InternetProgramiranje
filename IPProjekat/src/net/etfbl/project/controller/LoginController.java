package net.etfbl.project.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.etfbl.project.bean.UserBean;
import net.etfbl.project.dto.User;
import net.etfbl.project.utility.JSONUtils;

public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		UserBean userBean = new UserBean();
		HttpSession session = req.getSession();
		if (action.equals("logout")) {
			// User user = new
			// User(session.getAttribute("username").toString(),session.getAttribute("password").toString());

			int user_id = (int) session.getAttribute("id");
		//	System.out.println("user_id je " + user_id);
			userBean.selectIdLog(user_id);
			userBean.updateLog(userBean.getId());
			userBean.logout();

			//System.out.println(userBean.getId());
			session.invalidate();
			String address = "index.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(address);
			dispatcher.forward(req, res);
		}
		if (action.equals("login")) {

			String username = req.getParameter("username");
			String password = req.getParameter("password");

			User user = new User(username, password);

			try {
				if (userBean.login(user, session)) {

					user = userBean.getUserByUsername(username);
					userBean.insertLog(user);

					List<Integer> citiesId = JSONUtils.getCitiesID(user.getAlpha2Code());
					session.setAttribute("alpha2Code", user.getAlpha2Code());
					session.setAttribute("firstName", user.getFirstName());
					session.setAttribute("lastName", user.getLastName());
					session.setAttribute("picture", user.getPicture());
					session.setAttribute("citiesId", citiesId);
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					session.setAttribute("id", user.getId());
					// String address = "WEB-INF/pages/home.jsp";

					session.setAttribute("user", user);
					// RequestDispatcher dispatcher = req.getRequestDispatcher(address);
					// dispatcher.forward(req,res);
					res.sendRedirect(req.getContextPath() + "/home");
				} else {
					String address = "index.jsp";
					RequestDispatcher dispatcher = req.getRequestDispatcher(address);
					session.setAttribute("poruka", "Pogre&scaron;no korisni&ccaron;ko ime ili lozinka");
					dispatcher.forward(req, res);
				}
			} catch (NoSuchAlgorithmException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
