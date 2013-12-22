package de.zustandsforschung.markov.sqlinit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlInitImpl implements SqlInit{

	private Connection con;

	public Connection init(String sqliteDb) throws SQLException, ClassNotFoundException {

		Class.forName("org.sqlite.JDBC");
		this.con = DriverManager.getConnection(sqliteDb);
		
		return con;
	}

}
