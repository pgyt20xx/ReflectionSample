package sample;

import annotation.Resource;

public class B {
	
	@Resource
	private C c;
	
	@Resource
	private D d;
	
	public void hello() {
		System.out.println("b");
		c.hello();
		d.hello();
	}
}
