package com.coral.api.stripe;

import com.alibaba.fastjson.JSON;
import com.stripe.Stripe;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

//com.stripe.exception.InvalidRequestException: Charge ID cannot be captured because the charge has expired
// (a charge must be captured within 7 days). You will need to create a new charge to retry the payment.;
// code: charge_expired_for_capture
public class CapturePayment {


    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";

        String authChargeId = "ch_1FrLuvE4t518uVYgr8VT1EIc";
        Charge charge2 = Charge.retrieve(authChargeId);

        Map<String, Object> captureParams = new HashMap<>();
        captureParams.put("amount", 1000);
        //captureParams.put("description", "Capture by Snack");
        Charge c = charge2.capture(captureParams);
        System.out.println(c);
    }
}
