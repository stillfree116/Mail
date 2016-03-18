package ru.californication.models.implimentations;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ru.californication.hibernate.HibernateUtil;
import ru.californication.hibernate.entity.MessagesEntity;
import ru.californication.hibernate.entity.UserEntity;
import ru.californication.models.DAO;

public class DAOimplHibernate implements DAO {

	// Метод для получения пароля
	public String getPassword(String userName) {
		Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("userName", userName));
		List<UserEntity> lst = criteria.list();
		for (UserEntity ue : lst) {
			if (ue.getUserName().equals(userName)) {
				return ue.getPassword();
			}
		}
		return null;
	}
	
	// Метод для создания пользователя
	public void createUser(Map<String, String> info) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		UserEntity user = new UserEntity();

		user.setFirstName(info.get("firstName"));
		user.setSecondName(info.get("secondName"));
		user.setUserName(info.get("userName"));
		user.setPassword(info.get("password"));
		user.setSecret(info.get("secret"));
		user.setBirthDate(info.get("birthDate"));
		user.setGender(info.get("genger"));

		session.save(user);
		session.getTransaction().commit();

	}
	
	// Восстановления пароля
	public boolean resetPassword(String userName, String secret, String password) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("UPDATE UserEntity SET password = :password "
										+ "WHERE userName = :userName AND secret = :secret");
		query.setString("password", password);
		query.setString("userName", userName);
		query.setString("secret", secret);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// Метод для отправки сообщений
	public boolean setMessage(String from, String to, String text) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		MessagesEntity msg = new MessagesEntity();

		msg.setSender(from);
		msg.setReceiver(to);
		msg.setText(text);
		msg.setSendDate();

		session.save(msg);
		session.getTransaction().commit();
		
		return true;
	}

	// Получение списка сообщений пользователя
	public List<?> getUserMessages(String userName) {
		Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(MessagesEntity.class);
		criteria.add(Restrictions.eq("receiver", userName));
		List<MessagesEntity> messageList = criteria.list();
		return messageList;
	}
	
	// Получения списка польователей
	public List<?> getUsersList() {
		Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserEntity.class);
		List<UserEntity> usersList = criteria.list();
		return usersList;
	}

	// Получение информации о пользователе
	public List<?> getUserInfo(String userName) {
		Criteria criteria = HibernateUtil.getSessionFactory().openSession().createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("userName", userName));
		List<UserEntity> userInfo = criteria.list();
		return userInfo;
	}

}
