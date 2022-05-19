package sample;

import core.Container;

public class Main {
	public static void main(String[] args) throws Exception {
		Container con = new Container();
		A a = (A) con.getInstance(A.class);
		a.hello();
	}
}
