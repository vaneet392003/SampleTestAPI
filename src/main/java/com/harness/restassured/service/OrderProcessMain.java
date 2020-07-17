package main.java.com.harness.restassured.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import main.java.com.harness.restassured.model.OrderRequestObj;
import main.java.com.harness.restassured.model.ItemRequestObj;

public class OrderProcessMain {
	
	final static Logger logger = Logger.getLogger(OrderProcessMain.class);
	boolean debugEnabled = logger.isDebugEnabled();
	boolean infoEnabled = logger.isInfoEnabled();
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	
	public OrderRequestObj getOrderDetails(String customerNo) {
		OrderRequestObj orderRequestObj = new OrderRequestObj();
		ItemRequestObj itemRequestObj = new ItemRequestObj();
		Date date = new Date();
		try {
			if (infoEnabled) {
				logger.info("Starting : Get Order Details : Order Process Main");
			}
			itemRequestObj.setItem1("Book1");
			itemRequestObj.setItem2("Book2");
			orderRequestObj.setCustomerId("12345");
			orderRequestObj.setCustomerName("Test");
			orderRequestObj.setOrderId("ABC1234");
			orderRequestObj.setItems(itemRequestObj);
			orderRequestObj.setOrderDate(dateFormatter.format(date.getTime()));
			orderRequestObj.setOrderStatus("Delivered");
			if (infoEnabled) {
				logger.info("Exiting : Get Order Details : Order Process Main");
			}
		} catch (ArrayIndexOutOfBoundsException arrayexc) {
			if (infoEnabled) {
				logger.info("User Info : ManageLoginStats");
			}
		} catch (Exception exc) {
			if (infoEnabled) {
				logger.info("Failed to manage login stats : ", exc);

			}
		}
		return orderRequestObj;
	}

	public Map<String, String> setEnvVariable() {
		// pass the path to the file as a parameter
		File file = new File("/usr/local/tomcat/.env");
		//File file = new File("C:\\CIAM\\Rewards\\.env");
		Map<String, String> varMap = new HashMap<String, String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			String st1 = null;
			String[] keyValue = { new Random().toString(), new Random().toString() };
			while ((st = br.readLine()) != null) {
				if (st.endsWith("=")) {
					st1 = st + new Random().nextInt(2);
					keyValue = st1.split("=");
					varMap.put(keyValue[0], keyValue[1]);
				} else if (!st.endsWith("=")) {
					keyValue = st.split("=");
					varMap.put(keyValue[0], keyValue[1]);
				}
			}
			br.close();

		} catch (FileNotFoundException fileNotFoundException) {
			if (infoEnabled) {
				logger.error(".env File Not Found", fileNotFoundException);
			}
		} catch (IOException ioException) {
			if (infoEnabled) {
				logger.error("Failed to read file", ioException);
			}
		} catch (Exception exc) {
			if (infoEnabled) {
				logger.info("Exception : ", exc);
			}
		}
		return varMap;

	}

}
