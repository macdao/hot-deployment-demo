/**
 * Copyright(c) 2005 Ceno Techonologies, Ltd.
 *
 * History:
 *   2010-4-10 12:07:29 Created by xqi
 */
package com.macdao;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by xqi.
 *
 * @author <a href="mailto:xqi@ceno.cn">xqi</a> 
 * @version 1.0 2010-4-10 12:07:29
 */
public class ClassUtil
{
    public static byte[] getByte(String filePath)
            throws FileNotFoundException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(filePath);

        byte[] b = new byte[1024];
        int i;
        try {
            while ((i = fis.read(b)) != -1) {
                baos.write(b, 0, i);
            }
            return baos.toByteArray();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {

            try {
                fis.close();
            }
            catch (IOException e) {
                e.printStackTrace();

            }
            try {
                baos.close();
            }
            catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
