<%@ page import="java.util.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date now = new Date();
%>
<!DOCTYPE html>
<html>
    <head>
        <title>大数据分析建模平台</title>
        <link href="${home}/style/lib/kendo.common.css" rel="stylesheet"/>
        <link href="${home}/style/lib/kendo.css" rel="stylesheet"/>
        <link href="${home}/style/kendo.override.css" rel="stylesheet"/>
        <link href="${home}/style/site.css" rel="stylesheet"/>

        <script src="${home}/script/lib/jquery.js"></script>
        <script src="${home}/script/lib/kendo.js"></script>
        <style>
            html {
                height: 100%;
                padding: 0;
                margin: 0;
            }
            body {
                margin: 0;
                padding: 0;
                height: 100%;
                overflow: hidden;
                font-family: 'Microsoft Yahei', "Helvetica Neue", Helvetica, Arial, sans-serif
            }

            #header {
                overflow: hidden;
                background: url('${home}/image/header-bg.jpg') no-repeat;
                background-size: 100% 100%;
            }

            /**/
            #menu {
                border-radius: 0;
                margin: 0;
                border-left: 0;
                border-right: 0;
                background: #5fa4e8 repeat-x;
                background-image: linear-gradient(to bottom,#fff 0,
                    #5fa4e8 6%, #5fa4e8 95%, white 100%);

                border: 0;
            }
            #menu li {
                box-shadow: none;
                color: white;
                border: 0;
                border-radius: 0;
            }
            #menu .k-link {
                padding: .8em 1em;
                font-size: 1.14em;
            }

            #menu li.selected {
                background-color: #9fca20;
            }

            #menu li.selected > .k-link {
                font-weight: bold;
            }



            #menu .k-state-hover {
                background-color: #67b4ff;
            }

            #menu .k-state-hover .k-link {
                color: white;
            }

            #menu .k-state-border-down > .k-link {
                color: gray;
            }

            #menu .k-popup .k-link {
                color: black;
            }

            #menu .k-popup .selected .k-link {
                color: white;
            }

            #menu .k-popup .k-state-hover .k-link {
                color: white;
            }

            h1 {
                font-family: 'Microsoft Yahei';
                font-weight: normal;
                font-size: 1.535em;
                margin: 1.1em .5em .9em .5em;
            }

            #frameContainer {

            }
            iframe {
                border: 0;
                width: 100%;
                height: 100%;
            }

            #account-menu {
                background-color: transparent;
                border: 0;
                position: absolute;
                top: 38px;
                right:16px;
            }

            #account-menu > li {
                border: 0;
            }

            #account-menu > li > span {
                padding: .6em 1.1em;
                padding-left: 30px;
                background: url('${home}/image/ico/male.png') no-repeat 10px 9px;
            }

        </style>
        <script>

            sessionStorage.urls = '';

            $(function() {
                $("#menu").kendoMenu({
                    select: onMenuClick
                }).show();
            });
        </script>
        <script>

            function onMenuClick(e) {
                var $li = $(e.item);
                if($li.find('.k-i-arrow-s').length) {
                    return;
                }
                var $ul = $li.parent();
                if($ul.attr('id') != 'menu') {

                }
                var src = $li.data('href');
                if(!src) {
                    return;
                }

                $('#menu').find('li.selected').removeClass('selected');
                $li.addClass('selected');
                var $frame = $li.data('$frame');

                var $container = $('#frameContainer');
                $container.find('iframe').hide();
                if(!$frame) {
                    $frame = $('<iframe src="' + src + '"></iframe>');
                    $frame.appendTo($container);
                    $li.data('$frame', $frame);
                    updateFrameContainerHeight();
                }
                $frame.show();

                var $lblMenuMore = $('#lblMenuMore');
                if(!$li.parent().is('#menu')) {
                    $lblMenuMore.text($li.children('.k-link').text());
                    $lblMenuMore.closest('li').addClass('selected');
                }
                else {
                    $lblMenuMore.text('更多');
                    $lblMenuMore.closest('li').removeClass('selected');
                }
            }

            function updateFrameContainerHeight() {
                var $container = $('#frameContainer');
                $container.outerHeight($(document.body).height() - $container.offset().top);
            }

            $(function() {
                $("#menu").kendoMenu({
                    select: onMenuClick
                }).show();

                $(window).resize(function() {
                    updateFrameContainerHeight();
                });

                $("#account-menu").kendoMenu({
                    select: onMenuClick
                }).show();
            });
        </script>
    </head>
    <body>
        <div id="header" style="">
            <h1>大数据分析建模平台</h1>
        </div>
        <ul id="account-menu" style="display: none;">
            <li>
                <span id="lblUserName">${SESSION_DATA.userName}</span>
                <ul>
                    <li><a href="${home}/logout.do">注销</a></li>
                </ul>
            </li>
        </ul>
        <div id="menu-container">
            <ul id="menu" style="display: none;">
                <li id="tabHome" class="selected" data-href="${home}/index.html">
                    首页
                </li>
                <li data-href="${home}/manage/index.html">
                    数据模块
                </li>
                <li data-href="">
                    数据清洗
                </li>
                <li data-href="${home}/algorithm/index.html">
                    基础算法库
                </li>
                <li data-href="${home}/appmodel/index.html">
                    应用模型
                </li>

                <li data-href="${home}/monitor/index.html">
                    管理模块
                </li>
            </ul>
        </div>
        <div id="frameContainer">
            <iframe src="${home}/home.jsp"></iframe>
        </div>

        <script>
            updateFrameContainerHeight();
            $('#tabHome').data('$frame', $('iframe'));
        </script>
    </body>
</html>
