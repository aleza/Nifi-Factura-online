package com.fonline.claro.processors.fonline.functions;

import com.fonline.claro.processors.fonline.entity.BillingDetail;



public class Functions {

    public BillingDetail getCLT000DValues(String registro, BillingDetail billingDetail, int despuesCodigo) {
        //int32 base = 7 ;

        //mutable BillingDetail outTuple = {};

        // Assign matching fields of a tuple from another one.
        //assignFrom(outTuple, billingDetail);

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

}
