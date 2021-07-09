package 方法;
import static java.lang.System.out;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;
public class Deet<T> {
	private boolean testDeet(Locale l) {
		out.format("Locale = %s, Language Code = %s%n", l.getDisplayName(), l.getLanguage());
		return true;
	}
	private boolean testFoo(Locale l) {
		return false;
	}
	private boolean testBar() {
		return true;
	}
	public static void main(String... args) {
		try {			
			//获取Deet类的Class实例
			Class<?> c = Deet.class;
			//有Class实例创建一个类对象
			Object t = c.newInstance();

			//获取Class实例对应的类型声明的所有的方法
			Method[] allMethods = c.getDeclaredMethods();
			for (Method m : allMethods) {
				String mname = m.getName();//获取方法的名字
				//如果方法的名字不以test开头，或者返回类型不是boolean型
				if (!mname.startsWith("test") || (m.getReturnType() != boolean.class)) {
					continue;
				}
				//获取方法的所有参数类型
				Type[] pType = m.getGenericParameterTypes();
				//如果有参数，并且第一个参数是Locale类型
				if ((pType.length != 1) || Locale.class.isAssignableFrom(pType[0].getClass())) {
					continue;
				}
				out.format("调用 %s()%n", mname);
				try {	
					//调用方法，o是方法返回值
					Object o = m.invoke(t, Locale.CHINA);
					out.format("%s() 返回 %b%n%n", mname, (Boolean) o);
				} catch (InvocationTargetException x) {
					x.printStackTrace();
				}			}
		} catch (InstantiationException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		}
	}

}
