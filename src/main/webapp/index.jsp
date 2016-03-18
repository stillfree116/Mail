<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Добро пожаловать!</title>
        <link rel="stylesheet" href="style.css" type="text/css" media="screen"/>
        <link rel="shortcut icon" href="/img/arrow.png" type="image/x-icon">
    </head>
    <body>
    	<p class="forgot-password">
        <%
            HttpSession se = request.getSession();
            String status = (String) se.getAttribute("pageStatus");
            se.removeAttribute("pageStatus");
            if (status != null) {
                out.println(status);
                se.removeAttribute("pageStatus");
            }
        %>
        </p>
        <form action="login" method="post" class="login">
            <p> 
                <label>Логин:</label>
                <input type="text" name="userName" required>
            </p> 

            <p> 
                <label>Пароль:</label>
                <input type="password" name="password" required>
            </p>

            <p class="login-submit">
                <button type="submit" class="login-button">Войти</button>
            </p>

            <p class="forgot-password">
                <a href="registration.jsp">Регистрация</a>
            </p>
            
            <p class="forgot-password">
                <a href="forgot.jsp">Забыли пароль?</a>
            </p> 
        </form>
    </body>
</html>