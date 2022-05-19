package core;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import annotation.Resource;

public class Container {
	public Object getInstance(Class clazz) throws Exception {
		Object obj = clazz.getConstructor().newInstance();
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields) {
			Annotation[] annotations = f.getAnnotations();
			
			for(Annotation an : annotations) {
				
				if(an instanceof Resource) {		
					Class target = this.findImplClass(f.getType());
					// 再帰呼び出し
					Object instance = this.getInstance(target);
					
					f.setAccessible(true);
					f.set(obj, instance);
				}
			}
		}
		
		return obj;
	}
	
	private static List<File> getClassList(File targetDir) {
		List<File> list = new ArrayList<>();
		File[] files = targetDir.listFiles();
		for(File file : files) {
			if(file.isDirectory()) {
				// 再帰呼び出し
				list.addAll(getClassList(file));
				continue;
			}
			if(file.getName().endsWith(".class")) {
				list.add(file);
			}
		}
		return list;
	}
	public Class findImplClass(Class target) throws Exception {
		// 現在実行中のプログラムの読み込みをしているクラスローダー(クラスファイルを読み込みメモリ上に展開する役割)を取り出す
		// getResourceに空文字を渡してgetPathすることで実行中のプログラムが配置されるパスが取得できる。
		String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		File root = new File(classPath);
		List<File> list = getClassList(root);
		for(File file : list) {
			// 完全修飾クラス名に不要なパス
			// ★リファクタリングの必要あり
			String delPath = "C:\\pleiades\\workspace\\ReflectionSample\\bin\\";
			// 完全修飾クラス名を取得
			String name = file.getAbsolutePath()
					.replace(delPath, "")
					.replace("\\", ".")
					.replace(".class", "");
			Class clazz = Class.forName(name);
			if(clazz.isInterface()) {
				continue;
			}
			// targetの型にアサインできるclazzを返却
			// ★複数ある場合は未対応。
			if(target.isAssignableFrom(clazz)) {
				return clazz;
			}
		}
		return null;
	}
}
