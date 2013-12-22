package de.zustandsforschung.markov.keywdDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface KeywdDBHandler {
	
	
	List<String> get(Connection con,String sqlstr) throws SQLException;

}
