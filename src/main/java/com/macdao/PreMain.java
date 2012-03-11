/**
 * Copyright(c) 2005 Ceno Techonologies, Ltd.
 *
 * History:
 *   2010-3-21 23:30:43 Created by xqi
 */
package com.macdao;

import java.lang.instrument.Instrumentation;

/**
 * Created by xqi.
 *
 * @author <a href="mailto:xqi@ceno.cn">xqi</a> 
 * @version 1.0 2010-3-21 23:30:43
 */
public class PreMain
{
    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation inst)
    {
        instrumentation = inst;
    }

    public static Instrumentation getInstrumentation()
    {
        return instrumentation;
    }
}
