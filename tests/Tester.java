package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tester {

    public static boolean isTypeAbstractClass(final String typeName) {
        try {
            final Class<?> clazz = Class.forName(typeName);
            return Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isTypeClass(final String typeName) {
        try {
            final Class<?> clazz = Class.forName(typeName);
            return !Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean implementsTypeInterface(final String parentName, final String childName) {
        try {
            final Class<?> parentClazz = Class.forName(parentName);
            final Class<?> childClazz = Class.forName(childName);
            return parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasTypeDeclaredField(final String typeName, final String fieldName) {
        try {
            final Class<?> clazz = Class.forName(typeName);
            final Field[] declaredFields;
            final Field[] fields = declaredFields = clazz.getDeclaredFields();
            for (final Field field : declaredFields) {
                if (fieldName.equals(field.getName())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isFieldPrivate(final String typeName, final String fieldName) {
        try {
            final Class<?> clazz = Class.forName(typeName);
            final Field field = clazz.getDeclaredField(fieldName);
            return Modifier.isPrivate(field.getModifiers());
        } catch (ClassNotFoundException | NoSuchFieldException ex2) {

            return false;
        }
    }

    public static boolean hasTypeDeclaredMethod(final String typeName, final String methodName, final Class<?>[] parameterTypes) {
        try {
            final Class<?> clazz = Class.forName(typeName);
            final Method[] declaredMethods;
            final Method[] methods = declaredMethods = clazz.getDeclaredMethods();
            for (final Method method : declaredMethods) {
                final Class<?>[] types = method.getParameterTypes();
                if (methodName.equals(method.getName()) && Arrays.equals(types, parameterTypes)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isMethodPublic(final String typeName, final String methodName, final Class<?>[] parameterTypes) {
        try {
            final Class<?> clazz = Class.forName(typeName);
            final Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return Modifier.isPublic(method.getModifiers());
        } catch (ClassNotFoundException | NoSuchMethodException ex2) {


            return false;
        }
    }

    public static boolean hasFieldType(final String typeName, final String fieldName, final Class<?> fieldType) {
        try {
            final Class<?> clazz = Class.forName(typeName);
            final Field[] declaredFields;
            final Field[] fields = declaredFields = clazz.getDeclaredFields();
            for (final Field field : declaredFields) {
                if (fieldName.equals(field.getName()) && field.getType().equals(fieldType)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
