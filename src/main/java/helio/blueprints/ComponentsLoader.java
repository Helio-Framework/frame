package helio.blueprints;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * This class loads components into HELIO 
 * @author Andrea Cimmino
 * @author Juan Cano Benito
 */

class ComponentsLoader<C> {

	 public C loadClass(String jar, String classpath, Class<C> parentClass) throws Exception {
	   C instance = null;
	   if(jar==null)
		   return loadFromCode(classpath, parentClass);
	   if( (jar.startsWith(".") || jar.startsWith("/")) && !jar.startsWith("http")) {
		   // load from file
			instance = loadFromFile(new File(jar),  classpath, parentClass, false);
	   }else if(jar.startsWith("htt")){
		   instance = loadFromURL(new URL(jar),  classpath, parentClass);
	   }else if(isWindows() && Character.isUpperCase(jar.charAt(0))){
		   // load from file
			instance = loadFromFile(new File(jar),  classpath, parentClass, true);
	   }else {
		   throw new Exception("jar is provided by an unsuported protocol, currently available file or http(s)");
	   }
		return instance;
	}
	 
	 /**
	  * Check if the OS is Windows (any version)
	  * @return true if the OS is Windows
	  */
	 public static boolean isWindows() {
		 return System.getProperty("os.name").startsWith("Windows");
	 }

	 private C loadFromURL(URL jar, String classpath, Class<C> parentClass) throws ClassNotFoundException  {
			C newInstance  = null;
			try {
			        ClassLoader loader = URLClassLoader.newInstance(
			            new URL[] { jar.toURI().toURL() },
			            getClass().getClassLoader()
			        );
			       Class<?> clazz = Class.forName(classpath, true, loader);
			        Class<? extends C> newClass = clazz.asSubclass(parentClass);
			        Constructor<? extends C> constructor = newClass.getConstructor();
			        newInstance = constructor.newInstance();

			      } catch (Exception e) {
			        e.printStackTrace();
			      }

			if(newInstance==null)
			    throw new ClassNotFoundException("Class " + classpath + " wasn't found in plugin file " + jar);
			return newInstance;
		}

	private C loadFromFile(File jar, String classpath, Class<C> parentClass, boolean isWindows) throws ClassNotFoundException  {
		C newInstance  = null;
		String fileURL = "file://";
		if(isWindows)
			fileURL = "file:///";
		try {
		        ClassLoader loader = URLClassLoader.newInstance(
		            new URL[] { new URL(fileURL+jar.getCanonicalPath()) },
		            getClass().getClassLoader()
		        );
		       Class<?> clazz = Class.forName(classpath, true, loader);
		        Class<? extends C> newClass = clazz.asSubclass(parentClass);
		        Constructor<? extends C> constructor = newClass.getConstructor();
		        newInstance = constructor.newInstance();

		      } catch (Exception e) {
		        e.printStackTrace();
		      }

		if(newInstance==null)
		    throw new ClassNotFoundException("Class " + classpath + " wasn't found in plugin file " + jar);
		return newInstance;
	}

	private C loadFromCode(String classpath, Class<C> parentClass) throws ClassNotFoundException  {
		C newInstance  = null;
		try {
		        ClassLoader loader = ClassLoader.getSystemClassLoader();
		       Class<?> clazz = Class.forName(classpath, true, loader);
		        Class<? extends C> newClass = clazz.asSubclass(parentClass);
		        Constructor<? extends C> constructor = newClass.getConstructor();
		        newInstance = constructor.newInstance();

		      } catch (Exception e) {
		    	  throw new ClassNotFoundException(e.toString());
		      }

		if(newInstance==null)
		    throw new ClassNotFoundException("Class " + classpath + " wasn't found ");
		return newInstance;
	}

}