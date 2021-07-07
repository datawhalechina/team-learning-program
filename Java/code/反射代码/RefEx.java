package test;

import static java.lang.System.out;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

public class RefEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="hello";
		Class c=s.getClass();//获取类型
		System.out.println(c);
		String modifiers=Modifier.toString(c.getModifiers());//获取修饰符
		System.out.println(modifiers);
		
		 TypeVariable<?>[] tv = c.getTypeParameters();//获取泛型类型参数
		    if (tv.length != 0) {
			out.format("  ");
			for (TypeVariable<?> t : tv)
			    out.format("%s ", t.getName());
			out.format("%n%n");
		    } else {
			out.format("  -- No Type Parameters --%n%n");
		    }
		    
		    Class superclass=c.getSuperclass();//获取类型的父类
		    System.out.println(superclass);
		    
		    Type[] intfs = c.getGenericInterfaces();//获取类实现的接口
		    if (intfs.length != 0) {
			for (Type intf : intfs)
			    out.format("  %s%n", intf.toString());
			out.format("%n");
		    } else {
			out.format("  -- No Implemented Interfaces --%n%n");
		    }
		    
		    Annotation[] ann = c.getAnnotations();//获取类的注解
		    if (ann.length != 0) {
			for (Annotation a : ann)
			    out.format("  %s%n", a.toString());
			out.format("%n");
		    } else {
			out.format("  -- No Annotations --%n%n");
		    }
		    
		    Constructor[] cons=c.getDeclaredConstructors();//获取类的构造方法
		    for(Constructor con:cons){
		    	System.out.println(con.toGenericString());
		    	
		    }
		    out.format("%n%n");
		    
		    Field[] fs=c.getDeclaredFields();//获取类的数据成员（成员变量）
		    for(Field f:fs){
		    	System.out.println(f.toGenericString());
		    	
		    }
		    out.format("%n%n");
		    
		    Method[] ms=c.getMethods();//获取类的公有成员方法（包含从父类继承的）
		    for(Method m:ms){
		    	System.out.println(m.toGenericString());
		    	
		    }
		    out.format("%n%n");
		    
	}

}
