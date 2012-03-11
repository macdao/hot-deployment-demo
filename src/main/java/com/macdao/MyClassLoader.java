/**
 * Copyright(c) 2005 Ceno Techonologies, Ltd.
 *
 * History:
 *   2010-4-10 16:11:05 Created by xqi
 */
package com.macdao;

import java.io.FileNotFoundException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xqi.
 *
 * @author <a href="mailto:xqi@ceno.cn">xqi</a> 
 * @version 1.0 2010-4-10 16:11:05
 */
public class MyClassLoader
        extends ClassLoader
{
    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {

        Class c = findLoadedClass(name);
        if (c == null) {
            c = loadApplicationClass(name);
            if (c == null) {
                c = super.loadClass(name, false);
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

    private Class loadApplicationClass(String name)
    {
        byte[] clazzBytes;
        String filePath;
        try {
            filePath = prefix + name.replace(".", "/") + ".class";
            clazzBytes = ClassUtil.getByte(filePath);
            System.out.println("loaded class:" + name);
        }
        catch (FileNotFoundException e) {
            return null;
        }

        Class clazz = defineClass(name, clazzBytes, 0, clazzBytes.length);
        classes.add(new MyClass(filePath, clazz));
        return clazz;
    }

    private List<MyClass> classes = new ArrayList<MyClass>();
    private String prefix = "target/classes/";

    public void detectChanges()
            throws ClassNotFoundException, UnmodifiableClassException
    {
        List<MyClass> modifieds = new ArrayList<MyClass>();
        for (MyClass myClass : classes) {
            if (myClass.isModified()) {
                modifieds.add(myClass);
            }
        }
        if (!modifieds.isEmpty()) {
            System.out.println("class modified:" + modifieds);
            List<ClassDefinition> newDefinitions = new ArrayList<ClassDefinition>();
            for (MyClass myClass : modifieds) {
                newDefinitions.add(myClass.toDefinition());
            }
            PreMain.getInstrumentation().redefineClasses(
                    newDefinitions.toArray(new ClassDefinition[newDefinitions.size()]));
        }
    }
}
