<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="./../css/start/destroy.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="./../css/start/common.css" />
        <script src="https://kit.fontawesome.com/313a5a93b1.js" crossorigin="anonymous"></script>
    </head>
    <header>
        <div class="inner-head">
            <div class="head-all">
                <div class="head-title">
                    <a href="../jsp/home.jsp" class="home" >
                        素材提供サイト
                    </a>
                </div>
                <div class="head-left">
                    <form method="GET" action="list.jsp" class="search">
                        <input id="sbox"  id="s" name="s" type="search" placeholder="キーワードを入力" />
                        <input id="sbtn" type="submit" value="検索" />
                    </form>
                </div>
                <div class="head-right">
                    <a href="../jsp/cart.jsp" class="btn-text-3d">
                         <i class="fa-solid fa-cart-arrow-down"></i>
                    </a>
                    <a href="../jsp/profile.jsp" class="btn-flat-logo" >
                        <i class="fa fa-chevron-right"></i>○○さん
                    </a>
                </div>
            </div>
        </div>
    </header>