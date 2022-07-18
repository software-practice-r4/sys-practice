<jsp:useBean id="category" scope="session" class="sys_practice.Category" />
<%
try {
	category.dispCategory();
} catch (Exception e) {
%>
	System.err.println(e);
<%
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="sidebar">
			<h3>絞り込み検索</h3>
			<ul>
			<form action="./Narrowdown.jsp" method="GET">
				<h1>カテゴリー</h1>
				<div class="select">
					<select name="ndCategoryId" class="text-box" id="category_box" style="width: 100%;">
						<%
						for (int i = 0; i < category.getNum(); i++) {
						%>
						<option value=<%=category.getCategoryId(i)%>><%=category.getCategoryName(i)%></option>
						<%}%>
					</select>
				</div>
				<h1>価格</h1>
				<div class="select">
					<select name="ndPrice" class="text-box" id="price_box" style="width: 100%;">
						<option value="lt500">～&yen;500</option>
						<option value="mt500lt2000">&yen;500～&yen;2000</option>
						<option value="mt2000lt5000">&yen;2000～&yen;5000</option>
						<option value="mt5000">&yen;5000～</option>
					</select>
				</div>
				<h1>年齢制限</h1>
				<div class="select">
					<select name="ndIsAdult" class="text-box" id="adult_box" style="width: 100%;">
						<option value="0">全年齢</option>
						<option value="1">R-18</option>
					</select>
				</div>
				<br> <input type="submit" value="絞り込む" class="btn-square-pop">
			</form>
			</ul>
		</div>
		
		<script>
		window.addEventListener("load", ()=>{
			// カテゴリーが選択されていたら
			const categoryBox = document.getElementById("category_box");
			if(getParam("searchCategoryId") || getParam("ndCategoryId")){
				const searchCategoryId = getParam("searchCategoryId") ? getParam("searchCategoryId"): -1;
				const categoryId = getParam("ndCategoryId") ? getParam("ndCategoryId"): -1;
				for(let i=0;i<categoryBox.length;i++){
					if(searchCategoryId === categoryBox.options[i].value || categoryId === categoryBox.options[i].value){
						categoryBox.options[i].selected = true;
					}
				}

			}
			// 価格が選択されたら
			const priceBox = document.getElementById("price_box");
			if(getParam("searchPrice") || getParam("ndPrice")){
				const price = getParam("ndPrice") ? getParam("ndPrice") : "Z";
				const searchPrice = getParam("searchPrice")? getParam("searchPrice") : -1;
				
				if((searchPrice < 500 && searchPrice != -1) || (price === "lt500")){
					priceBox.options[0].selected = true;
					priceBox.options[1].selected = false;
					priceBox.options[2].selected = false;
					priceBox.options[3].selected = false;

				}else if(((500 <= searchPrice) && (searchPrice < 2000)) || (price === "mt500lt2000")){
					priceBox.options[0].selected = false;
					priceBox.options[1].selected = true;
					priceBox.options[2].selected = false;
					priceBox.options[3].selected = false;

				}
				
				else if(((2000 <= searchPrice) && (searchPrice < 5000)) || (price === "mt2000lt5000")){
					priceBox.options[0].selected = false;
					priceBox.options[1].selected = false;
					priceBox.options[2].selected = true;
					priceBox.options[3].selected = false;

				}
				else if((5000 <= searchPrice) || (price === "mt5000")){
					
					priceBox.options[0].selected = false;
					priceBox.options[1].selected = false;
					priceBox.options[2].selected = false;
					priceBox.options[3].selected = true;

				}
				else{
					priceBox.options[0].selected = false;
					priceBox.options[1].selected = false;
					priceBox.options[2].selected = false;
					priceBox.options[3].selected = false;

				}

			}
			// 年齢制限が選択されたら
			const isAdultBox = document.getElementById("adult_box");
			if(getParam("searchIsAdult") || getParam("ndIsAdult")){
				const isAdult = getParam("searchIsAdult") ? getParam("searchIsAdult") : 0;
				const age = getParam("ndIsAdult") ? getParam("ndIsAdult"): "0";

				// 年齢制限がR18なら
				if(isAdult === "1" || age === "1"){
					isAdultBox.options[0].selected = false;
					isAdultBox.options[1].selected = true;
				}
				// 年齢制限が全年齢なら
				else if(isAdult === "0" || age === "0"){
					isAdultBox.options[0].selected = true;
					isAdultBox.options[1].selected = false;
				}
			} 
		})
		</script>