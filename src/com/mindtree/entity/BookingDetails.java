package com.mindtree.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author M1035913
 *
 */
@Entity
@Table(name = "BookingDetails")
public class BookingDetails {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int bookingid;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CheckIn")
	Date checkin;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CheckOut")
	Date checkout;
	/*
	 * int userid; int hotelid;
	 */

	/**
	 * 
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	Users user;

	/**
	 * 
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotelId")
	Hotel hotel;

	/**
	 * 
	 */
	public BookingDetails() {
		super();
	}

	/**
	 * @param checkin
	 * @param checkout
	 * @param user
	 * @param hotel
	 */
	public BookingDetails(Date checkin, Date checkout, Users user, Hotel hotel) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
		this.user = user;
		this.hotel = hotel;
	}

	/**
	 * @return
	 */
	public int getBookingid() {
		return bookingid;
	}

	/**
	 * @param bookingid
	 */
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	/**
	 * @return
	 */
	public Date getCheckin() {
		return checkin;
	}

	/**
	 * @param checkin
	 */
	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	/**
	 * @return
	 */
	public Date getCheckout() {
		return checkout;
	}

	/**
	 * @param checkout
	 */
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	/**
	 * @return
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * @return
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * @param hotel
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}