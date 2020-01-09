package com.niit.Operations;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.niit.Model.*;
import com.niit.dbconfig.DbConfig;

public class UserOperations {
	Transaction tx;
	public void add(User user) {
		try {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setAuthority("admin");
			user.setStatus(true);
			Session session = DbConfig.getSession();
			 tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}
		
		catch(Exception e) {
			System.out.println(e);
			tx.rollback();
		}
	}
	
	public void delete(int userid) {
		try {
			Session session = DbConfig.getSession();
			User user = new User();
			user.setUserid(userid);
			tx=session.beginTransaction();
			session.delete(user);
			tx.commit();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void update(User user)
	{
		try
		{
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setAuthority("admin");
			user.setStatus(true);
			Session session=DbConfig.getSession();
			tx=session.beginTransaction();
			session.update(user);
			tx.commit();
		}
		catch (Exception e) 
		{
			System.out.println(e);
			tx.rollback();
		}
	}
	
	public List<User> display()
	{
		try
		{
			Session session=DbConfig.getSession();
			return session.createQuery("from com.niit.Model.User").list();
		}
		catch (Exception e) 
		{
			System.out.println(e);
			return null;
		}
	}
	
	public User displaybyid(int userid) {
		try {
			Session session=DbConfig.getSession();
			Query query= session.createQuery("from com.niit.Model.User where userid= :id");
			query.setParameter("id",userid);
			return (User)query.list().get(0);
		}
		
		catch (Exception e){
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	public User displaybyName(String username)
	{
		try
		{
			Session session=DbConfig.getSession();
			Query query=session.createQuery("from com.niit.Model.User where username= :username");
			query.setParameter("username", username);
			return (User)query.list().get(0);
		}
		catch (Exception e) 
		{
			System.out.println(e);
			return null;
		}
	}
	
}
