package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import java.util.HashMap;
import java.util.Map;

public class CustomerCreation {

    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";

        String tokenId = getToken();
        System.out.println("tokenId is " + tokenId);

        Customer customer = createCustomer("Coral.Ma", tokenId);
        System.out.println("CustomerID is " + customer.getId());
    }

    public static Customer createCustomer(String name, String tokenId) throws StripeException {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("name", name);
        customerParams.put("description", "Customer from ZIP");
        customerParams.put("source", tokenId);
        Customer cust = Customer.create(customerParams);
        return cust;
    }

    public static String getToken() throws StripeException {
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 1);
        cardParams.put("exp_year", 2020);
        cardParams.put("cvc", "314");
        tokenParams.put("card", cardParams);

        Token token = Token.create(tokenParams);
        String tokenId = token.getId();
        return tokenId;
    }
}
