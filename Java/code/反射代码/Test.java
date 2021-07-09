package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	public static String classname="类.Student";
	public static String fieldname="name";
	public static String methodname="setName";
	public static String[] mptypes= {"java.lang.String"};
	public static Object fvalue=new String("xiaolin");
	public static Object mp1=new String("xiaohong");
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class c=Class.forName(classname);//根据类名字符串获得类对应的Class实例
			//第一种用反射创建类对象的方法，相当于调用类的无参数构造方法
			Object o=c.newInstance();//Student o=new Student();
			
			//使用反射访问类对象的属性（给属性设置值，获取属性的值）
			Field f=c.getDeclaredField(fieldname);//根据Field名，获得Field对象
			f.setAccessible(true);
			f.set(o,fvalue);//o.name="xiaolin";使用反射给o对象的f代表的属性设置值为发value的值
			System.out.println(f.get(o));//获取o对象的f属性对应的值，并输出
			
			
			
			@SuppressWarnings("unchecked")
			//使用反射调用对象的方法
			//使用反射根据方法名，和参数类型、参数数量来获取具体某个方法
			Method m=c.getDeclaredMethod(methodname, Class.forName(mptypes[0]));//String.class
			//方法的调用，调用o对象的m方法，方法的实际参数是mp1，mo是方法调用的返回值
			Object mo=m.invoke(o,mp1);//o.setName("xiaohong");
			System.out.println(mo);
			//调用了o对象名为setAge，参数有1个，类型是int型的方法，实际参数是21
			m=c.getDeclaredMethod("setAge", int.class);
			m.invoke(o, 21);
			System.out.println(o);
			
			/*Method m=c.getDeclaredMethod(methodname);
			Object mo=m.invoke(o);
			System.out.println(mo);
			System.out.println(f.get(o));*/
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			//第二种用反射创建类对象的方法，先获取具体的Constructor，再用Constructor去创建对象
			Constructor con=c.getDeclaredConstructor(String.class,int.class);//根据构造方法参数类型来获取某个构造方法
			Object o1=con.newInstance("Mary",20);
			//用Constructor创建类对象，相当于Student o1=new Student("Mary",20);
			System.out.println(o1);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
