package com.fonline.claro.processors.fonline.functions;

import com.fonline.claro.processors.fonline.entity.BillingDetail;



public class Functions {

    public BillingDetail getCLT000DValues(String registro, BillingDetail billingDetail, int despuesCodigo) {
        //int32 base = 7 ;

        //mutable BillingDetail outTuple = {};

        // Inicializa estos dos valores que utilizaran todas las tuplas.
        //outTuple.WBD_DUE_DATE = (rstring)substring(line, base + 29, 10) ;
        billingDetail.setWBD_DUE_DATE(registro.substring(despuesCodigo + 29, despuesCodigo + 39));
        //outTuple.WBD_ACC_ID = (rstring)trim(substring(line, base + 3, 10), (ustring)" ") ;
        billingDetail.setWBD_ACC_ID(registro.substring(despuesCodigo + 3, despuesCodigo + 13));

        //outTuple.WBD_SUMMARY_TYPE = "R" ;
        billingDetail.setWBD_SUMMARY_TYPE("R");
        //outTuple.WBD_TYPE = "R";
        billingDetail.setWBD_TYPE("R");
        //outTuple.WBD_CHARGE_DESCRIPTION = "Total a Pagar" ;
        billingDetail.setWBD_CHARGE_DESCRIPTION("Total a Pagar");
        //outTuple.WBD_TOTAL_AMOUNT = getAmountOrPriceLen16(line, 13);
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 13, despuesCodigo + 28));

        //return outTuple;
        return billingDetail;
    }

    public BillingDetail getFAC010DValues(String registro, BillingDetail billingDetail, int despuesCodigo) {

        billingDetail.setWBD_CLU_BILL_NUMBER(registro.substring(despuesCodigo + 49, despuesCodigo + 64) + "- ");

        return billingDetail;
    }

    public BillingDetail getRES040DValues(String registro, BillingDetail billingDetail, int despuesCodigo) {

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 11, despuesCodigo + 81));
        billingDetail.setWBD_UNIT_PRICE(registro.substring(despuesCodigo + 91, despuesCodigo + 107));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 91, despuesCodigo + 107));
        billingDetail.setWBD_DESCRIPTION_DATE(registro.substring(despuesCodigo + 1, despuesCodigo + 11));

        return billingDetail;
    }

    public BillingDetail getFAC250DValues(String registro, BillingDetail billingDetail, int despuesCodigo) {

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 71));
        billingDetail.setWBD_UNIT_PRICE(registro.substring(despuesCodigo + 92, despuesCodigo + 102));
        billingDetail.setWBD_QTY(registro.substring(despuesCodigo + 71, despuesCodigo + 81));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 103, despuesCodigo + 119));

        return billingDetail;
    }

    public BillingDetail getRES040TValues(String registro, BillingDetail billingDetail, int despuesCodigo) {

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION("Total Resumen de Cuenta");
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 1, despuesCodigo + 17));

        return billingDetail;
    }

    public BillingDetail getFAC299TValues(String registro, BillingDetail billingDetail, int despuesCodigo) {

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 36));
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 36, despuesCodigo + 52));

        return billingDetail;
    }

    public BillingDetail getRES045TValues(String registro, BillingDetail billingDetail, int despuesCodigo) {

        billingDetail.setWBD_SUMMARY_TYPE("C");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION("Total Cuota de Equipo");
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 1, despuesCodigo + 17));

        return billingDetail;
    }

    public BillingDetail getRES045DValues(String registro, BillingDetail billingDetail, int despuesCodigo) {

        billingDetail.setWBD_SUMMARY_TYPE("C");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 13, despuesCodigo + 81));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT(registro.substring(despuesCodigo + 1, despuesCodigo + 13));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 91, despuesCodigo + 107));

        return billingDetail;
    }

}
