package test;

import sys_practice.User;

public class TestUser {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		User user = new User();
		
		user.dataloadById(100);
		for(int i=0;i<user.getNum();i++) {
			System.out.println(user.getUserId(i));
			System.out.println(user.getEmail(i));
			System.out.println(user.getExplanation(i));
			System.out.println(user.getIcon(i));
		}
		
		user.getDisplayNameById(100);

	}

}
