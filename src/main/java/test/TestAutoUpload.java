package test;

import sys_practice.Category;

public class TestAutoUpload {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Category category = new Category();
		String name = "hguahguahugha";
		
		try {
			name = category.getCategoryNameById(2);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			System.out.println(name);
		}
	}
}
