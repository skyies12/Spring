package com.study.spring;

import java.util.*;

public class Hello {
	private List<String> names;
	private Set<String> nums;
	private Map<String, Integer> ages;
	
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public Set<String> getNums() {
		return nums;
	}
	public void setNums(Set<String> nums) {
		this.nums = nums;
	}
	public Map<String, Integer> getAges() {
		return ages;
	}
	public void setAges(Map<String, Integer> ages) {
		this.ages = ages;
	}
}
