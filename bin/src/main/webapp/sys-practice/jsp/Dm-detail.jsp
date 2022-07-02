<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="DM詳細" />
	<jsp:param name="style" value="dm-detail" />
</jsp:include>

        <jsp:include page="./../components/header-after.jsp" />
        <div id="main">
            <jsp:include page="./../components/SideBar.jsp" />
            <div id="C">
                <div class="inner">
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                ○○さんとのDM
                            </h2>
                        </div>
                        <div class="chat">
                            <div class="all">
                                <ul>
                                    <div class="chat-left">
                                        <a href="profile.jsp">
                                            <h4>○○さん：</h4>
                                        </a>
                                        <div class="balloon1-left">
                                            <p>こんにちは。これは例です。aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
                                        </div>
                                    </div>
                                    <div class="chat-right">
                                        <h4>××さん：</h4>
                                        <div class="balloon1-right">
                                            <p>こんにちは。これは例です。</p>
                                        </div>
                                    </div>
                                    <div class="chat-left">
                                        <a href="profile.jsp">
                                            <h4>○○さん：</h4>
                                        </a>
                                        <div class="balloon1-left">
                                            <p>こんにちは。これは例です。aaaaaa</p>
                                        </div>
                                    </div>
                                    <div class="chat-right">
                                        <h4>××さん：</h4>
                                        <div class="balloon1-right">
                                            <p>こんにちは。これは例です。</p>
                                        </div>
                                    </div>
                                    <div class="chat-left">
                                        <a href="profile.jsp">
                                            <h4>○○さん：</h4>
                                        </a>
                                        <div class="balloon1-left">
                                            <p>こんにちは。これは例です。</p>
                                        </div>
                                    </div>
                                    <div class="chat-right">
                                        <h4>××さん：</h4>
                                        <div class="balloon1-right">
                                            <p>こんにちは。これは例です。</p>
                                        </div>
                                    </div>
                                </ul>
                            </div>
                            <div class="completion">
                                <form action="" class="send" method="post">
                                    <input type="text" name="intro" size="40" placeholder="テキスト" class="text-box">
                                    <input href="#" class="btn-square-so-pop" value="送信"></input>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
