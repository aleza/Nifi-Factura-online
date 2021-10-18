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
import com.fonline.claro.processors.fonline.entity.Archivo;
import com.fonline.claro.processors.fonline.entity.BillingDetail;
import com.fonline.claro.processors.fonline.functions.Functions;
import com.opencsv.CSVWriter;
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
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;


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
                    //String csvFile = formatCsv(input);
                    BillingDetail archivo = formatCsv(input);


                    // Write json bytes into output
                    //out.write(objectMapper.writeValueAsBytes(extracted));
                    out.write(objectMapper.writeValueAsBytes(archivo.toString()));
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

    private BillingDetail formatCsv(byte[] input){

        Functions functions = new Functions();
        BillingDetail billingDetail = new BillingDetail();


        Archivo archivo = new Archivo();
        String inputString = new String(input, StandardCharsets.UTF_8);

        int inicio = 0;
        int corte = inputString.indexOf("\n");
        String renglon = "";
        int despuesCodigo = 0;

        for (int i = 0; i < inputString.length(); i++) {

            try {
                renglon = inputString.substring(inicio, corte);
                inicio = corte + 1;
                corte = inputString.indexOf("\n", corte + 1);
            } catch (StringIndexOutOfBoundsException e) {
                renglon = inputString.substring(inicio, inputString.length());
                i = inputString.length();
            }
            //System.out.println("renglon " + renglon);
            if(renglon.contains("RES010D")){
                despuesCodigo = renglon.indexOf(" ");
                //System.out.println("A veeer " + renglon.substring(despuesCodigo, renglon.length()));
                archivo.setRenglon1(renglon.substring(despuesCodigo, renglon.length()));

            }else if(renglon.contains("CTI000D")){
                despuesCodigo = renglon.indexOf(" ");
                //System.out.println("A veeer " + renglon.substring(despuesCodigo, renglon.length()));
                archivo.setRenglon2(renglon.substring(despuesCodigo, renglon.length()));

            }else if(renglon.contains("FAC040D")){
                despuesCodigo = renglon.indexOf(" ");
               // System.out.println("A veeer " + renglon.substring(despuesCodigo, renglon.length()));
                archivo.setRenglon3(renglon.substring(despuesCodigo, renglon.length()));

            }else if(renglon.contains("CLT000D")){
                despuesCodigo = renglon.indexOf(" ");
                functions.getCLT000DValues(renglon, billingDetail, despuesCodigo);
            }
        }

        //return inputString;
        //return archivo;
        return billingDetail;
    }

}
