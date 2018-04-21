
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <script src="${home}/script/lib/jquery.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            overflow-y: hidden;
        }

        body {
            background-color: #808080;
            position: relative;
        }

        .form-container {
            background-color: white;
            position: absolute;
            width: 100%;
            top: 44%;
            margin-top: -160px;
            height: 330px;
            min-width: 800px;
            text-align: center;
        }

        .form {
            position: absolute;
            width: 800px;
            height: 100%;
            left: 50%;
            margin-left: -400px;
            background: url('${home}/image/app/login-bg.png') no-repeat;
            overflow: visible;
        }

        .field {
            position: absolute;
        }

        .field * {
            vertical-align: middle;
        }

        .field input {
            padding: .3em;
            margin-left: .4em;
        }

        .field-account {
            left: 456px;
            top: 100px;
        }

        .field-password {
            left: 456px;
            top: 144px;
        }

        .btn-login {
            position: absolute;
            left: 485px;
            top: 229px;
        }

        .btn-reset {
            display: block;
            width: 33px;
            height: 33px;
            position: absolute;
            left: 677px;
            top: 228px;
        }

        .copyright {
            position: absolute;
            right: 1em;
            bottom: 1em;
            font-size: .9em;
            color: silver;
        }

        <%----%>
        form div {
            margin: 1em;
        }

        .errorMsg {
            color: red;
            font-size: .9em;
            position: absolute;
            bottom: 22px;
            left: 432px;
        }

        .invisible {
            visibility: hidden;
        }
    </style>
</head>
<body class="invisible">
<script>
    if (top !== window) {
        top.location = window.location;
    }
    else {
        document.body.className = '';
    }
</script>
<div class="form-container">
    <div class="form">
        <form method="post">
            <input type="hidden" name="action" value="login"/>
            <div class="field field-account">
                <label for="account">帐号: </label>
                <input type="text" name="account" id="account" value="admin"/>
            </div>
            <div class="field field-password">
                <label for="account">密码: </label>
                <input type="password" name="password" id="password" value="123456"/>
            </div>
            <input type="image" src="${home}/image/app/btn-login.png" class="btn-login"/>
            <a href="#" class="btn-reset"></a>
        </form>
        <div class="errorMsg">${errorMsg}</div>
    </div>
</div>
<div class="copyright">
    版权所有 &copy; 2016, 广东省电信规划设计院 大数据工程院
</div>
<script>
    $(function() {
        $('.btn-reset').click(function() {
            $('form')[0].reset();
            return false;
        });
    });
</script>
</body>
</html>
