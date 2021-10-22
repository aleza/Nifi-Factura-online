package com.fonline.claro.processors.fonline.functions;

import com.fonline.claro.processors.fonline.entity.BillingDetail;
import com.fonline.claro.processors.fonline.entity.BillingDetailBac;

import java.math.BigInteger;


public class Functions {

    public BillingDetail getCLT000DValues(String registro, BillingDetail billingDetail, int despuesCodigo) {
        //int32 base = 7 ;

        //mutable BillingDetail outTuple = {};

        // Inicializa estos dos valores que utilizaran todas las tuplas.
        billingDetail.setWBD_DUE_DATE(registro.substring(despuesCodigo + 29, despuesCodigo + 39));
        billingDetail.setWBD_ACC_ID(registro.substring(despuesCodigo + 3, despuesCodigo + 13));
        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION("Total a Pagar");
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 13, despuesCodigo + 28));

        billingDetail.setWBD_CLU_BILL_NUMBER("");
        billingDetail.setWBD_CHARGE_TYPE("");

        return billingDetail;
    }

    public BillingDetail getRES010DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {
        //int32 base = 7 ;

        billingDetail.setWBD_PRINT_DATE(registro.substring(despuesCodigo + 1, despuesCodigo + 11));

        return billingDetail;
    }

    public BillingDetail getFAC010DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        if(registro.substring(despuesCodigo + 49, despuesCodigo + 64) != null){
            billingDetail.setWBD_CLU_BILL_NUMBER(registro.substring(despuesCodigo + 49, despuesCodigo + 64));
        }

        return billingDetail;
    }

    public BillingDetail getRES040DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 11, despuesCodigo + 81));
        billingDetail.setWBD_UNIT_PRICE(registro.substring(despuesCodigo + 91, despuesCodigo + 107));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 91, despuesCodigo + 107));
        billingDetail.setWBD_DESCRIPTION_DATE(registro.substring(despuesCodigo + 1, despuesCodigo + 11));

        return billingDetail;
    }

    public BillingDetail getFAC250DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 71));
        billingDetail.setWBD_UNIT_PRICE(registro.substring(despuesCodigo + 92, despuesCodigo + 102));
        billingDetail.setWBD_QTY(registro.substring(despuesCodigo + 71, despuesCodigo + 81));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 103, despuesCodigo + 119));

        return billingDetail;
    }

    public BillingDetail getRES040TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION("Total Resumen de Cuenta");
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 1, despuesCodigo + 17));

        return billingDetail;
    }

    public BillingDetail getFAC299TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 36));
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 36, despuesCodigo + 52));

        return billingDetail;
    }

    public BillingDetail getRES045TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("C");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION("Total Cuota de Equipo");
        billingDetail.setWBD_TOTAL_AMOUNT(registro.substring(despuesCodigo + 1, despuesCodigo + 17));

        return billingDetail;
    }

    public BillingDetail getRES045DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("C");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 13, despuesCodigo + 81));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT(registro.substring(despuesCodigo + 1, despuesCodigo + 13));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 91, despuesCodigo + 107));

        return billingDetail;
    }

    public BillingDetail getFAC300TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("C");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 21));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 21, despuesCodigo + 37));

        return billingDetail;
    }

    public BillingDetail getFAC200TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 21));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 21, despuesCodigo + 37));
        billingDetail.setWBD_TYPE("D");
        billingDetail.setWBD_CLU_BILL_NUMBER("");

        return billingDetail;
    }
    public BillingDetail getFACXXXDValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        if(registro.contains("FAC040D")){
            billingDetail.setWBD_CHARGE_TYPE("Cargos Fijos");
        }else if(registro.contains("FAC050D")){
            billingDetail.setWBD_CHARGE_TYPE("Cargos Variables");
        }else if(registro.contains("FAC110D")){
            billingDetail.setWBD_CHARGE_TYPE("Otros Cargos");
        }

        billingDetail.setWBD_TYPE("D");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 71));
        billingDetail.setWBD_UNIT_PRICE(registro.substring(despuesCodigo + 94, despuesCodigo + 105));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 105, despuesCodigo + 114));
        billingDetail.setWBD_QTY(registro.substring(despuesCodigo + 71, despuesCodigo + 84));

        return billingDetail;
    }
    public BillingDetail getFAC120DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());

        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 71));
        billingDetail.setWBD_UNIT_PRICE(registro.substring(despuesCodigo + 92, despuesCodigo + 103));
        billingDetail.setWBD_QTY(registro.substring(despuesCodigo + 71, despuesCodigo + 82));
        billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 103, despuesCodigo + 119));
        billingDetail.setWBD_TYPE("D");
        billingDetail.setWBD_CHARGE_TYPE("Cargos Variables");

        return billingDetail;
    }

}
