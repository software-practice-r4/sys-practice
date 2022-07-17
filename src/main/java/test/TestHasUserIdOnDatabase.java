package test;

import sys_practice.User;

public class TestHasUserIdOnDatabase {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		User user = new User();
		boolean err=false;
		
		try {
			err = user.hasUserIdOnDatabase(1000000000);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			System.out.println(err);
		}
	}

}
