package com.fonline.claro.processors.fonline.functions;

import com.fonline.claro.processors.fonline.entity.BillingDetail;
import com.fonline.claro.processors.fonline.entity.BillingDetailBac;

import java.math.BigDecimal;
import java.text.DecimalFormat;


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
        billingDetail.setWBD_TOTAL_AMOUNT(formatValue(registro.substring(despuesCodigo + 13, despuesCodigo + 29),16));

        billingDetail.setWBD_CLU_BILL_NUMBER("");
        billingDetail.setWBD_CHARGE_TYPE("");
        billingDetail.setWBD_UNIT_PRICE(formatValue("0 ",2));
        billingDetail.setWBD_QTY(formatValue("0 ",2));
        billingDetail.setWBD_AMOUNT(formatValue("0 ",2));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT("");
        billingDetail.setWBD_DESCRIPTION_DATE("");

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
            billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(registro.substring(despuesCodigo + 49, despuesCodigo + 64).replace("-", "")));
        }

        return billingDetail;
    }

    public BillingDetail getRES040DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT((billingDetailBac.getBillingDetail().getWBD_BILL_NUMBER_INSTALMENT()));

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(trimSpaces(registro.substring(despuesCodigo + 11, despuesCodigo + 81)));
        billingDetail.setWBD_UNIT_PRICE(formatValue(registro.substring(despuesCodigo + 91, despuesCodigo + 107), 16));
        billingDetail.setWBD_QTY(formatValue(registro.substring(despuesCodigo + 71, despuesCodigo + 81),10));
        billingDetail.setWBD_AMOUNT(formatValue(registro.substring(despuesCodigo + 91, despuesCodigo + 107),16));
        billingDetail.setWBD_DESCRIPTION_DATE(registro.substring(despuesCodigo + 1, despuesCodigo + 11));
        billingDetail.setWBD_TOTAL_AMOUNT(formatValue("000", 3));

        return billingDetail;
    }

    public BillingDetail getFAC250DValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 71));
        billingDetail.setWBD_UNIT_PRICE(formatValue(registro.substring(despuesCodigo + 92, despuesCodigo + 102),10));
        billingDetail.setWBD_QTY(formatValue(registro.substring(despuesCodigo + 71, despuesCodigo + 81),10));
        billingDetail.setWBD_AMOUNT(formatValue(registro.substring(despuesCodigo + 103, despuesCodigo + 119),16));

        return billingDetail;
    }

    public BillingDetail getRES040TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());


        billingDetail.setWBD_UNIT_PRICE(formatValue("000", 3));
        billingDetail.setWBD_QTY(formatValue("000", 3));
        billingDetail.setWBD_AMOUNT(formatValue("000", 3));

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION("Total Resumen de Cuenta");
        billingDetail.setWBD_TOTAL_AMOUNT(formatValue(registro.substring(despuesCodigo + 1, despuesCodigo + 17),16));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT("");
        billingDetail.setWBD_DESCRIPTION_DATE("");

        return billingDetail;
    }

    public BillingDetail getFAC299TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_CLU_BILL_NUMBER("");
        billingDetail.setWBD_UNIT_PRICE(formatValue("000", 3));
        billingDetail.setWBD_QTY(formatValue("000", 3));
        billingDetail.setWBD_AMOUNT(formatValue("000", 3));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT("");
        billingDetail.setWBD_DESCRIPTION_DATE("");

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(trimSpaces(registro.substring(despuesCodigo + 1, despuesCodigo + 36)));
        billingDetail.setWBD_TOTAL_AMOUNT(formatValue(registro.substring(despuesCodigo + 36, despuesCodigo + 52), 16));

        return billingDetail;
    }

    public BillingDetail getRES045TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));
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
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));
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
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        billingDetail.setWBD_UNIT_PRICE(formatValue("000", 3));
        billingDetail.setWBD_QTY(formatValue("000", 3));
        billingDetail.setWBD_AMOUNT(formatValue("000", 3));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT("");
        billingDetail.setWBD_DESCRIPTION_DATE("");

        billingDetail.setWBD_SUMMARY_TYPE("R");
        billingDetail.setWBD_TYPE("R");
        billingDetail.setWBD_CHARGE_DESCRIPTION(trimSpaces(registro.substring(despuesCodigo + 1, despuesCodigo + 21)));
        billingDetail.setWBD_TOTAL_AMOUNT(formatValue(registro.substring(despuesCodigo + 21, despuesCodigo + 37),16));
        //billingDetail.setWBD_AMOUNT(registro.substring(despuesCodigo + 21, despuesCodigo + 37));

        return billingDetail;
    }

    public BillingDetail getFAC200TValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));

        billingDetail.setWBD_CHARGE_TYPE("");
        billingDetail.setWBD_SUMMARY_TYPE("");
        billingDetail.setWBD_UNIT_PRICE(formatValue("000", 3));
        billingDetail.setWBD_QTY(formatValue("000", 3));
        billingDetail.setWBD_AMOUNT(formatValue("000", 3));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT("");
        billingDetail.setWBD_DESCRIPTION_DATE("");

        billingDetail.setWBD_CHARGE_DESCRIPTION(registro.substring(despuesCodigo + 1, despuesCodigo + 21));
        billingDetail.setWBD_TOTAL_AMOUNT(formatValue(registro.substring(despuesCodigo + 21, despuesCodigo + 37),16));
        billingDetail.setWBD_TYPE("D");

        return billingDetail;
    }
    public BillingDetail getFACXXXDValues(String registro, BillingDetail billingDetail, int despuesCodigo, BillingDetailBac billingDetailBac) {

        billingDetail.setWBD_ACC_ID(billingDetailBac.getBillingDetail().getWBD_ACC_ID());
        billingDetail.setWBD_DUE_DATE(billingDetailBac.getBillingDetail().getWBD_DUE_DATE());
        billingDetail.setWBD_PRINT_DATE(billingDetailBac.getBillingDetail().getWBD_PRINT_DATE());
        billingDetail.setWBD_CLU_BILL_NUMBER(trimSpaces(billingDetailBac.getBillingDetail().getWBD_CLU_BILL_NUMBER()));
        billingDetail.setWBD_CHARGE_TYPE(billingDetailBac.getBillingDetail().getWBD_CHARGE_TYPE());

        if(registro.contains("FAC040D")){
            billingDetail.setWBD_CHARGE_TYPE("Cargos Fijos");
        }else if(registro.contains("FAC050D")){
            billingDetail.setWBD_CHARGE_TYPE("Cargos Variables");
        }else if(registro.contains("FAC110D")){
            billingDetail.setWBD_CHARGE_TYPE("Otros Cargos");
        }

        billingDetail.setWBD_SUMMARY_TYPE("");
        billingDetail.setWBD_TYPE("D");
        billingDetail.setWBD_CHARGE_DESCRIPTION(trimSpaces(registro.substring(despuesCodigo + 1, despuesCodigo + 71)));
        billingDetail.setWBD_UNIT_PRICE(formatValueTres(registro.substring(despuesCodigo + 94, despuesCodigo + 105),11));
        billingDetail.setWBD_AMOUNT(formatValue(registro.substring(despuesCodigo + 105, despuesCodigo + 121),16));
        billingDetail.setWBD_QTY(formatValue(registro.substring(despuesCodigo + 71, despuesCodigo + 85),10));

        //estos no los tiene en cuenta en el código de IBM
        billingDetail.setWBD_TOTAL_AMOUNT(formatValue("000", 3));
        billingDetail.setWBD_BILL_NUMBER_INSTALMENT("");
        billingDetail.setWBD_DESCRIPTION_DATE("");

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
        billingDetail.setWBD_UNIT_PRICE(formatValue(registro.substring(despuesCodigo + 92, despuesCodigo + 103),11));
        billingDetail.setWBD_QTY(formatValue(registro.substring(despuesCodigo + 71, despuesCodigo + 82),11));
        billingDetail.setWBD_AMOUNT(formatValue(registro.substring(despuesCodigo + 103, despuesCodigo + 119),16));
        billingDetail.setWBD_TYPE("D");
        billingDetail.setWBD_CHARGE_TYPE("Cargos Variables");

        return billingDetail;
    }


    public String formatValue(String numeroConSigno, int termina){
        if (numeroConSigno.isBlank()){
            numeroConSigno="000";
            termina=3;
        }
        String numeroSinSigno = numeroConSigno.substring(0,termina - 1);
        String signo = numeroConSigno.substring(termina - 1, termina);
        BigDecimal numeroConComa = new BigDecimal(numeroSinSigno);
        numeroConComa = numeroConComa.divide(BigDecimal.valueOf(100));
        DecimalFormat formatter = new DecimalFormat("#0.0#");
        String valor = formatter.format(numeroConComa);
        String valorConSigno = (signo.equals("-"))?signo.concat(valor):valor;

        return valorConSigno;
    }

    public String formatValueTres(String numeroConSigno, int termina){
        if (numeroConSigno.isBlank()){
            numeroConSigno="000";
            termina=3;
        }
        String numeroSinSigno = numeroConSigno.substring(0,termina - 1);
        String signo = numeroConSigno.substring(termina - 1, termina);
        BigDecimal numeroConComa = new BigDecimal(numeroSinSigno);
        numeroConComa = numeroConComa.divide(BigDecimal.valueOf(10000));
        DecimalFormat formatter = new DecimalFormat("#0.0#");
        String valor = formatter.format(numeroConComa);
        String valorConSigno = (signo.equals("-"))?signo.concat(valor):valor;

        return valorConSigno;
    }

    public String trimSpaces(String entrada){
        return entrada.trim();
    }

}
