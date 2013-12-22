package de.zustandsforschung.markov;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import de.zustandsforschung.markov.model.MarkovDictionary;
import de.zustandsforschung.markov.model.Tokens;
import de.zustandsforschung.markov.random.RandomGeneratorImpl;

public class AppMark {

	/**
	 * @param args
	 * @throws IOException 
	 */
	static int MAXGEN ;
	private static String word;
	private static ArrayList<String> coutallwordsarr;
	private static String lineseparator;
	private static StringBuffer sb;
	private static String locale;
	private static String themes;
	private static String sentencesfile;
	private static String outfile;
	
	public static void main(String[] args) throws IOException {
		
		if (args.length > 1) {

			locale = args[0];
			themes = args[1];
		
		lineseparator = System.lineSeparator();
		
		sentencesfile = "/home/juno/junoworkspace/SearchGoogleLogParser/"+locale+themes+".csv";
		
		InputStream is = new FileInputStream(sentencesfile);

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(is);

		coutallwordsarr = new ArrayList<String>();
		while (sc.hasNext()) {

			word = sc.next();
			coutallwordsarr.add(word);

		}
		System.out.println(coutallwordsarr.size());
		
		is.close();
		
		MAXGEN = coutallwordsarr.size();
		
		File tempFile = new File(sentencesfile);
		
		MarkovDictionary markovDictionary = new MarkovDictionary(2);
		MarkovDictionaryBuilder markovDictionaryBuilder = new MarkovDictionaryBuilderImpl(markovDictionary);
		MarkovTextGenerator markovTextGenerator = new MarkovTextGeneratorImpl(
				markovDictionary);
		markovTextGenerator.setRandomGenerator(new RandomGeneratorImpl());
		MarkovCommandLine.fromFile(markovDictionaryBuilder, tempFile);
				
		sb = new StringBuffer(1024 * 1024);
		
		String[] dic = markovTextGenerator.generate(MAXGEN*10).split("\\s+");
		
		String line = "";
		for (String word:dic){
			
			line = line +" "+word;
			if (line.endsWith(".")) {

				if (line.length() > 15) {
					System.out.println(line);
					line = line + lineseparator;					

					sb.append(line.substring(1, 2).toUpperCase() + line.substring(2));
					
					line = "";
				}

			}
									
		}

		outfile ="/home/juno/git/goFastCgi/goFastCgi/markresources/"+locale+"_"+themes+"/all"+locale+"_"+themes+".txt";
		System.out.println("Start create file "+outfile);
		File file = new File(outfile);
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		writer.write(sb.toString());
		writer.close();
		
		}
				

	}

}
