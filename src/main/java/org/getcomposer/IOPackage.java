package org.getcomposer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Constructor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A package that can be read from a file or string and also dump to the latter one.
 * 
 * @author Thomas Gossmann <gos.si>
 * 
 */
public class IOPackage extends ObservableModel {
	
	private transient String path;
	private static Gson builder = null;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public File toFile() {
		return new File(path);
	}
	
	public String toJson() {
		Gson gson = getBuilder();
		return gson.toJson(this);
	}
	
	protected static <T> T fromFile(File input, Class<T> classOfT) throws FileNotFoundException {
		InputStream stream = new FileInputStream(input);
		InputStreamReader reader = new InputStreamReader(stream);

		T phpPackage = fromReader(reader, classOfT);
		((IOPackage)phpPackage).setPath(input.getAbsolutePath());
		return phpPackage;
	}
	
	protected static <T> T fromJson(String json, Class<T> classOfT) {
		return fromReader(new StringReader(json), classOfT);
	}
	
	private static <T> T fromReader(Reader input, Class<T> classOfT) {
		T phpPackage = null;
		try {
			Gson gson = getBuilder();
			phpPackage = (T) gson.fromJson((Reader)input, classOfT);
			
			// gson.fromJson returns null if file is empty, make a blank package
			if (phpPackage == null) {
				Class<T> packageClass = classOfT;
				Constructor<T> constructor;
				constructor = packageClass.getConstructor();
				phpPackage = constructor.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phpPackage;
	}
	
	public static Gson getBuilder() {
		if (builder == null) {
			builder = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapter(License.class, License.getSerializer())
				.registerTypeAdapter(Support.class, Support.getSerializer())
				.registerTypeAdapter(Persons.class, Persons.getSerializer())
				.registerTypeAdapter(Dependencies.class, Dependencies.getSerializer())
				.registerTypeAdapter(Versions.class, Versions.getSerializer())
				.create();
		}
		return builder;
	}
}
