package ru.californication.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import ru.californication.MD5;
import ru.californication.models.DAO;
import ru.californication.models.implimentations.DAOimplHibernate;

@WebServlet("/login")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Создаем объект класса DAOimplHibernate который реализует интерфейс DAO
	DAO dao = new DAOimplHibernate();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Создаем объект session и получаем введенное имя пользователя и пароль
		HttpSession session = request.getSession(true);
		String userName = request.getParameter("userName");
		String password = MD5.getMD5(request.getParameter("password"));
		
		if (login(userName, password)) {
			// Передаем в атрибутах обЪекты класса DAOimplHibernate
			session.setAttribute("userInfo", dao.getUserInfo(userName));
			session.setAttribute("messages", dao.getUserMessages(userName));
			session.setAttribute("usersList", dao.getUsersList());
			response.sendRedirect("/Mail/main.jsp");
		} else {
			// Отображаем пользователью информацию о некорректных данных
			session.setAttribute("pageStatus", "Неверное имя пользователя или пароль!");
			response.sendRedirect("/Mail");
		}
	}

	// Метод для проверки пользователя и пароля
	private boolean login(String name, String pass) {
		String passFromDB = dao.getPassword(name);

		if(pass.equals(passFromDB)) {
			return true;
		} else {
			return false;
		}
	}
}
