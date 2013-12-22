package de.zustandsforschung.markov.smartsents;

import java.io.File;
import java.io.IOException;

import de.zustandsforschung.markov.model.Tokens;

public interface SentsGenerator {
	
	String generate(String locale,String themes,File tempFile,Tokens tokens,int quant) throws IOException;

}
