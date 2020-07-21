package net.etfbl.project.controller;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import net.etfbl.project.bean.CommentBean;
import net.etfbl.project.bean.DangerBean;
import net.etfbl.project.bean.NotificationBean;
import net.etfbl.project.bean.UserBean;
import net.etfbl.project.dto.Comment;
import net.etfbl.project.dto.Danger;
import net.etfbl.project.dto.User;
import net.etfbl.project.utility.JavaMail;

@MultipartConfig
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/home.jsp";
		HttpSession session = req.getSession();
		session.setAttribute("username", req.getParameter("username"));
		if (req.getParameter("name") != null) {
			String[] categories = req.getParameterValues("checkbox");
			User user = (User) session.getAttribute("user");
			DangerBean dangerBean = new DangerBean();
			UserBean userBean = new UserBean();
			List<String>mails = userBean.getAllMailUsers();
		//	String categoriesMessage="";
			//for(String c:categories)
			//	categoriesMessage+=c+"\n";
			if (!req.getParameter("lat").equals("")) {
				Danger danger = new Danger(user.getId(), req.getParameter("name"),
						Double.parseDouble(req.getParameter("lat")), Double.parseDouble(req.getParameter("lng")),
						req.getParameter("description"));
				dangerBean.insertDanger(danger, categories);
				for(String m:mails) {
					JavaMail.sendMail(m,req.getParameter("name"),req.getParameter("description")+"\nLAT: "+req.getParameter("lat")+"\nLNG: "+req.getParameter("lng"));
				}
			} else {
				Danger danger = new Danger(user.getId(), req.getParameter("name"), null, null,
						req.getParameter("description"));
				dangerBean.insertDanger(danger, categories);
				for(String m:mails) {
				JavaMail.sendMail(m,req.getParameter("name"),req.getParameter("description"));}
			}
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(address);
		dispatcher.forward(req, resp);
		//resp.sendRedirect(req.getContextPath() + "/home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if (action.contentEquals("uploadVideo")) {
			NotificationBean notificationBean = new NotificationBean();
			User user = (User) session.getAttribute("user");
			ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath(req.getContextPath());
			Part part = req.getPart("videoFile");
			String fileName = this.getSubmittedFileName(part);
			String username = (String) session.getAttribute("username");
			File file = new File(realContextPath + "video" + File.separator + username);

			if (!file.exists()) {
				file.mkdir();
			}
			if (!fileName.isEmpty()) {
				String stringovi[] = fileName.split(Pattern.quote("."));
				String pom = "";
				for (int i = 0; i < stringovi.length - 1; i++) {
					if (i != stringovi.length - 2)
						pom += stringovi[i] + ".";
					else {
						pom += stringovi[i];
					}
				}
				pom += HomeController.randomString() + "." + stringovi[stringovi.length - 1];
				part.write(realContextPath + "video" + File.separator + username + File.separator + pom);
				fileName = "../../video" + "/" + username + "/" + pom;
				notificationBean.addVideo(user, fileName,req.getParameter("nameVideoUpload"));
			}

		}
		if (action.contentEquals("uploadComment")) {
			CommentBean commentBean = new CommentBean();
			ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath(req.getContextPath());
			Part part = req.getPart(req.getParameter("idCommentPicture"));
			String fileName = this.getSubmittedFileName(part);
			String username = (String) session.getAttribute("username");
			File file = new File(realContextPath + "picture" + File.separator + username);
			String commentString = req.getParameter("comment");
			String pom = "";
			if (!file.exists()) {
				file.mkdir();
			}
			if (!fileName.isEmpty()) {
				String stringovi[] = fileName.split(Pattern.quote("."));

				for (int i = 0; i < stringovi.length - 1; i++) {
					if (i != stringovi.length - 2)
						pom += stringovi[i] + ".";
					else {
						pom += stringovi[i];
					}
				}
				pom += HomeController.randomString() + "." + stringovi[stringovi.length - 1];
			}

			if (!fileName.contentEquals("")) {
				part.write(realContextPath + "picture" + File.separator + username + File.separator + pom);
				fileName = "../../picture" + "/" + username + "/" + pom;
			} else {
				fileName = null;
			}
			if (commentString.contentEquals("")) {
				commentString = null;
				;
			}
			// notificationBean.addVideo(user,fileName);
			int userId = Integer.parseInt(req.getParameter("idUser"));

			Comment comment = new Comment(commentString, fileName,
					Integer.parseInt(req.getParameter("idNotification")));
			if (fileName == null && commentString == null) {

			} else {
				String avatar = (String) session.getAttribute("picture");
				commentBean.insertComment(comment, userId, avatar);
			}

		}
		if (action.contentEquals("uploadText")) {

			String textContent = req.getParameter("descriptionTextUpload");
			NotificationBean notificationBean = new NotificationBean();
			User user = (User) session.getAttribute("user");
			ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath(req.getContextPath());
			String username = (String) session.getAttribute("username");
			List<Part> parts = (List<Part>) req.getParts();
			List<String> fileNames = new ArrayList<String>();
			for (Part p : parts) {
				String fileName = this.getSubmittedFileName(p);
				if (fileName != null) {
					File file = new File(realContextPath + "picture" + File.separator + username);
					if (!file.exists()) {
						file.mkdir();
					}
					if (!fileName.isEmpty()) {
						String stringovi[] = fileName.split(Pattern.quote("."));
						String pom = "";
						for (int i = 0; i < stringovi.length - 1; i++) {
							if (i != stringovi.length - 2)
								pom += stringovi[i] + ".";
							else {
								pom += stringovi[i];
							}
						}

						pom += HomeController.randomString() + "." + stringovi[stringovi.length - 1];
						p.write(realContextPath + "picture" + File.separator + username + File.separator + pom);
						fileName = "../../picture" + "/" + username + "/" + pom;
						fileNames.add(fileName);

					}

				}
			}
			notificationBean.addText(user, fileNames, textContent,req.getParameter("nameTextUpload"));
		//	res.sendRedirect(req.getContextPath() + "/home");
		}
		if (action.contentEquals("uploadLink")) {
			NotificationBean notificationBean = new NotificationBean();
			String url = null;
			url = req.getParameter("link");
			if (!url.equals(""))
				notificationBean.addLink((User) session.getAttribute("user"), url,req.getParameter("nameLinkUpload"));
		}
		// String address = "/WEB-INF/pages/home.jsp";

		// RequestDispatcher dispatcher =
		// req.getRequestDispatcher(address);res.sendRedirect(req.getContextPath() +
		// "/home");
		// dispatcher.forward(req, res);
		res.sendRedirect(req.getContextPath() + "/home");
	}

	private String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}

	public static String randomString() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		// System.out.println("nesto mi se moze");

	}

}
