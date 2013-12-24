package de.zustandsforschung.markov;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SmartSentsGenTest {

	
	private String line;


	@Before
	public void setUp() throws Exception {
		
		line = " vuonna 2015. Puhelimen käyttöjärjestelmä on Windows Phone 8. 05:39Osake Nokia +1 27 % 2 704 +0 03  lähettäneet ”pari isompaa pankkia”. – Asunto-osakeyhtiöissä  ∇ Mainos  artikkeli jatkuu alempana ∇ ∇ Artikkeli jatkuu ∇ Brax";
	}
	

	@Test
	public void testMain() {
		line = line.replaceAll("\\p{P}", " ");
		
		line = line.replaceAll("\\s+", " ").trim();
		
		
		System.out.println(line);
		
		
	}

}
