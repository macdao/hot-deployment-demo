/**
 * Copyright(c) 2005 Ceno Techonologies, Ltd.
 *
 * History:
 *   2010-4-10 16:20:38 Created by xqi
 */
package com.macdao;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.instrument.ClassDefinition;

/**
 * Created by xqi.
 *
 * @author <a href="mailto:xqi@ceno.cn">xqi</a> 
 * @version 1.0 2010-4-10 16:20:38
 */
public class MyClass
{
    private Class clazz;

    private String filePath;

    private long timestamp;

    public MyClass(String filePath, Class clazz)
    {
        this.clazz = clazz;
        this.filePath = filePath;
        this.timestamp = new File(filePath).lastModified();
    }

    public boolean isModified()
    {
        return new File(filePath).lastModified() > timestamp;
    }

    public ClassDefinition toDefinition()
    {
        try {
            this.timestamp = new File(filePath).lastModified();
            return new ClassDefinition(clazz, ClassUtil.getByte(filePath));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString()
    {
        return "MyClass{'" + filePath + "'}";
    }
}
