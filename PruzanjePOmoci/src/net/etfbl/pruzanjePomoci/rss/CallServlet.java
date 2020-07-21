package net.etfbl.pruzanjePomoci.rss;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.io.SyndFeedOutput;

import net.etfbl.pruzanjePomoci.dao.CallCategoryDAO;
import net.etfbl.pruzanjePomoci.dao.CallDAO;
import net.etfbl.pruzanjePomoci.dto.Call;
import net.etfbl.pruzanjePomoci.dto.CallCategory;

//@WebServlet("/rss")
public class CallServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5196712284401820569L;

	public CallServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding(StandardCharsets.UTF_8.name());
		req.setCharacterEncoding(StandardCharsets.UTF_8.name());
		SyndFeed feed = new SyndFeedImpl();
		List<SyndEntry> entries = new ArrayList<>();
		List<Call> calls = CallDAO.getCalls();
		feed.setFeedType("rss_2.0");
		feed.setTitle("title");
		feed.setDescription("description");
		feed.setLink("https://www.rezultati.com");
		try {
			calls.forEach(call -> {
				SyndContent desc = new SyndContentImpl();
				desc.setType("text/plain");
				String description = "Opis: " + call.getDescription() + " Lokacija: " + call.getLocation();
				desc.setValue(description);
				SyndEntry item = new SyndEntryImpl();
				item.setTitle(call.getName());
				item.setDescription(desc);
				item.setLink(call.getPicture());
				item.setAuthor("Dejan Bunic");

				Date date = null;

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					// sdf.setTimeZone(TimeZone.getTimeZone("UTC+2"));
					date = sdf.parse(call.getTime());
					// System.out.println(date.getTimezoneOffset());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				item.setPublishedDate(date);

				List<SyndCategory> category = new ArrayList<SyndCategory>();
				item.setCategories(category);
				Optional<CallCategory> categoryOptional = CallCategoryDAO.getCategorys().stream()
						.filter(c -> c.getId() == call.getIdCallCategory()).findFirst();
				SyndCategoryImpl sci = new SyndCategoryImpl();
				sci.setName(categoryOptional.get().getName());
				category.add(sci);
				item.setCategories(category);

				entries.add(item);
			});

			feed.setEntries(entries);

			res.getWriter().println(new SyndFeedOutput().outputString(feed));
		} catch (Exception ex) {
			ex.printStackTrace();
			res.sendError(500);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doGet(req, res);
	}
}
