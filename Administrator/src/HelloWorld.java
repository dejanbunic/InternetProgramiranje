import java.text.ParseException;
import java.util.List;

import net.etfbl.project.bean.LogBean;
import net.etfbl.project.dao.LogDAO;
import net.etfbl.project.dto.Log;

public class HelloWorld{
	public static void main(String[]args) throws ParseException {
		LogBean logBean = new LogBean();
		System.out.println(logBean.getIntegers());
		List<Log> logs = LogDAO.allLogs24Hours();
		System.out.println(logs.get(0).getLoginDate());
}
}