package main.java.com.harness.restassured.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import main.java.com.harness.restassured.model.OrderRequestObj;
import main.java.com.harness.restassured.service.OrderProcessMain;

@Path("/api")
public class APIController {

	final static Logger logger = Logger.getLogger(APIController.class);
	boolean debugEnabled = logger.isDebugEnabled();
	boolean infoEnabled = logger.isInfoEnabled();
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	Date date = new Date();

	@GET
	@Path("/order")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getOrder(@QueryParam("customerNo") String customerNo, @HeaderParam("authorization") String authentication)
			throws IOException {

		try {
			if (infoEnabled) {
				logger.info("Register Device API : Adapt Auth Service");
			}
			OrderRequestObj orderRequestObj = new OrderRequestObj();
			String reqBody = customerNo;
			OrderProcessMain orderProcessMain = new OrderProcessMain();
			// If the String is not empty, parses the payload into a map
			if (!reqBody.isEmpty()) {
				orderRequestObj = orderProcessMain.getOrderDetails(customerNo);
			}
			return Response.status(201).entity(orderRequestObj).build();
		} catch (Exception ex) {
			if (infoEnabled) {
				logger.info("Register Device Failed : API Exception", ex);
			}
			return Response.status(500).entity("server exception").build();
		}

	}

	@GET
	@Path("/healthcheck")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMsg() {
		return "Alive----" + new Date();
	}

	public Map<String, String> setEnvVariable() {
		// pass the path to the file as a parameter
		File file = new File("/usr/local/tomcat/.env");
		// File file = new File("C:\\CIAM\\Rewards\\.env");
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

	@POST
	@Path("/setPropertyValue")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setPropertyValue() {
		System.setProperty("API_KEY", "436553872");
		// get the property value and print it out
		return Response.status(201).entity("Variable Set").build();

	}

}