package net.etfbl.project.controller;

import java.io.File;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.etfbl.project.bean.UserBean;
import net.etfbl.project.dto.User;

@MultipartConfig
public class RegistrationController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistrationController() {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String address="/register.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(address);
		dispatcher.forward(req, res);
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		if (action.equals("register")) {
			String username = req.getParameter("username");
			String password1 = req.getParameter("password1");
			String password2 = req.getParameter("password2");
			String email = req.getParameter("email");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			if (password1.equals(password2)) {
				UserBean userBean = new UserBean();
				User user = new User(username, password1, email, firstName, lastName);
				try {
					if (userBean.register(user)) {

						HttpSession session = req.getSession();
						ServletContext context = session.getServletContext();
						String realContextPath = context.getRealPath(req.getContextPath());

						System.out.println(realContextPath);
						File file = new File(realContextPath + "users");
						if (file.exists()) {
						} else {
							file.mkdir();
						}
						File userFile = new File(realContextPath + "users" + File.separator + username);
						userFile.mkdir();

						session.setAttribute("username", user.getUsername());
						String address = "WEB-INF/pages/profile.jsp";
						RequestDispatcher dispatcher = req.getRequestDispatcher(address);
						session.setAttribute("userBean", userBean);
						dispatcher.forward(req, res);
					} else {
						System.out.println("nije regustrovao");
						HttpSession session = req.getSession();
						session.setAttribute("poruka", "Zauzeto korisnicko ime ili email");
						res.sendRedirect(req.getContextPath() + "/register.jsp");
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Nisu lozinke iste");
				HttpSession session = req.getSession();
				session.setAttribute("poruka", "Nisu lozinke iste");
				res.sendRedirect(req.getContextPath() + "/register.jsp");
			}
		}
	}
}
