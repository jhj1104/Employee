package webapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Service;

import webapp.exception.JDOMRuntimeException;
import webapp.exception.URLRuntimeException;
import webapp.model.weather.Current;
import webapp.model.weather.Forecast;
import webapp.model.weather.Weather;

@Service
public class WeatherService {
	
	static Log log = LogFactory.getLog(WeatherService.class);
	
	String url = "http://weather.service.msn.com/data.aspx?culture=ko-KR&weasearchstr=";
	SAXBuilder jdom = new SAXBuilder();
	
	Current getCurrent(Element current) {
		Current c = new Current();
		c.setTemperature((Integer.parseInt(current.getAttributeValue("temperature"))));
		c.setSkycode(current.getAttributeValue("skycode"));
		c.setSkytext(current.getAttributeValue("skytext"));
		c.setObservationtime(current.getAttributeValue("observationtime"));
		c.setObservationpoint(current.getAttributeValue("Observationpoint"));
		c.setFeelslike(current.getAttributeValue("feelslike"));
		c.setHumidity(current.getAttributeValue("humidity"));
		c.setWinddisplay(current.getAttributeValue("winddisplay"));
		c.setDay(current.getAttributeValue("day"));
		c.setShortday(current.getAttributeValue("shortday"));
		c.setWindspeed(current.getAttributeValue("windspeed"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			c.setDate(df.parse(current.getAttributeValue("date")));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		
		return c;
	}
	
	List<Forecast> getForecasts(Element weather) {
		List<Forecast> list = new ArrayList<Forecast>();		
		List<Element> ws = weather.getChildren("forecast");
		
		for (Element e : ws) {
			Forecast f = new Forecast();
			
			Integer low = Integer.parseInt(e.getAttributeValue("low"));
			Integer high = Integer.parseInt(e.getAttributeValue("high"));
			String skycodeday = e.getAttributeValue("skycodeday");
			String skyextday = e.getAttributeValue("skyextday");
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = df.parse(e.getAttributeValue("date"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String day = e.getAttributeValue("day");
			String shortday = e.getAttributeValue("shortday");
			Integer precip = Integer.parseInt(e.getAttributeValue("precip"));
			
			f.setLow(low);
			f.setHigh(high);
			f.setSkycodeday(skycodeday);
			f.setSkyextday(skyextday);
			f.setDate(date);
			f.setDay(day);
			f.setShortday(shortday);
			f.setShortday(shortday);
			f.setPrecip(precip);
			
			list.add(f);
			
			
		
		}
				
		
		return list;
	}
	
	public Weather getWeather(String city) { 
		log.info("############################");
		log.info("getWeather()..city = " + city);
		log.info("#############################");
		
		String urlcity = url + city ;
		Weather w=null;
		
		try {
			URL u = new URL(urlcity);
			InputStream in = u.openStream();
			Document doc = jdom.build(in);
			Element weatherdata = doc.getRootElement();
			Element weather = weatherdata.getChild("weather");
			Element current = weather.getChild("current");
			List<Forecast> forecasts = getForecasts(weather);
			
			w = new Weather();
			Current c = getCurrent(current);		
			
			w.setCurrent(c);
			w.setForecasts(forecasts);
			
			log.info("skytext = " + c.getSkytext());
			log.info("date = " + c.getDate());
			
			XMLOutputter output = new XMLOutputter();
			output.setFormat(Format.getPrettyFormat());
			output.output(doc, System.out);
			
		} catch (IOException e) {			
			throw new URLRuntimeException(urlcity, e);
		} catch (JDOMException e) {
			throw new JDOMRuntimeException("build error", e);
		}
		
		
		return w;
	}
}
