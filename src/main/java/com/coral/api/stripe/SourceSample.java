package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.model.Source;

import java.util.HashMap;
import java.util.Map;

public class SourceSample {


    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";

        Source source = Source.retrieve("ch_1FqfAKE4t518uVYgNg0KCi6z");
        System.out.println(source);
    }
}
