package org.smart4j.chapter2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInit) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className, isInit, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.info("Class加载失败:{}", e);
        }
        return clazz;
    }

    /**
     * 获取指定包名下得所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String packagePath = url.getPath().replaceAll("%20", " ");
                    addClass(classSet, packagePath, packageName);
                } else if ("jar".equals(protocol)) {
                    JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
                    if (urlConnection != null) {
                        JarFile jarFile = urlConnection.getJarFile();
                        if (jarFile != null) {
                            Enumeration<JarEntry> entries = jarFile.entries();
                            while (entries.hasMoreElements()) {
                                JarEntry jarEntry = entries.nextElement();
                                String jarEntryName = jarEntry.getName();
                                if (".class".equals(jarEntryName)) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                    doAddClass(classSet, className);
                                }

                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.info("获取类集合失败:{}", e);
        }
        return null;
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {

    }
}
