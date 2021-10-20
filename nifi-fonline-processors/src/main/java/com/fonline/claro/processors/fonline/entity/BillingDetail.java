package com.fonline.claro.processors.fonline.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class BillingDetail {
    private String WBD_ACC_ID;
    private String WBD_PRINT_DATE;
    private String WBD_DUE_DATE;
    private String WBD_FILENAME;
    private String WBD_CLU_BILL_NUMBER;
    private BigInteger WBD_LINE_ORDER;
    private String WBD_CHARGE_TYPE;
    private String WBD_SUMMARY_TYPE;
    private String WBD_CHARGE_DESCRIPTION;
    private String WBD_UNIT_PRICE;
    private String WBD_QTY;
    private String WBD_AMOUNT;
    private String WBD_TOTAL_AMOUNT;
    private String WBD_BILL_NUMBER_INSTALMENT;
    private String WBD_DESCRIPTION_DATE;
    private String WBD_TYPE;

    @Override
    public String toString(){
        return  "\'" + WBD_ACC_ID +"\'"+","+
                "\'" + WBD_PRINT_DATE +"\'"+","+
                "\'" + WBD_DUE_DATE +"\'"+","+
                "\'" + WBD_FILENAME +"\'"+","+
                "\'" + WBD_CLU_BILL_NUMBER +"\'"+","+
                "\'" + WBD_LINE_ORDER +"\'"+","+
                "\'" + WBD_CHARGE_TYPE +"\'"+","+
                "\'" + WBD_SUMMARY_TYPE +"\'"+","+
                "\'" + WBD_CHARGE_DESCRIPTION +"\'"+","+
                "\'" + WBD_UNIT_PRICE +"\'"+","+
                "\'" + WBD_QTY +"\'"+","+
                "\'" + WBD_AMOUNT +"\'"+","+
                "\'" + WBD_TOTAL_AMOUNT +"\'"+","+
                "\'" + WBD_BILL_NUMBER_INSTALMENT +"\'"+","+
                "\'" + WBD_DESCRIPTION_DATE +"\'"+","+
                "\'" + WBD_TYPE +"\'" + "''";
    }

}
