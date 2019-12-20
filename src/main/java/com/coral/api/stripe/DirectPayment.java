package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import java.util.HashMap;
import java.util.Map;

public class DirectPayment {


    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";

        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 1);
        cardParams.put("exp_year", 2020);
        cardParams.put("cvc", "314");
        tokenParams.put("card", cardParams);

        Token token = Token.create(tokenParams);
        String tokenId = token.getId();
        System.out.println("tokenId is " + tokenId);

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", "Customer for ZA");
        customerParams.put("source", tokenId);

        Customer c = null;
        try {
            c = Customer.create(customerParams);
            System.out.println(c);
        } catch (StripeException e) {
            e.printStackTrace();
        }


        //发起支付
        Map<String, Object> payParams = new HashMap<>();
        payParams.put("amount", 2800);
        payParams.put("currency", "usd");
        payParams.put("description", "Charge from Coral");
        payParams.put("customer", c.getId());
        Charge charge = Charge.create(payParams);

        //charge  支付是同步通知
        if ("succeeded".equals(charge.getStatus())) {
            //交易成功后，需要更新我们的订单表，修改业务参数，此处省略
            System.out.println("Charge Success");
        } else {
            System.out.println("Charge Failure");
        }
    }
/*
    public void addCard(StripePayRequestVO stripePayRequestVO) {

        Stripe.apiKey = "your_alipay";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", stripePayRequestVO.getUserId());
        User user = this.get("from User where id=:userId", params);
        if (null == user) {
            return failure(ResponseEnum.USER_NOT_FOUND_FAILURE);
        }

        //如果没有这个stripe用户，添加卡片就是创建用户
        if (user.getStripeChargeId() == null || "".equals(user.getStripeChargeId())) {
            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("description", "Customer for test");
            customerParams.put("source", stripePayRequestVO.getToken());

            Customer c = null;
            try {
                c = Customer.create(customerParams);
                user.setStripeChargeId(c.getId());
                this.saveOrUpdate(user);
                success("添加成功");
            } catch (StripeException e) {
                e.printStackTrace();
            }

        } else {
            //  有这个用户，就是修改他的唯一一张默认卡
            try {
                Customer c = Customer.retrieve(user.getStripeChargeId());
                System.out.println("给客户修改默认卡号");
                Map<String, Object> tokenParam = new HashMap<String, Object>();
                tokenParam.put("source", stripePayRequestVO.getToken());
                c.update(tokenParam);
                return success("修改成功");
            } catch (StripeException e) {
                System.out.println("异常了");
                System.out.println(e);
                e.printStackTrace();
            }


        }
        return failure(ResponseEnum.EVENT_SYSTEM_ERROR_FAILURE);
    }*/
}
