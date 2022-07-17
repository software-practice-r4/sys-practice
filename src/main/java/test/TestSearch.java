package test;

import sys_practice.Search;

public class TestSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Search s = new Search();
		
		try {
			s.narrowDown(2, "<500", 100);
			System.out.println(s.getNum());
			for(int i=0;i<s.getNum();i++) {
	            System.out.println(s.getMaterialId(i));
	            System.out.println(s.getMaterialName(i));
	            System.out.println(s.getPrice(i));
	            System.out.println(s.getThumbnail(i));
	            System.out.println(s.getCategoryId(i));
	            System.out.println(s.getCategoryName(i));
	            System.out.println(s.getProviderId(i));
	            System.out.println(s.getExplanation(i));
	            System.out.println(s.getIsAdult(i));
	        }
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		System.out.println(s.hasData(-1));
		System.out.println(s.hasData(1));
		System.out.println(s.hasData(100));
		System.out.println(s.hasData(-100));
		System.out.println(s.hasData(0));
	}

}
