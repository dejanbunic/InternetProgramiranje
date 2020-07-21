package net.etfbl.project.controller;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import net.etfbl.project.bean.UserBean;
import net.etfbl.project.dto.User;
import net.etfbl.project.utility.JSONUtils;

@MultipartConfig
@WebServlet(name = "ProfileController", urlPatterns = { "/profile" }, initParams = {
		@WebInitParam(name = "FILE_UPLOAD_PATH", value = "/opt/myuser/files/upload") })
public class ProfileController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/profile.jsp";
		HttpSession session = req.getSession();
		RequestDispatcher dispatcher = req.getRequestDispatcher(address);
		dispatcher.forward(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		ServletContext context = session.getServletContext();
		String realContextPath = context.getRealPath(req.getContextPath());

		String email = req.getParameter("email");
		String firstname = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		// String country = req.getParameter("country");
		String region = req.getParameter("region");
		String city = req.getParameter("city");
		System.out.println("city city citu"+city);
		
		int id = (int) session.getAttribute("id");
		String alpha2Code = req.getParameter("country");
		if (action.equals("profile")) {
			String mail_notification = req.getParameter("mail_notification");
			System.out.println("mail_notification "+mail_notification);
			String username = req.getParameter("username");
			Part part = req.getPart("file-upload");
			String fileName = this.getSubmittedFileName(part);
			if (!fileName.isEmpty()) {
				part.write(realContextPath + "users" + File.separator + username + File.separator + fileName);
				fileName = "../../users" + "/" + username + "/" + fileName;
			} else {
				fileName = req.getParameter("country");
				Locale locale = new Locale("en", fileName);
				String alpha3Code = locale.getISO3Country().toLowerCase();
				fileName = "https://restcountries.eu/data/" + alpha3Code + ".svg";
				System.out.println(fileName);

			}
			UserBean userBean = new UserBean();
			User user = new User(id, username, email, firstname, lastName, region, city, alpha2Code, fileName,mail_notification);
			userBean.updateUserById(user);
			List<Integer> citiesId = JSONUtils.getCitiesID(user.getAlpha2Code());
			session.setAttribute("user", user);
			session.setAttribute("alpha2Code", user.getAlpha2Code());
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("picture", user.getPicture());
			session.setAttribute("citiesId", citiesId);
			session.setAttribute("username", username);
			session.setAttribute("id", user.getId());
			userBean.insertLog(user);
			
			
			String username1 = req.getParameter("username1");
			User user1 = userBean.getUserByUsername(username1);
			System.out.println("username je username je"+username);
			String newPassword = req.getParameter("newPassword");
			String oldPassword = req.getParameter("oldPassword");
			String confirmPassword = req.getParameter("confirmPassword");
			System.out.println(username);
			System.out.println(newPassword);
			System.out.println(confirmPassword);
			try {
					if(!newPassword.equals("") && !oldPassword.equals("") && !confirmPassword.equals("") ) {
						if(newPassword.equals(confirmPassword))
							System.out.println("usao u promjenu pass");
							userBean.changePassword(user1, newPassword, oldPassword);
					}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res.sendRedirect(req.getContextPath() + "/home");
		}
		if (action.equals("changePassword")) {
			UserBean userBean = new UserBean();
			// User user = userBean.changePassword(user)
			String username = req.getParameter("username1");
			User user = userBean.getUserByUsername(username);
			System.out.println("username je username je"+username);
			String newPassword = req.getParameter("newPassword");
			String oldPassword = req.getParameter("oldPassword");
			System.out.println(username);
			System.out.println(newPassword);
			// user.setPassword (req.getParameter("newPassword"));
			
			try {
				userBean.changePassword(user, newPassword, oldPassword);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String address = "WEB-INF/pages/profile.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(address);
			
			dispatcher.forward(req, res);
		}
	}

	private String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); 
																													
			}
		}
		return null;
	}
}
