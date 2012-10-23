package org.getcomposer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;

import org.getcomposer.serialization.LicenseSerializer;
import org.getcomposer.serialization.VersionsSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

		T phpPackage = fromObject(reader, classOfT);
		((IOPackage)phpPackage).setPath(input.getAbsolutePath());
		return phpPackage;
	}
	
	protected static <T> T fromJson(String json, Class<T> classOfT) throws FileNotFoundException {
		return fromObject(json, classOfT);
	}
	
	private static <T> T fromObject(Object input, Class<T> classOfT) throws FileNotFoundException {
		T phpPackage = null;
		try {
			Gson gson = getBuilder();
			if (input instanceof Reader) {
				phpPackage = (T) gson.fromJson((Reader)input, classOfT);
			} else if (input instanceof String) {
				phpPackage = (T) gson.fromJson((String)input, classOfT);
			}
			
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
				.registerTypeAdapter(License.class, new LicenseSerializer())
				.registerTypeAdapter(Versions.class, new VersionsSerializer())
				.create();
		}
		return builder;
	}
}
