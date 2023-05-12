package com.digio.absinterface.service;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@Service
public class LayerService {
    String baseUrl="https://ext.digio.in:444";
    String Client_id="AIZ67DUSNZ8TGWJV4DZ7DI3T5Z2LN2W2";
    String Client_secret="ASN9AVKHU6HF41KTU71G3KNXLG1ET7BC";
    public String handleFileUpload(HashMap<String, Object> filedata,String filename, String identifier, String name, String reason, int expiredays, String display,boolean notify) {

        final String uri = baseUrl + "/v2/client/document/upload";

        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, Object> params = new HashMap<>();

//        params.add("file", file);
        HashMap<String,String> signers=new HashMap<String, String>();
        signers.put("identifier",identifier);
        signers.put("name",name);
        signers.put("reason",reason);

        ArrayList< HashMap<String,String>> ar=new ArrayList<>();
        ar.add(signers);
        params.put("signers", ar);
        params.put("expire_in_days", expiredays);
        params.put("display_on_page",display);
//        params.put("notify_signers", notify);
        params.put("file_name", filename);
        params.put("file_data",filedata.get("filedata"));


        Gson gson = new Gson();
        String json = gson.toJson(params);


        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization","Basic  Base64encodedValueOf("+Client_id+":"+Client_secret+")");
        System.err.println(json.toString());

        String response = restTemplate.postForObject(uri, new HttpEntity<String>(json.toString(), headers),String.class);

        return response;
    }

//    private RestTemplate getMappedRestTemplate(){
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ObjectMapper newObjectMapper = new ObjectMapper();
//
//        newObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
//
//        newObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//
//        MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter=new MappingJackson2HttpMessageConverter();
//
//        FormHttpMessageConverter formConvertor = new FormHttpMessageConverter();
//
//        restTemplate.getMessageConverters().add(formConvertor);
//
//        restTemplate.getMessageConverters().add(mappingJacksonHttpMessageConverter);
//
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//
//        return restTemplate;
//    }

//    public ResponseEntity<byte[]>handleFileUpload2()

}

