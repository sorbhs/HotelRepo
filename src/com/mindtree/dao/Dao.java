package com.mindtree.dao;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.entity.BookingDetails;
import com.mindtree.entity.Hotel;
import com.mindtree.entity.Users;

/**
 * @author M1035913
 *
 */
public interface Dao {

	/**
	 * @param searchString
	 * @return
	 */
	public ArrayList<Hotel> SearchString(String searchString);

	/**
	 * @param hotelid
	 * @return
	 */
	public Hotel hotelInfo(int hotelid);

	/**
	 * @param email
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public Users fetchUser(String email, String pass) throws Exception;

	/**
	 * @param bo
	 */
	public void insertData(BookingDetails bo);

	/**
	 * @param id
	 * @return
	 */
	public List<BookingDetails> fetchBooking(int id);
}
