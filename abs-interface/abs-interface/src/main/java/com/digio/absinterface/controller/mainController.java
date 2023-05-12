package com.digio.absinterface.controller;

import com.digio.absinterface.service.LayerService;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/layer/digio")
public class mainController {


    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LayerService service;

    @GetMapping(value = "/get")
    public String fun(){

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        HashMap<String,String>signers=new HashMap<String, String>();
        signers.put("identifier","1234567890");
        signers.put("name","Sanket Nayak");
        signers.put("reason","Loan Agreement");

        ArrayList< HashMap<String,String>>ar=new ArrayList<>();
        ar.add(signers);
        params.put("signers", Collections.singletonList(ar));
        params.put("expire_in_days", Collections.singletonList(10));
        params.put("display_on_page", Collections.singletonList("all"));
        params.put("notify_signers", Collections.singletonList(true));
        Gson gson = new Gson();
        String json = gson.toJson(params);

        return json ;
    }

    @PostMapping (value = "/post")
    public
    @ResponseBody
      String handleFileUpload(@RequestBody  HashMap<String, Object> filedata,@RequestParam String filename, @RequestParam String identifier, @RequestParam String name, @RequestParam String reason, @RequestParam int expiredays, @RequestParam String display,@RequestParam boolean notify) throws Exception {

        return service.handleFileUpload(filedata,filename, identifier, name, reason, expiredays, display,notify);

    }

    @PostMapping(value = "/p")
    String fun(@RequestBody String s){return s;}

}

