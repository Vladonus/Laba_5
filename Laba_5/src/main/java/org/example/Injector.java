package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;
/**
 * A class that performs dependency injection on any object that contains fields marked with @AutoInjectable
 */
class Injector<T> {
    private Properties properties;

    Injector(String pathToPropertiesFile) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(new File(pathToPropertiesFile)));
    }
    /**
     * inject accepts an arbitrary object, checks it for the presence of fields with an annotation AutoInjectable.
     * If there is such a field, look at its type and look for the implementation in the file inj.properties.

     * @param obj an object of any class
     * @return returns an object with initialized comments with an annotation AutoInjectable
     */
    T inject(T obj) throws IOException, IllegalAccessException, InstantiationException {
        Class dependency;

        Class cl = obj.getClass();

        Field[] fields = cl.getDeclaredFields();
        for (Field field: fields){
            Annotation a = field.getAnnotation(AutoInjectable.class);
            if (a != null){
                /**
                 * Method toString returns a string in the form of type and full_name variables
                 */
                String[] fieldType = field.getType().toString().split(" ");
                String equalsClassName = properties.getProperty(fieldType[1], null);
                if (equalsClassName != null){
                    try {
                        dependency = Class.forName(equalsClassName);
                    } catch (ClassNotFoundException e){
                        System.out.println("Class was not found " + equalsClassName);
                        continue;
                    }
                    /** for private fields
                     * it is necessary to call setAccessible with the true parameter
                     */
                    field.setAccessible(true);
                    field.set(obj, dependency.newInstance());
                }
                else
                    System.out.println("Properties was not found for field type " + fieldType[1]);
            }
        }
        return obj;
    }
}
