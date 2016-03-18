<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Восстановление пароля</title>
        <link rel="stylesheet" href="style.css" type="text/css" media="screen"/>
    </head>
    <body>
        <%
            HttpSession se = request.getSession();
            String status = (String) se.getAttribute("pageStatus");
            se.removeAttribute("pageStatus");
            if (status != null) {
                out.println(status);
                se.removeAttribute("pageStatus");
            }
        %>
        <form action="forgot" method="post" class="login">           
            <div class="forgot">
            <p> 
                <label>Логин:</label>
                <input type="text" name="userName" required>
            </p> 

            <p>  
                <label >Контрольное слово:</label>
                <input type="text" name="secret" required> 
            </p> 
            
            <p> 
                <label>Новый пароль:</label>
                <input type="password" name="password" required>             
            </p>
            </div>

            <p> 
                 <button type="submit" class="login-button">Изменить</button>  
            </p> 
        </form>
    </body>
</html>