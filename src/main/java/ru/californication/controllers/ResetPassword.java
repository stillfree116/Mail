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

@WebServlet("/forgot")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Создаем объект класса DAOimplHibernate который реализует интерфейс DAO
	DAO dao = new DAOimplHibernate();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Создаем объект класса HttpSession
		HttpSession session = request.getSession(true);
		
		// Из post параметров берем имя пользователя, новый пароль, и секретное слово
		String userName = request.getParameter("userName");
		String password = MD5.getMD5(request.getParameter("password"));
		String secret = request.getParameter("secret");
		
		// Вызываем метод сброса пароля, в случае успеха переходим на главную страницу
		if (dao.resetPassword(userName, secret, password)) {
			session.setAttribute("pageStatus", "Ура! Пароль успешно изменен, запиши на бумажку и попробуй залогиниться.");
			response.sendRedirect("/Mail");
		} else {
			session.setAttribute("pageStatus", "Неверное имя пользователя или секетное слово!");
			response.sendRedirect("/Mail/forgot.jsp");
		}
	}
}
