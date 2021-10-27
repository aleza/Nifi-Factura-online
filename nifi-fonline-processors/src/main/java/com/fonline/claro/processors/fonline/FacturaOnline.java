/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fonline.claro.processors.fonline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fonline.claro.processors.fonline.entity.BillingDetail;
import com.fonline.claro.processors.fonline.entity.BillingDetailBac;
import com.fonline.claro.processors.fonline.entity.BillingList;
import com.fonline.claro.processors.fonline.functions.Functions;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.ReadsAttributes;
import org.apache.nifi.annotation.behavior.WritesAttribute;
import org.apache.nifi.annotation.behavior.WritesAttributes;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.SeeAlso;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.flowfile.attributes.CoreAttributes;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Tags({"example"})
@CapabilityDescription("Provide a description")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute = "", description = "")})
@WritesAttributes({@WritesAttribute(attribute = "", description = "")})
public class FacturaOnline extends AbstractProcessor {

    public static final PropertyDescriptor MY_PROPERTY = new PropertyDescriptor
            .Builder().name("MY_PROPERTY")
            .displayName("MY PROPERTY")
            .description("Custom property")
            .required(true)
            .build();

    public static final Relationship failure = new Relationship.Builder()
            .name("failure")
            .description("Fallo al procesar archivo de entrada")
            .build();

    public static final Relationship success = new Relationship.Builder()
            .name("success")
            .description("Proceso OK")
            .build();


    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
        descriptors.add(MY_PROPERTY);
        // this.descriptors = Collections.unmodifiableList(descriptors);

        final Set<Relationship> relationships = new HashSet<Relationship>();
        relationships.add(failure);
        relationships.add(success);
        this.relationships = Collections.unmodifiableSet(relationships);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return descriptors;
    }

    @OnScheduled
    public void onScheduled(final ProcessContext context) {

    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
        FlowFile flowFile = session.get();
        ObjectMapper objectMapper = new ObjectMapper();
        if (flowFile == null) {
            return;
        }
        // TODO implement
        try {
            flowFile = session.write(flowFile, (rawIn, rawOut) -> {
                try (final InputStream in = new BufferedInputStream(rawIn); final OutputStream out =
                        new BufferedOutputStream(rawOut)) {

                    // Convert input stream flow file into a byte array
                    //byte[] input = toByteArray(in);
                    byte[] input = in.readAllBytes();

                    // Extract fields out of input
                    Map<String, String> extracted = extractFields(input);
                    //BillingDetail archivo = formatCsv(input);
                    BillingList archivoL = formatCsv(input);


                    // Write json bytes into output
                    //out.write(objectMapper.writeValueAsBytes(extracted));

                    out.write(objectMapper.writeValueAsBytes(archivoL.toString()));


                    //out.write(Integer.parseInt(objectMapper.writeValueAsString(archivo)));
                }
            });
        } catch (ProcessException pe) {
            getLogger().error("Failed to deserialize {}", new Object[]{flowFile, pe});
            session.transfer(flowFile, failure);
            return;
        }

        flowFile = session.putAttribute(flowFile, CoreAttributes.MIME_TYPE.key(), "application/json");
        session.transfer(flowFile, success);


    }

    private Map<String, String> extractFields(byte[] input) {

        String inputString = new String(input, StandardCharsets.UTF_8);
        Map<String, String> extracted = new HashMap<String, String>();
        extracted.put("input", inputString);
        return extracted;
    }

    //private BillingDetail formatCsv(byte[] input){
    private BillingList formatCsv(byte[] input) {

        BillingList billingList = new BillingList();
        ArrayList<BillingDetail> billingDetails = new ArrayList<BillingDetail>();
        Functions functions = new Functions();

        String inputString = new String(input, StandardCharsets.UTF_8);

        int inicio = 0;
        //int corte = inputString.indexOf("\n");
        int corte = 150;
        String renglon = "";
        //int despuesCodigo = 0;
        int despuesCodigo = 7;
        int paso = 0;
        int index = 0;

        BillingDetail billingDetail = new BillingDetail();
        BillingDetailBac billingDetailBac = new BillingDetailBac();

        for (int i = 0; i < inputString.length(); i++) {

            try {
                renglon = inputString.substring(inicio, corte);
                inicio = corte + 1;
                corte = inputString.indexOf("\n", corte + 1);
            } catch (StringIndexOutOfBoundsException e) {
                renglon = inputString.substring(inicio, inputString.length());
                i = inputString.length();
            }

            //BillingDetail billingDetail = new BillingDetail();

            //Ver
            billingDetail.setWBD_FILENAME("z3_2026_36126.dat");

            if (renglon.contains("CLT000D")) {
                index = 0;
                //despuesCodigo = renglon.indexOf(" ");
                functions.getCLT000DValues(renglon, billingDetail, despuesCodigo);
                cargaBac(billingDetail, billingDetailBac);
                //paso ++;
            } else if (renglon.contains("RES010D")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getRES010DValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("FAC010D")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getFAC010DValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                //paso ++;
            } else if (renglon.contains("RES040D")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getRES040DValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("FAC250D")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getFAC250DValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("RES040T")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getRES040TValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("FAC299T")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getFAC299TValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("RES045T")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getRES045TValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("RES045D")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getRES045DValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("FAC200T")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getFAC200TValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("FAC040D") ||
                    renglon.contains("FAC050D") ||
                    renglon.contains("FAC110D")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getFACXXXDValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("FAC120D")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getFAC120DValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            } else if (renglon.contains("FAC300T")) {
                //despuesCodigo = renglon.indexOf(" ");
                functions.getFAC300TValues(renglon, billingDetail, despuesCodigo, billingDetailBac);
                cargaBac(billingDetail, billingDetailBac);
                paso++;
            }

            if (paso > 0) {
                paso = 0;
                index++;
                billingDetail.setWBD_LINE_ORDER(index);
                billingDetails.add(billingDetail);
                billingDetail = new BillingDetail();
                //billingDetailBac = new BillingDetailBac();
            }
        }
        billingList.setBillingDetails(billingDetails);
        return billingList;
    }

    private BillingDetailBac cargaBac(BillingDetail billingDetail, BillingDetailBac billingDetailBac) {
        billingDetailBac.setBillingDetail(billingDetail);
        return billingDetailBac;
    }


}
