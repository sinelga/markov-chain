package de.zustandsforschung.markov.sentsfile;

import java.io.IOException;
import java.util.List;

public interface SentsFile {

	void create(String locale,String themes,List<String> sentensArr) throws IOException;
}
