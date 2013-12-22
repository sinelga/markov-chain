package de.zustandsforschung.markov.keywdDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KeywdDBHandlerImpl implements KeywdDBHandler{

	private Statement stat;
	private ResultSet rs;
	private List<String> keywordsArr;

	@Override
	public List<String> get(Connection con,String sqlstr) throws SQLException {

		keywordsArr = new ArrayList<String>();
		stat = con.createStatement();
		
		rs = stat.executeQuery(sqlstr);
		
		
		while (rs.next()) {
		
			String keyword = rs.getString("Keyword");
			keywordsArr.add(keyword);
			
		}
		
		return keywordsArr;
	}

}
