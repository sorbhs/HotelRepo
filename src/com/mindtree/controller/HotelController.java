package com.mindtree.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.Request;
import com.mindtree.entity.BookingDetails;
import com.mindtree.entity.Hotel;
import com.mindtree.entity.Users;
import com.mindtree.service.HotelService;

/**
 * @author M1035913 hi this is first commit
 * this is second commit
 * edited from github
 * edited from github2
 */
@Controller
public class HotelController {

	/**
	 * 
	 */
	Hotel selectedHotel;
	/**
	 * 
	 */
	Users selectedUser;
	// HotelService service=new HotelService();
	/**
	 * 
	 */
	@Autowired
	HotelService service;

	/**
	 * @param service
	 */
	public void setService(HotelService service) {
		this.service = service;
	}

	/**
	 * 
	 */
	String username;

	/**
	 * This will redirect to home page
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) {
		Cookie []cookies=request.getCookies();
		for(Cookie cc:cookies)
		{
			cc.setMaxAge(0);
			cc.setValue(null);
		}
		//cookies[1].setMaxAge(0);
		//cookies[1].setValue(null);
		//response.addCookie(cookies);
		ModelAndView modelandview = new ModelAndView("HotelHome");
		if(getLoginStatus(request, response))
			modelandview.addObject("loginstatus","1");			
		else
			modelandview.addObject("loginstatus","0");
		return modelandview;
		
	}

	/**
	 * Redirect to search page where user can search name of hotel according to
	 * input string
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search")
	public ModelAndView toSearch() {

		ModelAndView modelandview = new ModelAndView("searchHotels");
		return modelandview;
	}

	/**
	 * This will show all the search result according to string entered by user
	 * 
	 * @param searchStr
	 * @return
	 */
	@RequestMapping(value = "/searchResult")
	public ModelAndView toSearchResult(@RequestParam("searchString") String searchStr) {
		ModelAndView modelandview = new ModelAndView("searchResult");
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		// service.forMail();
		hotelList = service.SearchString(searchStr);
		modelandview.addObject("hotelList", hotelList);
		return modelandview;

	}

	/**
	 * If user want to search again.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backsearchResult")
	public ModelAndView backSearchResult() {
		ModelAndView modelandview = new ModelAndView("searchHotels");
		return modelandview;
	}

	/**
	 * 
	 * Fetch information of selected hotel and display
	 * 
	 * @param val
	 * @return
	 */
	@RequestMapping(value = "/hotelInfo")
	public ModelAndView hotelInfo(@RequestParam("hotelId") int val) {
		ModelAndView modelandview = new ModelAndView("hotelDetails");
		Hotel hotel = service.hotelinfo(val);
		modelandview.addObject("hotelInfo", hotel);
		selectedHotel = hotel;
		return modelandview;
	}

	/**
	 * Redirect to login page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String toLogin(HttpServletRequest request,HttpServletResponse response) {
		
		Cookie []cookies=request.getCookies();
		//if(cookies[1].getName().equals(null))
		System.out.println(cookies.length);
		if(cookies.length==1)
			return "loginPage";			
		else
			return "bookingDetails";
		
		//ModelAndView modelandview = new ModelAndView("loginPage");
		//return modelandview;
	}

	@RequestMapping(value = "/mailme")
	public ModelAndView toMail() {

		ModelAndView modelandview = new ModelAndView("emailService");
		return modelandview;
	}

	@RequestMapping(value = "/back")
	public ModelAndView back() {
		ModelAndView modelandview = new ModelAndView("HotelHome");
		return modelandview;
	}

	@RequestMapping(value = "/mail")
	public ModelAndView toMail(@RequestParam("emsg") String emsg, @RequestParam("email") String email) {
		ModelAndView modelandview = new ModelAndView("msgsent");
		System.out.println(emsg + " " + email);
		System.out.println(email);
		System.out.println(emsg);
		int sentOrNot = service.forMail(emsg, email);
		if (sentOrNot == 1)
			modelandview.addObject("result", "mail sent");
		else
			modelandview.addObject("result",
					"Error...mail not sent. Possible Reasons: Mail ID not registered on AWS / Mail id"
							+ " Registered in different Region / Wrong Mail Id");
		// modelandview = new ModelAndView("msgsent");
		return modelandview;
	}

	/**
	 * 
	 * This will go to database and search for email ID and password
	 * combination, if found will move to next page else will display proper
	 * error message
	 * 
	 * @param pass
	 * @param email
	 * @param userN
	 * @return
	 */
	@RequestMapping(value = "/afterLogin")
	public String afterLogin(@RequestParam("password") String pass, @RequestParam("email") String email,
			@RequestParam("username") String userN,HttpServletResponse response) {
		username = userN;
		
		//System.out.println("Here before methhod loggin");
		//String name=request.getParameter("username");  
		Cookie ck=new Cookie("uname",username);//creating cookie object
		//ck.setMaxAge(60);
		System.out.println("value of cookie :"+ck.getValue());
	    
		
		Users user;
		try {
			user = service.fetchUserService(email, pass);
		} catch (Exception e) {
			System.out.println("If login is wrongs");
			//ModelAndView modelandview = new ModelAndView("loginPage");
			//modelandview.addObject("msg", "Sorry.Email id and/or password is incorrect.");
			//return modelandview;
			response.addHeader("msg", "Sorry.Email id and/or password is incorrect.");
			return "loginPage";
		
		}
		System.out.println("Here after loggin");
		System.out.println("if login is successfull");
		response.addCookie(ck);//adding cookie in the response  
	//	ModelAndView modelandview = new ModelAndView("bookingDetails");
		selectedUser = user;
	//	modelandview.addObject("user", user);
	//	return modelandview;
		return "bookingDetails";
	}

	/**
	 * 
	 * Get check in and check-out date as input and insert data into database
	 * and book hotel name of selected user
	 * 
	 * @param checkin
	 * @param checkout
	 * @return
	 */
	@RequestMapping(value = "/place")
	public ModelAndView place(@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout,
			HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelandview = new ModelAndView("reservation");
				
		try {
			BookingDetails bk = new BookingDetails();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(checkin);
			java.sql.Date checkindate = new java.sql.Date(date.getTime());
			bk.setCheckin(checkindate);
			date = formatter.parse(checkout);
			java.sql.Date checkoutdate = new java.sql.Date(date.getTime());
			bk.setCheckout(checkoutdate);

			bk.setHotel(selectedHotel);
			bk.setUser(selectedUser);
			HotelService service = new HotelService();
			service.insertBookingDetails(bk);
			int ses = service.ses(bk.getUser().getEmail());

			modelandview.addObject("username", username);
			modelandview.addObject("bookingDetails", service.fetchBookingService(selectedUser.getUserid()));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return modelandview;
	}

	@RequestMapping(value = "/docc")
	public ModelAndView DocUpload() {
		ModelAndView modelandview = new ModelAndView("uploaded");
		try {
			HotelService service = new HotelService();
			String etag=service.upload();
			modelandview.addObject("etag",etag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelandview;
	}
	
	@RequestMapping(value = "/doc")
	public ModelAndView UploadForm() {
		ModelAndView modelandview = new ModelAndView("uploadForm");
		return modelandview;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {

		Cookie []cookies=request.getCookies();
		//System.out.println(cookies[0].getValue());
		//System.out.println(cookies[1].getValue());
		cookies[1].setMaxAge(0);
		cookies[1].setValue(null);
		response.addCookie(cookies[1]);
		//System.out.println(cookies[0].getValue());
		//System.out.println(cookies[1].getValue());
		ModelAndView modelandview = new ModelAndView("searchHotels");
		return modelandview;
	}
	
	private boolean getLoginStatus(HttpServletRequest request,HttpServletResponse response)
	{
		Cookie []cookies=request.getCookies();
		if(cookies.length==1)
			return false;		
		else
			return true;
		
	}
	
}
