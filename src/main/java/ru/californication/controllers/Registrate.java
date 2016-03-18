package ru.californication.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import ru.californication.MD5;
import ru.californication.models.DAO;
import ru.californication.models.implimentations.DAOimplHibernate;

@WebServlet("/registrate")
public class Registrate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Создаем объект класса DAOimplHibernate который реализует интерфейс DAO
	DAO dao = new DAOimplHibernate();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		
		// Создаем коллекцию из введенных пользователем данных
		HashMap<String, String> info = new HashMap<String, String>();
		
		info.put("firstName", request.getParameter("firstName"));
		info.put("secondName", request.getParameter("secondName"));
		info.put("userName", request.getParameter("userName"));
		info.put("password", MD5.getMD5(request.getParameter("password")));
		info.put("secret", request.getParameter("secret"));
		info.put("birthDate", request.getParameter("birthDate"));
		info.put("genger", request.getParameter("genger"));		
		
		dao.createUser(info);
		
		session.setAttribute("pageStatus", "Поздравляем, вы успешно зарегистрировались!");
		response.sendRedirect("/Mail");
	}
}
