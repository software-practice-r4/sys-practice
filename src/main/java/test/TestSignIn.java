package test;

import sys_practice.SignIn;

public class TestSignIn {

	public static void main(String[] args) {
 		String email = "example@example.com";
		String pass = "example";
		
		SignIn signin = new SignIn();
		
		try {
			System.out.println(signin.signIn(email, pass));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		email = "example@example.co";
		pass = "example";
		
		try {
			System.out.println(signin.signIn(email, pass));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		email = "example@example.com";
		pass = "exam";
		
		try {
			System.out.println(signin.signIn(email, pass));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}

}
