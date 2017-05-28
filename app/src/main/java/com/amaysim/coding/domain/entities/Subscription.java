package com.amaysim.coding.domain.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Subscription {

    private static final SimpleDateFormat SUBSCRIPTION_DATE_PARSE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SUBSCRIPTION_DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy");

    public final String name;
    public final String dataBalance;
    public final String creditBalance;
    public final String expiryDate;
    public final String price;

    public Subscription(String name,
                        String dataBalance,
                        String creditBalance,
                        String expiryDate,
                        String price) {
        this.name = name;
        this.dataBalance = dataBalance;
        this.creditBalance = creditBalance;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public String getPrice() {
        double cents = Double.parseDouble(price);
        return String.format("$%.2f", cents / 1000);
    }

    public String getCreditBalance() {
        double mb = Double.parseDouble(creditBalance == null ? "0" : creditBalance);
        return String.format("%.2fGB", mb / 1000);
    }

    public String getDataBalance() {
        double mb = Double.parseDouble(dataBalance == null ? "0" : dataBalance);
        return String.format("%.2fGB", mb / 1000);
    }

    public String getExpiryDate() {
        try {
            Date date = SUBSCRIPTION_DATE_PARSE.parse(expiryDate);
            return SUBSCRIPTION_DATE_FORMAT.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
}