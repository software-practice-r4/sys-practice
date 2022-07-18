package test;

import sys_practice.Cart;

public class TestCart {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Cart cart = new Cart();
		
		for(int i=0;i<100;i++) {
			System.out.println(cart.removeCart(-100, 1));
		}
	}

}
