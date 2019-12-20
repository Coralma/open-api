package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

public class ChargeSample {


    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";


        Charge charge =
                Charge.retrieve("ch_1FqfcrE4t518uVYgsQjgXS8N");
        /*ChargeCollection charges = Charge.list(params);
        ChargeCollection charges = Charge.list(params);*/
        System.out.println(charge);
    }
}
