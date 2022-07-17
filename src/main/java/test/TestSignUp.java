package test;

import sys_practice.SignUp;

public class TestSignUp {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		SignUp signup = new SignUp();
		
		String email = "test@test.com";
		String pass = "test";
		int qId = 100;
		String ans = "サンプル答え";
		
		System.out.println(signup.signUp(email, pass, qId, ans));
	}

}
