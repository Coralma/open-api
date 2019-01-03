package com.coral.api.integration;

import com.alibaba.fastjson.JSON;
import com.coral.api.utils.FormatUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by coral on 2018/10/23.
 */
public class StripeAPI {

    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        charge();
        //retrieve();
    }

    public static void charge() throws StripeException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("amount", 999);
        params.put("currency", "usd");
        params.put("source", "tok_visa");
        params.put("receipt_email", "jenny.rosen@example.com");
        Charge charge = Charge.create(params);
        FormatUtil.printJson(JSON.toJSONString(charge));
    }

    public static void retrieve() throws StripeException {
        Charge charge = Charge.retrieve("ch_1DOGNR2eZvKYlo2CEOUxQujl");
        FormatUtil.printJson(JSON.toJSONString(charge));
    }
}
