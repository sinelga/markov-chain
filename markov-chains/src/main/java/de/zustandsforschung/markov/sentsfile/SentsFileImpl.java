package de.zustandsforschung.markov.sentsfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import de.zustandsforschung.markov.ComUtils.Capitalizer;

public class SentsFileImpl implements SentsFile {

	private String lineseparator;
	private String line;

	public void create(String locale, String themes, List<String> sentensArr) throws IOException {
		// TODO Auto-generated method stub
		
		
		lineseparator = System.getProperty("line.separator");
		
		String filename = String.format("%s_%s_%s.%s",locale,themes, RandomStringUtils.randomAlphanumeric(8), "txt");
//		File file = new File(locale+"_"+themes+".txt");
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());

		BufferedWriter out = new BufferedWriter(fw);
		
		for(String sents:sentensArr){
			
			line = Capitalizer.capitalize(sents);
			out.write(line+".");
			out.write(lineseparator);			
		}
		
		out.close();

	}

}
