package com.spi.test;

public class RaviTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		B b1 = new B();
		b1.printLength();
		A a1 = new A();
		a1.printLength();

	}

}
class A {
	A(){
		System.out.println("A() Cons");
		str = "abc";
	}
	String str;
	void printLength(){
		System.out.println(str.length());
	}
}
class B extends A{
	B(){
		System.out.println("B() Cons");
	}
}