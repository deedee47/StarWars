package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.config.PropsConfig;
import com.deedee.fingertips.deestarwars.interfaces.IHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class HttpService implements IHttpService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PropsConfig propsConfig;

    @Override
    public ResponseEntity get(String endpointUrl, Class resultClass)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", propsConfig.headerValue);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity result = restTemplate.exchange(endpointUrl, HttpMethod.GET,  entity, resultClass);

        return result;
    }
}
