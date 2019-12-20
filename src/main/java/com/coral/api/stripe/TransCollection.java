package com.coral.api.stripe;

import com.stripe.Stripe;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;

import java.util.HashMap;
import java.util.Map;

public class TransCollection {

    public static void main(String[] args) throws Exception {
        Stripe.apiKey = "sk_test_j61O41rS0rYDJFrsC5HQnGMq00StxV7wVC";

        Map<String, Object> params = new HashMap<>();
        params.put("payout", "po_1FraJbE4t518uVYgEtcvbFG7");
        //params.put("type", "charge");
        params.put("limit", 10);
        BalanceTransactionCollection btc = BalanceTransaction.list(params);
        System.out.println(btc);

    }
}
