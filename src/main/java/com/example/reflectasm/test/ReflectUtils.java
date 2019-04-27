package com.example.reflectasm.test;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 测试结论，
 * 反射时长     优 -> 劣
 * asm by name -> asm by index -> accessible java -> common java
 *
 * @author <a href="mailto:zhongzhangzhi@xiniaoyun.com">zhongzhangzhi(zhongzhangzhi@xiniaoyun.com)</a>
 */
public class ReflectUtils {


    public static void main(String[] args) throws Exception{
        asmReflectByName(data());
        asmReflectByIndex(data());
        commonReflect(data());
        accessibleReflect(data());
    }

    /**
     * asm 根据name 反射
     * @param pojos
     */
    public static void asmReflectByName(List<TestPOJO> pojos) {

        List<String> res = new ArrayList<>();

        Long time = System.currentTimeMillis();
        MethodAccess access = MethodAccess.get(TestPOJO.class);


        for (int i = 0; i < pojos.size(); i++) {
            String name = String.valueOf(access.invoke(pojos.get(i), "getName"));
            String id = String.valueOf(access.invoke(pojos.get(i), "getId"));
            res.add(name);
            res.add(id);
        }

        System.out.println("asm Reflect By Name>> 耗时：" + (System.currentTimeMillis() - time) + "ms; 次数：" + res.size());
    }

    /**
     * asm 根据Index 反射
     * @param pojos
     */
    public static void asmReflectByIndex(List<TestPOJO> pojos) {

        List<String> res = new ArrayList<>();

        Long time = System.currentTimeMillis();
        MethodAccess access = MethodAccess.get(TestPOJO.class);
        int nameIndex = access.getIndex("getName");
        int idIndex = access.getIndex("getId");


        for (int i = 0; i < pojos.size(); i++) {
            String name = String.valueOf(access.invoke(pojos.get(i), nameIndex));
            String id = String.valueOf(access.invoke(pojos.get(i), idIndex));
            res.add(name);
            res.add(id);
        }

        System.out.println("asm Reflect By Index>> 耗时：" + (System.currentTimeMillis() - time) + "ms; 次数：" + res.size());
    }

    /**
     * 常规java 反射
     * @param pojos
     * @throws Exception
     */
    public static void commonReflect(List<TestPOJO> pojos) throws Exception{
        List<String> res = new ArrayList<>();

        Class<?> c = TestPOJO.class;

        Long time = System.currentTimeMillis();
        for (int i = 0; i < pojos.size(); i++) {
            Method m = c.getMethod("getName");
            String name = String.valueOf(m.invoke(pojos.get(i)));
            m = c.getMethod("getId");
            String id = String.valueOf(m.invoke(pojos.get(i)));
            res.add(name);
            res.add(id);
        }

        System.out.println("java Reflect >> 耗时：" + (System.currentTimeMillis() - time) + "ms; 次数：" + res.size());
    }

    /**
     * 避过安全验证的Java 反射
     * @param pojos
     * @throws Exception
     */
    public static void accessibleReflect(List<TestPOJO> pojos) throws Exception {
        List<String> res = new ArrayList<>();

        Class<?> c = TestPOJO.class;
        Long time = System.currentTimeMillis();
        for (int i = 0; i < pojos.size(); i++) {
            Method m = c.getMethod("getName");
            //  避过java安全验证
            m.setAccessible(true);
            String name = String.valueOf(m.invoke(pojos.get(i)));
            m = c.getMethod("getId");
            //  避过java安全验证
            m.setAccessible(true);
            String id = String.valueOf(m.invoke(pojos.get(i)));
            res.add(name);
            res.add(id);
        }

        System.out.println("java accessible Reflect >> 耗时：" + (System.currentTimeMillis() - time) + "ms; 次数：" + res.size());
    }

    public static List<TestPOJO> data() {
        List<TestPOJO> pojos = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            TestPOJO pojo = new TestPOJO("钟章志" + i, i);
            pojos.add(pojo);
        }
        return pojos;
    }
}
