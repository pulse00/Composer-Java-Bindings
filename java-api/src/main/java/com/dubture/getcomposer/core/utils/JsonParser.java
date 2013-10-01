package com.dubture.getcomposer.core.utils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;

public class JsonParser {

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
}
