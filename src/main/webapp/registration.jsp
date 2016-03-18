<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Страница регистрации</title>
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
        <form action="registrate" method="post" class="login">
            <div class="forgot">
                <p> 
                    <label>Как вас зовут:</label>
                    <input type="text" name="firstName" placeholder="Имя" required><br><br>
                    <input type="text" name="secondName"  placeholder="Фамилия" required>
                </p>

                <p>
                    <label>Придумайте логин:</label>
                    <input type="text" name="userName" required>
                </p>

                <p>
                    <label>Придумайте пароль:</label>
                    <input type="password" name="password" required>
                </p>

                <p>
                    <label>Придумайте секретное слово</label>
                    <input type="text" name="secret" required>
                </p>

                <p>
                    <label>Введите дату рождения</label>
                    <input type="date" name="birthday" required>
                </p>


                <p>
                    <label>Пол:</label>
                    <select name="Пол:" required>
                        <option name="gender" value="man">Мужчина</option>
                        <option name="gender" value="woman">Женщина</option>
                    </select>
                </p>

                <p> 
                    <button type="submit" class="login-button">Регистрация</button>  
                </p>
        </form>
    </body>
</html>