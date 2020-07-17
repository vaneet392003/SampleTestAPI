package test.java.com.harness.restassured.controller;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;


import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import main.java.com.harness.restassured.controller.APIController;
import main.java.com.harness.restassured.model.OrderRequestObj;
import main.java.com.harness.restassured.service.OrderProcessMain;

@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppControllerIntegrationTest {
	
	final static Logger logger = Logger.getLogger(APIController.class);
	boolean debugEnabled = logger.isDebugEnabled();
	boolean infoEnabled = logger.isInfoEnabled();
	
	
    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://35.244.107.6/restapi/";
    }

    @MockBean
    OrderProcessMain appService;

    @Test
    public void givenOrderId_whenMakingGetRequestToOrderEndpoint_thenReturnOrder() {
    	
    	logger.info("----Order Details---" + appService.getOrderDetails("12345"));

        OrderRequestObj orderRequestObj = new OrderRequestObj();
        when(appService.getOrderDetails("12345")).thenReturn(orderRequestObj);
        get(uri + "/api/order?customerNo" + orderRequestObj.getCustomerId()).then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("orderid", equalTo(orderRequestObj.getOrderId()))
            .body("orderdate", equalTo(orderRequestObj.getOrderDate()))
            .body("orderstatus", equalTo(orderRequestObj.getOrderStatus()));


        OrderRequestObj result = get(uri + "/api/order" + orderRequestObj.getCustomerId()).then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(OrderRequestObj.class);
        assertThat(result).isEqualTo(orderRequestObj);

        String responseString = get(uri + "/api/order" + orderRequestObj.getCustomerId()).then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .asString();
        assertThat(responseString).isNotEmpty();
    }
}
