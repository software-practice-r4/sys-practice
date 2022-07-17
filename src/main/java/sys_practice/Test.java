package sys_practice;

/*
 * @author keita
 * @version 1.0
 * */

public class Test {

	int i = 0;
	User user = new User();
	Category category = new Category();
	String result = null;

	//CategoryクラスのdispCategory()メソッドの検証
	public void doDispCategory() {
		category.dispCategory();
	}

	//CategoryクラスのdispCategory()メソッドの検証
	public void doGetCategoryNameById() {
		//正常値1 異常値100
		result = category.getCategoryNameById(1);
		System.out.println("Return value: " + result);
	}

}