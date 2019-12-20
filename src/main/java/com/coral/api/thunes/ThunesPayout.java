package com.coral.api.thunes;

import com.coral.api.utils.HttpConnectionUtils;
import com.google.gson.Gson;

import java.util.Map;

public class ThunesPayout {

    Gson gson = new Gson();

    public static void main(String[] args) {
        System.out.println("ok");
        ThunesPayout thunesPayout = new ThunesPayout();
        thunesPayout.executePayout();
    }

    public void executePayout() {
//        String payers = HttpConnectionUtils.httpGetWithAuth("https://api-mt.pre.thunes.com/v2/money-transfer/payers", "UTF-8");
        String payer = HttpConnectionUtils.httpGetWithAuth("https://api-mt.pre.thunes.com/v1/money-transfer/payers/214", "UTF-8");

        String quoJason = genQuotationJson("214", "C000009");
        String quotation = HttpConnectionUtils.httpPostWithAuth("https://api-mt.pre.thunes.com/v2/money-transfer/quotations", quoJason);
        Map map = gson.fromJson(quotation, Map.class);
        Double id = (Double)map.get("id");

        System.out.println("id: "+id.longValue());
        System.out.println(quotation);
    }

    public String genQuotationJson(String payerId, String externalId) {
        String quoJason = "{\n" +
                "\t\"external_id\" : \""+externalId+"\", \n" +
                "\t\"payer_id\" : \""+payerId +"\",\n" +
                "\t\"mode\" : \"SOURCE_AMOUNT\", \n" +
                "\t\"transaction_type\" : \"B2C\", \n" +
                "\t\"source\" : {\n" +
                "\t\t\"amount\" : \"1\", \n" +
                "\t\t\"currency\" : \"SGD\", \n" +
                "\t\t\"country_iso_code\" : \"SGP\"\n" +
                "\t},\n" +
                "\t\"destination\" : {\n" +
                "\t\t\"amount\" : null,\n" +
                "\t\t\"currency\" : \"IDR\" \n" +
                "\t}\n" +
                "}";
        return quoJason;
    }
}
