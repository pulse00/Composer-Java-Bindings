package com.dubture.getcomposer.json;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

public class JsonParser {
	
	JSONParser parser = new JSONParser();
	
	public JsonParser() {
		
	}

	public static ContainerFactory getContainerFactory() {
		return new ContainerFactory() {
			@SuppressWarnings("rawtypes")
			public List creatArrayContainer() {
				return new LinkedList();
			}

			@SuppressWarnings("rawtypes")
			public Map createObjectContainer() {
				return new LinkedHashMap();
			}
		};
	}
	
	public Object parse(String json) throws ParseException {
		try {
			return parser.parse(json, getContainerFactory());
		} catch (org.json.simple.parser.ParseException e) {
			throw buildException(e);
		}
	}
	
	public Object parse(Reader reader) throws ParseException, IOException {
		try {
			return parser.parse(reader, getContainerFactory());
		} catch (org.json.simple.parser.ParseException e) {
			throw buildException(e);
		}
	}
	
	private ParseException buildException(org.json.simple.parser.ParseException e) {
		ParseException pe = new ParseException(e.getMessage());
		pe.setErrorType(e.getErrorType());
		pe.setUnexpectedObject(e.getUnexpectedObject());
		pe.setPosition(e.getPosition());
		
		return pe;
	}
	
}
