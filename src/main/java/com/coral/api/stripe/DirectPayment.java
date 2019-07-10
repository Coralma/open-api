package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.model.Token;

import java.util.HashMap;
import java.util.Map;

public class DirectPayment {


    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_lbpohsjMzvrEG2AagMzUDYtk";

        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 1);
        cardParams.put("exp_year", 2020);
        cardParams.put("cvc", "314");
        tokenParams.put("card", cardParams);

        Token.create(tokenParams);
    }
}
