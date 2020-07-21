package main;


import java.util.ArrayList;
import java.util.List;

import net.etfbl.pruzanjePomoci.dao.CallDAO;

import net.etfbl.pruzanjePomoci.dto.Call;

import net.etfbl.pruzanjePomoci.rss.Feed;
import net.etfbl.pruzanjePomoci.rss.FeedMessage;
import net.etfbl.pruzanjePomoci.rss.RSSFeedParser;

public class Main {
	public static void main(String[]args) {
		//List<Event>events=new ArrayList<Event>();
		//events=EventDAO.getAll();
	//	Event event= new Event();
	//	event=events.get(1);
	//	System.out.println(event.getTime());
		//EventBean eventBean= new EventBean();
		//eventBean.writeToRSS(event);
		 	//RSSFeedParser parser = new RSSFeedParser(
	         //       "https://europa.eu/newsroom/calendar.xml_en?field_nr_events_by_topic_tid=151");
	     //   Feed feed = parser.readFeed();
	      //  System.out.println(feed);
	      //  for (FeedMessage message : feed.getMessages()) {
	        //    System.out.println(message);
	      //  }
		Call call = new Call();
		call.setName("dejan");
		call.setLocation("tu");
		call.setPicture("ee");
		call.setTime("2001-11-11 00:00:00");
		call.setIdCallCategory(1);
		call.setDescription("opis");
     	CallDAO.insertCall(call);
		//Event event = new Event();
		
		
		List<Call>calls=CallDAO.getCalls();
		System.out.println("ime je"+calls.get(0).getName());
	}
	
}