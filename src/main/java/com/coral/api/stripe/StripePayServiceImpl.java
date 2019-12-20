package com.coral.api.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class StripePayServiceImpl {

/*
    public Response charge(StripePayRequestVO request) {

        try {
            Stripe.apiKey = "your_apikey";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userId", request.getUserId());
            User user = this.get("from User where id=:userId", params);
            if (null == user) {
                return failure(ResponseEnum.USER_NOT_FOUND_FAILURE);
            }
            //无stripe账号，直接返回
            if (user.getStripeChargeId() == null || "".equals(user.getStripeChargeId())) {
                return success(ResponseEnum.USER_BAD_REQUEST_FAILURE);
            }

            // 业务订单数据，此处省略

            //发起支付
            Map<String, Object> payParams = new HashMap<>();
            payParams.put("amount", product.getPrice().intValue());
            payParams.put("currency", "usd");
            payParams.put("description", "Charge for " + user.getEmail());
            payParams.put("customer", user.getStripeChargeId());
            Charge charge = Charge.create(payParams);


            //charge  支付是同步通知
            if ("succeeded".equals(charge.getStatus())) {
                //交易成功后，需要更新我们的订单表，修改业务参数，此处省略
                return success(ResponseEnum.PAY_SUCCESS.getMessage());
            } else {
                return failure(ResponseEnum.PAY_ALIPAY_FAILURE);
            }

        } catch (StripeException e) {
            e.printStackTrace();
        }
        return failure(ResponseEnum.EVENT_SYSTEM_ERROR_FAILURE);
    }

    @Override
    public Response getCardList(StripePayRequestVO stripePayRequestVO) {
        Stripe.apiKey = "your_alipay";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", stripePayRequestVO.getUserId());
        User user = this.get("from User where id=:userId", params);
        if (null == user) {
            return failure(ResponseEnum.USER_NOT_FOUND_FAILURE);
        }
        List list = new ArrayList<StripeAddCardVO>();
        //如果没有这个stripe用户，就返回列表为空


        try {
            Map<String, Object> cardParams = new HashMap<String, Object>();
            cardParams.put("limit", 1);
            cardParams.put("object", "card");
            List<PaymentSource> cardList = Customer.retrieve(user.getStripeChargeId()).getSources().list(cardParams).getData();
            StripeCardVO stripeCardVO = new StripeCardVO();
            for (PaymentSource p : cardList) {
                Card c = (Card) p;
                stripeCardVO.setLast4(c.getLast4());
                stripeCardVO.setExpYear(c.getExpYear());
                stripeCardVO.setExpMonth(c.getExpMonth());
                list.add(stripeCardVO);
            }
            return success(list);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response addCard(StripePayRequestVO stripePayRequestVO) {

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
