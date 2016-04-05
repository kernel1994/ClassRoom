package team.dx.classroom.utils;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CompilerUtils {

	/**
	 * 封装了编译java文件，得到返回值
	 * 参数
	 * path:文件路径，不包括文件名称
	 * filename文件名称
	 * url = path + filename
	 * */
	public static Object[] run(String path, String filename, Object[] args) {

		try {
			// java编译器
			 JavaCompiler complier = ToolProvider.getSystemJavaCompiler();     
			// 文件管理器，参数1：diagnosticListener 监听器,监听编译过程中出现的错误
		        StandardJavaFileManager sjf =   
		                complier.getStandardFileManager(null, null, null);  
		        Iterable<? extends JavaFileObject> it = sjf.getJavaFileObjects(path + "/" + filename);
		     
		        CompilationTask task = complier.getTask(null, sjf, null, null, null, it);  
		        task.call();  //调用创建  ,创建class文件
		        sjf.close();  
		           
		        //URL urls[] = new URL[]{ new URL("file:/" + path)}; //储存文件目录的地址
		        //URLClassLoader uLoad = new URLClassLoader(urls);  //classloader从哪个目录找？
		        
		        // example   Test.class  -->  Test 去掉后缀
		        String name= filename.split("\\.")[0];
		        Class clazz = Class.forName(name);
		        Object[] values = { clazz.getMethods()[0].invoke(clazz.newInstance(), args)};
		        
		        
		        //下面这两句代码在console中可以正确运行，但是不能在serlet运行，会出现ClassNotFoundException
		        // example   Test.class  -->  Test 去掉后缀
		        /*Class c = uLoad.loadClass(filename.split("\\.")[0]);
		        Object[] values = { c.getMethods()[0].invoke(c.newInstance(), args)};
		        */
		        return values;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}