package com.rap.Helper;

import java.nio.charset.Charset;

public class StringPattern {
	public static String parseUTF(String input)
	{
		byte[] _input = Charset.forName("ISO-8859-1").encode(input).array();
		return new String(_input, Charset.forName("UTF-8"));
	}
}
