package org.getcomposer;

import org.getcomposer.entities.GenericEntity;


/**
 * A package that can be read from a file or string and also dump to the latter one.
 * 
 * @author Thomas Gossmann <gos.si>
 * 
 */
public class Resource extends GenericEntity {
	
//	private transient String path;
//
//	public String getPath() {
//		return path;
//	}
//
//	public void setPath(String path) {
//		this.path = path;
//	}
	
//	public File toFile() {
//		return new File(path);
//	}
	
//	protected static <T> T fromFile(File input, Class<T> classOfT) throws FileNotFoundException {
//		InputStream stream = new FileInputStream(input);
//		InputStreamReader reader = new InputStreamReader(stream);
//
//		T phpPackage = fromReader(reader, classOfT);
//		((Resource)phpPackage).setPath(input.getAbsolutePath());
//		return phpPackage;
//	}
//	
//	protected static <T> T fromJson(String json, Class<T> classOfT) {
//		return fromReader(new StringReader(json), classOfT);
//	}
//	
//	protected static <T> T fromReader(Reader input, Class<T> classOfT) {
//		T phpPackage = null;
//		try {
//			Gson gson = getBuilder();
//			phpPackage = (T) gson.fromJson((Reader)input, classOfT);
//			
//			// gson.fromJson returns null if file is empty, make a blank package
//			if (phpPackage == null) {
//				Class<T> packageClass = classOfT;
//				Constructor<T> constructor;
//				constructor = packageClass.getConstructor();
//				phpPackage = constructor.newInstance();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return phpPackage;
//	}
//	
//	public static Gson getBuilder() {
//		if (gson == null) {
//			GsonBuilder builder = new GsonBuilder()
//				.setPrettyPrinting();
//			
//			try {
//				Type[] types = getBuilderRegistry();
//				for (Type type : types) {
//					Class<?> clz = (Class<?>) type;
//					
//					Method caller = clz.getMethod("getSerializer");
//					Object serializer = caller.invoke(null);
//					
//					builder = builder.registerTypeAdapter(type, serializer);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			gson = builder.create();
//		}
//		return gson;
//	}
//	
//	public static Type[] getBuilderRegistry() {
//		return new Type[] {
//			// entities
//			Person.class,
//			License.class,
//			Autoload.class,
//			Psr0.class,
//			Support.class,
//			Config.class,
//			Extra.class,
//			ComposerRepository.class,
//			GitRepository.class,
//			MercurialRepository.class,
//			PearRepository.class,
//			PackageRepository.class,
//			SubversionRepository.class,
//			VcsRepository.class,
//			ComposerPackage.class,
//			RepositoryPackage.class,
//			Distribution.class,
//			Source.class,
//
//			
//			// collections
//			Persons.class,
//			Dependencies.class,
//			Repositories.class,
//			Versions.class,
//			
//			// generics
//			GenericArray.class,
//			GenericEntity.class
//		};
//	}
}
