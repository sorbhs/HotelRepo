import static org.junit.Assert.*;

import org.junit.Test;

import com.mindtree.entity.Hotel;
import com.mindtree.service.HotelService;

/**
 * @author M1035913
 *
 */
public class TestHotel {

	/**
	 * 
	 */
	@Test
	public void test() {
		HotelService ser=new HotelService();

		Hotel h=new Hotel();
		h.setHotelState("Spain");
		Hotel act=ser.hotelinfo(3);
		
		Hotel exp=h;
		assertEquals(exp.getHotelState(),act.getHotelState());	
	}
}
