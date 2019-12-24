package com.coral.api.stripe;

import com.coral.api.utils.DateUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthPayment {

    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";

        //获取Stripe客户ID
        String customerID = "cus_GGEXdYATJDr3Jd";

        //发起支付
        Map<String, Object> payParams = new HashMap<>();
        payParams.put("amount", 4000);
        payParams.put("currency", "sgd");
        payParams.put("description", "PreAuth by Snack " + DateUtils.date2String(new Date(),DateUtils.YYYY_MM_DD_HH_MM_SS));
        payParams.put("customer", customerID);
        payParams.put("capture", false);
        Charge charge = Charge.create(payParams);
        String authChargeId = charge.getId();
        System.out.println("ChargeId is : " +  authChargeId);
        if ("succeeded".equals(charge.getStatus())) {
            System.out.println("PreAuth Charge Success");
        } else {
            System.out.println("PreAuth Charge Failure");
        }
    }

    /*public Charge createAuth(String request) throws StripeException {
        Customer customer = createCustomer(request,request);
        Map<String, Object> payParams = new HashMap<>();
        payParams.put("AMOUNT", 8000);
        payParams.put("CURRENCY", "usd");
        payParams.put("DESCRIPTION", "Charge from customer without token");
        payParams.put("CUSTOMER", "cus_FrUzCq1CA9C2G2");
        payParams.put("CAPTURE", false);
        Charge charge = Charge.create(payParams);
        return charge;
    }

    public Customer createCustomer(String name, String tokenId) throws StripeException {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("name", name);
        customerParams.put("description", "Customer for ZA");
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
    }*/
}
