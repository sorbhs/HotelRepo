package com.mindtree.service;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.entity.BookingDetails;
import com.mindtree.entity.Hotel;
import com.mindtree.entity.Users;

/**
 * @author M1035913
 *
 */
public interface ServiceI {

	/**
	 * @param searchString
	 * @return
	 */
	public ArrayList<Hotel> SearchString(String searchString);

	/**
	 * @param hotelid
	 * @return
	 */
	public Hotel hotelinfo(int hotelid);

	/**
	 * @param email
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public Users fetchUserService(String email, String pass) throws Exception;

	/**
	 * @param bo
	 */
	public void insertBookingDetails(BookingDetails bo);

	/**
	 * @param userid
	 * @return
	 */
	public List<BookingDetails> fetchBookingService(int userid);
}
