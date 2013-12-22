package de.zustandsforschung.markov.smartsents;

import java.io.File;
import java.io.IOException;

import de.zustandsforschung.markov.MarkovCommandLine;
import de.zustandsforschung.markov.MarkovDictionaryBuilder;
import de.zustandsforschung.markov.MarkovDictionaryBuilderImpl;
import de.zustandsforschung.markov.MarkovTextGenerator;
import de.zustandsforschung.markov.MarkovTextGeneratorImpl;
import de.zustandsforschung.markov.model.MarkovDictionary;
import de.zustandsforschung.markov.model.Tokens;
import de.zustandsforschung.markov.random.RandomGeneratorImpl;

public class SentsGeneratorImpl implements SentsGenerator{

	@Override
	public String generate(String locale, String themes, File tempFile,
			Tokens tokens,int quant) throws IOException {
		
		
		MarkovDictionary markovDictionary = new MarkovDictionary(1);
		MarkovDictionaryBuilder markovDictionaryBuilder = new MarkovDictionaryBuilderImpl(
				markovDictionary);
		markovDictionaryBuilder.clearPreviousToken();
		markovDictionaryBuilder.addTokens(tokens);
//		markovDictionaryBuilder.
		MarkovTextGenerator markovTextGenerator = new MarkovTextGeneratorImpl(
				markovDictionary);
		markovTextGenerator.setRandomGenerator(new RandomGeneratorImpl());
		MarkovCommandLine.fromFile(markovDictionaryBuilder, tempFile);

		String dic = markovTextGenerator.generate(quant);
		
		System.out.println(dic);
		
		return dic;
				
		
	}

}
