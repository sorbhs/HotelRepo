package com.mindtree.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mindtree.entity.BookingDetails;
import com.mindtree.entity.Hotel;
import com.mindtree.entity.Users;
import java.util.List;

/**
 * @author M1035913
 *
 */
public class daoImpl implements Dao {

	/* (non-Javadoc)
	 * @see com.mindtree.dao.Dao#SearchString(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Hotel> SearchString(String searchString) {
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		session.beginTransaction();
		String hql = "from Hotel where hotel_name like '%" + searchString + "%'";
		list = (ArrayList<Hotel>) session.createQuery(hql).list();
		for (Hotel listt : list) {
			System.out.println(listt);
		}
	
		
		return list;
	}

	
	
	/* (non-Javadoc)
	 * @see com.mindtree.dao.Dao#hotelInfo(int)
	 */
	public Hotel hotelInfo(int hotelid) {
		Hotel hotel = new Hotel();
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		session.beginTransaction();
		String hql = "from Hotel where hotel_id=" + hotelid + "";
		hotel = (Hotel) session.createQuery(hql).list().get(0);
		return hotel;
	}

	/* (non-Javadoc)
	 * @see com.mindtree.dao.Dao#fetchUser(java.lang.String, java.lang.String)
	 */
	public Users fetchUser(String email, String pass) throws Exception {
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		session.beginTransaction();
		Users user = new Users();
		String hql = "from Users where email='" + email + "' and password='" + pass + "'";
		user = (Users) session.createQuery(hql).list().get(0);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.mindtree.dao.Dao#insertData(com.mindtree.entity.BookingDetails)
	 */
	public void insertData(BookingDetails bo) {
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.save(bo);
		tx.commit();
	}

	/* (non-Javadoc)
	 * @see com.mindtree.dao.Dao#fetchBooking(int)
	 */
	@SuppressWarnings("unchecked")
	public List<BookingDetails> fetchBooking(int id) {
		List<BookingDetails> bookingdetails = new ArrayList<BookingDetails>();
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		session.beginTransaction();
		String hql = "from BookingDetails where userId=" + id + "";
		System.out.println("After completion");
		bookingdetails = session.createQuery(hql).list();
		System.out.println(bookingdetails.get(0).getUser().getEmail());
		System.out.println(bookingdetails.get(0).getHotel().getHotelName());
		System.out.println(bookingdetails.get(0).getBookingid());
		return bookingdetails;
	}
}
