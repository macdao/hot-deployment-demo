/**
 * Copyright(c) 2005 Ceno Techonologies, Ltd.
 *
 * History:
 *   2010-4-10 12:05:25 Created by xqi
 */
package com.macdao;

import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Method;

/**
 * Created by xqi.
 *
 * @author <a href="mailto:xqi@ceno.cn">xqi</a> 
 * @version 1.0 2010-4-10 12:05:25
 */
public class Main
{
    public static void main(String[] args)
            throws Exception
    {
        Object obj = null;
        while (true) {
            detectChanges();
            Class clazz = getClass("com.macdao.Macdao");
            if (obj == null || !obj.getClass().isAssignableFrom(clazz)) {
                obj = clazz.newInstance();
                System.out.println("new instance of " + clazz);
            }
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals("say")) {
                    method.invoke(obj);
                }
            }
            Thread.sleep(1000);
        }
    }

    private static void detectChanges()
            throws ClassNotFoundException, UnmodifiableClassException
    {
        try {
            classLoader.detectChanges();
        }
        catch (UnsupportedOperationException e) {
            classLoader = null;
        }
    }

    private static Class getClass(String s)
            throws ClassNotFoundException
    {
        if (classLoader == null) {
            classLoader = new MyClassLoader();
        }
        return classLoader.loadClass(s);
    }

    private static MyClassLoader classLoader = new MyClassLoader();


}
