package net.etfbl.project.bean;

import net.etfbl.project.dao.LogDAO;

public class LogBean {
  public int getUserNumberLogs(int id) {
	  return LogDAO.getUserNumberLogs(id);
  }
}
