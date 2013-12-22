package de.zustandsforschung.markov;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.zustandsforschung.markov.keywdDB.KeywdDBHandlerImpl;
import de.zustandsforschung.markov.model.Tokens;
import de.zustandsforschung.markov.sentsfile.SentsFileImpl;
import de.zustandsforschung.markov.smartsents.SentsGeneratorImpl;
import de.zustandsforschung.markov.sqlinit.SqlInitImpl;

public class SmartSentsGen {

	private static String locale;
	private static String themes;
	private static String sentencesfile;
	private static String sqliteDb;
	private static Connection con;
	private static String sqlstr;
	private static List<String> keywords;
	private static List<String> sentensesArr;

	/**
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, SQLException {

		if (args.length > 1) {

			locale = args[0];
			themes = args[1];

			SqlInitImpl sqlInit = new SqlInitImpl();

			sqliteDb = "jdbc:sqlite:/home/juno/git/goFastCgi/goFastCgi/singo.db";

			con = sqlInit.init(sqliteDb);

			KeywdDBHandlerImpl keywdDBHandler = new KeywdDBHandlerImpl();

			sqlstr = "select Keyword from keywords where Locale='" + locale
					+ "' and Themes='" + themes
					+ "' order by Hits desc limit 10;";

			keywords = keywdDBHandler.get(con, sqlstr);

//			sentencesfile = "/home/juno/git/goFastCgi/goFastCgi/markresources/fi_FI_finance/all_fi_FI_finance.txt";
			
			sentencesfile = "all_"+locale+"_"+themes+".txt";

			BufferedReader br = new BufferedReader(
					new FileReader(sentencesfile));

			String line;

			Pattern pattern = Pattern.compile("^[\\pL]*$");

			File tempFile = File.createTempFile("markov", ".tmp");
			tempFile.deleteOnExit();
			BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

			while ((line = br.readLine()) != null) {

				String[] stlitbyspace = line.split("\\s+");

				for (String word : stlitbyspace) {

					Matcher matcher = pattern.matcher(word);

					if (matcher.matches()) {

						if (word.length() > 3) {
							out.write(word.toLowerCase() + " ");
						}

					}

				}

			}
			br.close();
			out.close();

			SentsGeneratorImpl sentsGenerator = new SentsGeneratorImpl();
			
			Collections.shuffle(keywords);

			sentensesArr = new ArrayList<String>();

			for (String keyword : keywords) {

				String sentense = sentsGenerator.generate(locale, themes,
						tempFile, new Tokens(keyword), 15);
				sentensesArr.add(sentense);

			}
			
			SentsFileImpl sentsFile = new SentsFileImpl();			
			sentsFile.create(locale, themes, sentensesArr);
			
		}

	}

}
