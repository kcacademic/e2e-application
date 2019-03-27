package com.sapient.learning;

import java.io.Serializable;

public class Word implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String _id;
	
	private String word;
	
	private int count;
	
	public Word(String _id, String word, int count) {
		this.set_id(_id);
		this.word = word;
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
}
