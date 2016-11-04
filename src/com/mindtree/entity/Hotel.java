package com.mindtree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author M1035913
 *
 */
@Entity
@Table(name = "hotel")
public class Hotel {
	/**
	 * 
	 */
	@Id
	@Column(name = "hotel_id")
	int hotelId;
	/**
	 * 
	 */
	@Column(name = "hotel_name")
	String hotelName;
	/**
	 * 
	 */
	@Column(name = "hotel_address")
	String hotelAddress;
	/**
	 * 
	 */
	@Column(name = "hotel_city")
	String hotelCity;
	/**
	 * 
	 */
	@Column(name = "hotel_state")
	String hotelState;
	/**
	 * 
	 */
	@Column(name = "hotel_zip")
	String hotelZip;
	/**
	 * 
	 */
	@Column(name = "hotel_price")
	int hotelPrice;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelCity() {
		return hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelState() {
		return hotelState;
	}

	public void setHotelState(String hotelState) {
		this.hotelState = hotelState;
	}

	public String getHotelZip() {
		return hotelZip;
	}

	public void setHotelZip(String hotelZip) {
		this.hotelZip = hotelZip;
	}

	public int getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(int hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

}