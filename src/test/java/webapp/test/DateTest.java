package webapp.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import webapp.escape.Screen;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateTest {

	static Log log = LogFactory.getLog(DateTest.class);

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		Date current = new Date();
		log.info(current.toLocaleString());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test2() {
		Calendar current = Calendar.getInstance();
		log.info(current.getTime().toLocaleString());
	}

	@Test
	public void test3() {
		Date current = new Date();

		//current.setDate(current.getDate() + 10);
		log.info("year = " + (current.getYear() + 1900));
		log.info("month = " + (current.getMonth() + 1));
		log.info("date = " + current.getDate());
		log.info("week = " + current.getDay());

	}

	@Test
	public void test4() {
		Date date = new Date(2015 - 1900, 3 - 1, 1);

		log.info("start week = " + date.getDay());

		int month = date.getMonth();
		while (true) {
			date.setDate(date.getDate() + 1);
			if (month != date.getMonth())
				break;
		}
		date.setDate(date.getDate() - 1);
		log.info("end week = " + date.getDay());
		log.info("end date = " + date.getDate());
	}

	@Test
	public void test5() {
		Date date = new Date(2015 - 1900, 1 - 1, 1);

		int month = date.getMonth();
		int day = 0;

		while (true) {

			System.out.print(date.getDate() + " ");
			if (date.getDay() == 6)
				System.out.println();
			if (month != date.getMonth())
				break;
			date.setDate(date.getDate() + 1);

		}

	}

}
