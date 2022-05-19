package sample;

import annotation.Resource;

public class A {
	@Resource
	private B b;
	
	@Resource
	private E e;
	
	public void hello() {
		System.out.println("a");
		b.hello();
		e.hello();
	}
}
