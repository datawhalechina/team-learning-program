package 类;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
enum E { A, B };//枚举类型E

public class ClassEx {
	
	public static void main(String[] args) throws Exception {

	//第1种获取Class实例的方法 ：对象名.getClass()，原始类型、接口类型对应的Class实例不能用这种方法获取
		//类类型
		Class c = "foo".getClass();
		System.out.println(c);
		

		
		c = System.out.getClass();
		System.out.println(c);		
		
		Date date=new Date();
		c=date.getClass();
		System.out.println(c);
		
		//接口类型
		List<Date> array=new ArrayList<Date>();
		c=array.getClass();
		System.out.println(c);	
		
		//数组类型
		int[] a=new int[10];
		c=a.getClass();
		System.out.println(c+"  "+c.getCanonicalName());
		
		char[] b=new char[10];
		c=b.getClass();
		System.out.println(c+"  "+c.getName());
		
		double[] d=new double[10];
		c=d.getClass();
		System.out.println(c);
		
		long[] l=new long[10];
		c=l.getClass();
		System.out.println(c);
		
		Date[] f=new Date[10];
		c=f.getClass();
		System.out.println(c+"  "+c.getCanonicalName());
		
		//枚举类型
		E e=E.A;
		c=e.getClass();
		System.out.println(c);
		
		System.out.println("*****************************");
		
		//基本类型，编译错误
		//int a=10;
		//c=a.getClass();
		
	//第2种获取Class实例的方法 ：类型名.class，所有类型对应的Class实例都可以用这种方法获取
		c=String.class;
		System.out.println(c);
		
		c=Date.class;
		System.out.println(c);
		
		c=List.class;
		System.out.println(c);
		
		c=int.class;
		System.out.println(c);
		
		c=int[][].class;
		System.out.println(c);
		
		c=Date[][].class;
		System.out.println(c);
		
		c=E.class;
		System.out.println(c);
		
		System.out.println("*****************************");
		
	//第3种获取Class实例的方法 ：Class.forName(类全名字符串)，原始类型对应的Class实例不可以用这种方法获取
		c=Class.forName("java.lang.String");
		System.out.println(c);
		
		c=Class.forName("java.util.List");
		System.out.println(c);
		
		c=Class.forName("[I");//int数组类型int[]
		System.out.println(c);	
		
		c = Class.forName("[D");//double型数组double[]
		System.out.println(c);
		
		c = Class.forName("[[Ljava.lang.String;");//String二维数组String[][]
		System.out.println(c);
		
		c=Class.forName("类.E");
		System.out.println(c);
		
		System.out.println("*****************************");
	
  //基本类型包装类中的TYPE成员的值是基本类型对应的Class类实例
		c=Double.TYPE;
		System.out.println(c);
		c=Void.TYPE;
		System.out.println(c);
	}
}
