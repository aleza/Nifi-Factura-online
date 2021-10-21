package com.fonline.claro.processors.fonline.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class BillingDetailBac {
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

}
