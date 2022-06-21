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
                            <h2 class="centering-ttl">
                                素材の投稿
                            </h2>
                        </div>
                        <div class="information">
                            <ul>
                                <form action="" method="post">
                                    <p>
                                        タイトル：<br><input type="text" name="title" size="40" placeholder="タイトル" class="text-box">
                                    </p>
                                    <p>
                                        説明：<br><input type="text" name="explain" size="40" placeholder="説明" class="text-box">
                                    </p>
                                    <p>
                                        価格：<br><input type="text" name="price" size="40" placeholder="価格" class="text-box">
                                    </p>
                                    <p>
                                        カテゴリー：
                                    <div class="select">
                                        <select name="category" class="text-box" >
                                            <option value="A">A型</option>
                                            <option value="B">B型</option>
                                            <option value="O">O型</option>
                                            <option value="AB">AB型</option>
                                        </select>
                                    </div>
                                    </p>
                                    <p>
                                        カテゴリーの中にないとき：<br><input type="text" name="name" size="40" placeholder="カテゴリーの中にないとき" class="text-box">
                                    </p>
                                    <p>
                                        アップロードファイル：<br><input type="file" name="name" size="40" placeholder="画像" class="text-box">
                                    </p>
                                </form>
                            </ul>
                            <div class="completion">
                                <input type="submit" class="btn-square-so-pop" value="アップロード"><br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
