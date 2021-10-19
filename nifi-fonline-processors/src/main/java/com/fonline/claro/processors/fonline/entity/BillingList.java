package com.fonline.claro.processors.fonline.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillingList {

    private List<BillingDetail> billingDetails;

    @Override
    public String toString(){
        return billingDetails.toString();
    }

}
