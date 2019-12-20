package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.model.Customer;

public class CustomerQuery {

    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";
        Customer customer = Customer.retrieve("cus_GGEXdYATJDr3Jd");
        System.out.println(customer);
    }
}
