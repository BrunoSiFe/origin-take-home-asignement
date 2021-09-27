package com.bruno.origin.model;

public class InsuranceResult {

	private String finalScore;

	private Integer numericalScore;

	public InsuranceResult() {
	}
	
	public InsuranceResult(String finalScore, Integer numericalScore) {
		this.finalScore = finalScore;
		this.numericalScore = numericalScore;
	}

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	public Integer getNumericalScore() {
		return numericalScore;
	}

	public void setNumericalScore(Integer numericalScore) {
		this.numericalScore = numericalScore;
	}

}
