package org.getcomposer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.getcomposer.collection.Dependencies;
import org.getcomposer.collection.GenericArray;
import org.getcomposer.collection.License;
import org.getcomposer.collection.Persons;
import org.getcomposer.collection.Psr0;
import org.getcomposer.collection.Repositories;
import org.getcomposer.collection.Versions;
import org.getcomposer.entities.Autoload;
import org.getcomposer.entities.Config;
import org.getcomposer.entities.Distribution;
import org.getcomposer.entities.Extra;
import org.getcomposer.entities.GenericEntity;
import org.getcomposer.entities.Person;
import org.getcomposer.entities.Source;
import org.getcomposer.entities.Support;
import org.getcomposer.repositories.ComposerRepository;
import org.getcomposer.repositories.GitRepository;
import org.getcomposer.repositories.MercurialRepository;
import org.getcomposer.repositories.PackageRepository;
import org.getcomposer.repositories.PearRepository;
import org.getcomposer.repositories.SubversionRepository;
import org.getcomposer.repositories.VcsRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A package that can be read from a file or string and also dump to the latter one.
 * 
 * @author Thomas Gossmann <gos.si>
 * 
 */
public class Resource extends GenericEntity {
	
	private transient String path;
	private transient static Gson gson = null;

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
		((Resource)phpPackage).setPath(input.getAbsolutePath());
		return phpPackage;
	}
	
	protected static <T> T fromJson(String json, Class<T> classOfT) {
		return fromReader(new StringReader(json), classOfT);
	}
	
	protected static <T> T fromReader(Reader input, Class<T> classOfT) {
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
		if (gson == null) {
			GsonBuilder builder = new GsonBuilder()
				.setPrettyPrinting();
			
			try {
				Type[] types = getBuilderRegistry();
				for (Type type : types) {
					Class<?> clz = (Class<?>) type;
					
					Method caller = clz.getMethod("getSerializer");
					Object serializer = caller.invoke(null);
					
					builder = builder.registerTypeAdapter(type, serializer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			gson = builder.create();
		}
		return gson;
	}
	
	public static Type[] getBuilderRegistry() {
		return new Type[] {
			// entities
			Person.class,
			License.class,
			Autoload.class,
			Psr0.class,
			Support.class,
			Config.class,
			Extra.class,
			ComposerRepository.class,
			GitRepository.class,
			MercurialRepository.class,
			PearRepository.class,
			PackageRepository.class,
			SubversionRepository.class,
			VcsRepository.class,
			ComposerPackage.class,
			RepositoryPackage.class,
			Distribution.class,
			Source.class,

			
			// collections
			Persons.class,
			Dependencies.class,
			Repositories.class,
			Versions.class,
			
			// generics
			GenericArray.class,
			GenericEntity.class
		};
	}
}
