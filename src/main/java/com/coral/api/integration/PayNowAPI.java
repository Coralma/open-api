package com.coral.api.integration;

import com.coral.api.utils.HttpConnectionUtils;

/**
 * Created by coral on 2018/10/23.
 */
public class PayNowAPI {

    public static void main(String[] args) {
        checkStatus();
    }

    public static void authentication() {


    }

    public static void initiatePayment() {
        String url = "https://api-sandbox.income.com.sg/payment/paynow/scb/initiatepayment";
    }


    public static void checkStatus() {
        String url = "https://api-sandbox.income.com.sg/payment/paynow/scb/checkstatus";
        String json = "{" +
                "\"client_reference_ids\": [\"123456\"]\n" +
                "}";
        String result = HttpConnectionUtils.httpPost(url, json);
        System.out.println("payment refer: "+ result);
    }
}
