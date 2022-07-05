<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="素材の投稿" />
	<jsp:param name="style" value="post-material" />
</jsp:include>

<jsp:include page="./../components/header-after.jsp" />
<div id="main">
	<jsp:include page="./../components/SideBar.jsp" />
	<div id="C">
		<div class="inner">
			<div class="post">
				<div class="centering-ttl-box">
					<h2 class="centering-ttl">素材を検索</h2>
				</div>
				<div class="information">
					<ul>
						<form action="ReceiveInfo.jsp" method="post">
							<p>
								キーワード:<br> <input type="search" name="keyword" size="40"
									placeholder="キーワード" class="text-box">
							</p>
							<p>
								価格：<br> <input type="number" name="searchPrice" size="40"
									placeholder="152" class="text-box" max="100000">
							</p>
							<p>カテゴリー：
							<div class="select">
								<select name="searchCategoryId" class="text-box">
									<option value="1">BGM</option>
									<option value="2">イラスト</option>
									<option value="3">動画</option>
								</select>
							</div>
							</p>
							<p>
								年齢制限：<br> <input type="radio" name="SearchIsAdlut"
									value="1"> <label for="adlut">成人向け</label><br>
								<input type="radio" name="SearchIsAdlut" value="0">
								<label for="adlut">非成人向け</label>
							</p>
							<input type="submit" value="検索"><br>
						</form>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./../components/Footer.jsp" />
</body>
</html>
