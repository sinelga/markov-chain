package de.zustandsforschung.markov.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Occurrences {

	private final Map<String, Double> occurrences;

	public Occurrences() {
		occurrences = new HashMap<String, Double>();
	}

	public Set<Entry<String, Double>> entrySet() {
		return occurrences.entrySet();
	}

	public Double get(final String token) {
		return occurrences.get(token);
	}

	public void put(final String token, final Double count) {
		occurrences.put(token, count);
	}

	public Collection<Double> values() {
		return occurrences.values();
	}

	public void increaseCount(final String token) {
		if (occurrences.get(token) == null) {
			occurrences.put(token, Double.valueOf(0));
		}
		occurrences.put(token, occurrences.get(token) + 1);
	}

	public Double totalCount() {
		Double totalCount = 0.0;
		for (Double count : values()) {
			totalCount += count;
		}
		return totalCount;
	}

	public double calculateProbability(final Double occurrence) {
		return occurrence / totalCount();
	}

}
