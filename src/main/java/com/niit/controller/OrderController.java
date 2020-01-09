package com.niit.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@RequestMapping(value = "/buy")
	public void m1(HttpServletResponse resp) {
		try {
			ApiContext.ClearInstance();
			ApiContext context = ApiContext.create("test_DWGdqTY8zqzDIUs0LAfNV5TqyW46zRkQINN", "test_bmKE0vakiW7bozHirng7WaYBTu7ovxFfRyys4dCYWelnLtcVdoGsRLjaHU5LsDU0wJi57MlTUVQqOzpQvANJ8gsn3Q2L7cdzYMCGKersflRQ6WOUw3ebSwgnKNJ", ApiContext.Mode.TEST);
			Instamojo api = new InstamojoImpl(context);
			
			PaymentOrder order = new PaymentOrder();
			order.setName("Ravi123");
			order.setEmail("rmule1996@gmail.com");
			order.setPhone("7208132913");
            order.setCurrency("INR");
            order.setAmount((double)112);
            order.setDescription("This is a test transaction.");
            order.setRedirectUrl("https://www.google.com");
            order.setWebhookUrl("https://www.google.com");
            order.setTransactionId(UUID.randomUUID().toString());

           PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
           resp.sendRedirect(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
		}
		
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
}
