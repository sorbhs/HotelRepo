package com.mindtree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author M1035913
 *
 */
@Entity
@Table(name = "User")
public class Users {

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	@Column(name = "UserId")
	int userid;

	/**
	 * 
	 */
	@Column(name = "Email")
	String email;

	/**
	 * 
	 */
	@Column(name = "Password")
	String password;

	/**
	 * @return
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}