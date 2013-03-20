package org.getcomposer.core.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

public class JsonFormatter {

	public static String format(final Object object) {
		final JsonVisitor visitor = new JsonVisitor(1, '\t');
		visitor.visit(object, 0);
		return JsonFormatter.postProcessing(visitor.toString());
	}
	
	private static String postProcessing(String json) {
		json = json.replace("[\n{", "[{");
		json = json.replace("},\n{", "}, {");
		return json;
	}
	
	private static class JsonVisitor {

		private final StringBuilder builder = new StringBuilder();
		private final int indentationSize;
		private final char indentationChar;

		public JsonVisitor(final int indentationSize, final char indentationChar) {
			this.indentationSize = indentationSize;
			this.indentationChar = indentationChar;
		}

		private void visit(final List<Object> array, final int indent) {
			final int length = array.size();
			if (length == 0) {
				write("[]", 0);
			} else {
				writeln("[", 0);
				for (int i = 0; i < length; i++) {
					visit(array.get(i), indent + 1);
					if (i < length - 1) {
						writeln(",", 0);
					}
				}
				writeln("", 0);
				write("]", indent);
			}

		}

		private void visit(final Map<String, Object> obj, final int indent) {
			final int length = obj.size();
			if (length == 0) {
				write("{}", 0);
			} else {
				writeln("{", 0);
				final Iterator<String> keys = ((Set<String>)obj.keySet()).iterator();
				while (keys.hasNext()) {
					final String key = keys.next();
					write("\"" + escape(key) + "\" : ", indent + 1);
					visit(obj.get(key), indent + 1);
					if (keys.hasNext()) {
						writeln(",", 0);
					}
				}
				writeln("", 0);
				write("}", indent);
			}

		}

		@SuppressWarnings("unchecked")
		private void visit(final Object object, int indent) {
			if (object instanceof List) {
				visit((List<Object>) object, indent);
			} else if (object instanceof Map) {
				visit((Map<String, Object>) object, indent);
			} else {
				if (builder.charAt(builder.length() - 1) != '\n') {
					indent = 0;
				}
				if (object instanceof String) {
					write("\"" + escape((String)object) + "\"", indent);
				} else if (object instanceof Boolean || object instanceof Number) {
					write(String.valueOf(object), indent);
				} else {
					write("\"" + JSONObject.escape(String.valueOf(object)) + "\"", indent);
				}
			}
		}
		
		private void writeln(final String data, final int indent) {
			write(data, indent);
			builder.append('\n');
		}

		private void write(final String data, final int indent) {
			for (int i = 0; i < (indent * indentationSize); i++) {
				builder.append(indentationChar);
			}
			builder.append(data);
		}
		
		private String escape(String string) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < string.length(); i++) {
				char ch = string.charAt(i);
				if (ch == '"') {
					sb.append('\"');
				} else if ((ch>='\u0000' && ch<='\u001F') || (ch>='\u007F' && ch<='\u009F') || (ch>='\u2000' && ch<='\u20FF')){
					String ss=Integer.toHexString(ch);
					sb.append("\\u");
					for(int k=0;k<4-ss.length();k++){
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
			return sb.toString();
		}

		@Override
		public String toString() {
			return builder.toString();
		}

	}

}