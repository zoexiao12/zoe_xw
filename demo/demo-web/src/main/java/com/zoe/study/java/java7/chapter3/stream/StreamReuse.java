package com.zoe.study.java.java7.chapter3.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamReuse {
	
	private InputStream input;
	
	public StreamReuse(InputStream input) {
		if(input.markSupported()) {
			this.input = input;
		}else {
			this.input = new BufferedInputStream(input);
		}
	}
	
	public InputStream getInputStream() {
		input.mark(Integer.MAX_VALUE);
		return input;
	}
	
	public void markUsed() throws IOException {
		input.reset();
	}

}
