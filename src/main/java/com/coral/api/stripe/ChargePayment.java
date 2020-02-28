package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

public class ChargePayment {

    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";

        //获取Stripe客户ID
        String customerID = "cus_GGEXdYATJDr3Jd";

        //发起支付
        Map<String, Object> payParams = new HashMap<>();
        payParams.put("amount", 5500);
        payParams.put("currency", "sgd");
        payParams.put("description", "Direct Charge by Coral");
        payParams.put("customer", customerID);
        Charge charge = Charge.create(payParams);
        System.out.println(charge.getId());
        if ("succeeded".equals(charge.getStatus())) {
            System.out.println("Direct Charge Success");
        } else {
            System.out.println("Direct Charge Failure");
        }
    }
}
