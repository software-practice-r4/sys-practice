<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./../components/Header.jsp">
	<jsp:param name="title" value="DM" />
	<jsp:param name="style" value="dm" />
</jsp:include>

        <jsp:include page="./../components/header-after.jsp" />
        <div id="main">
            <jsp:include page="./../components/left-menu.jsp" />
            <div id="C">
                <div class="inner">
                    <div class="post">
                        <div class="centering-ttl-box">
                            <h2 class="centering-ttl">
                                DM
                            </h2>
                        </div>
                        <div class="talk">
                            <ul>
                                <a href="dm-detail.jsp">
                                    <div class="name-chat">
                                        <h4>
                                            ○○さん　　　　　とのやりとり
                                        </h4>
                                        <h5>
                                            テキストテキスト
                                        </h5>
                                    </div>
                                </a>
                                <a href="dm-detail.jsp">
                                    <div class="name-chat">
                                        <h4>
                                            ○○さん　　　　　とのやりとり
                                        </h4>
                                        <h5>
                                            テキストテキスト
                                        </h5>
                                    </div>
                                </a>
                                <a href="dm-detail.jsp">
                                    <div class="name-chat">
                                        <h4>
                                            ○○さん　　　　　とのやりとり
                                        </h4>
                                        <h5>
                                            テキストテキスト
                                        </h5>
                                    </div>
                                </a>
                                <a href="dm-detail.jsp">
                                    <div class="name-chat">
                                        <h4>
                                            ○○さん　　　　　とのやりとり
                                        </h4>
                                        <h5>
                                            テキストテキスト
                                        </h5>
                                    </div>
                                </a>
                                <a href="dm-detail.jsp">
                                    <div class="name-chat">
                                        <h4>
                                            ○○さん　　　　　とのやりとり
                                        </h4>
                                        <h5>
                                            テキストテキスト
                                        </h5>
                                    </div>
                                </a>
                            </ul>
                        </div>
                        <div class="next">
                            <a href="#">1..100</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./../components/Footer.jsp" />
    </body>
</html>
