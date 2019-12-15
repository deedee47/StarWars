package com.deedee.fingertips.deestarwars.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IHttpService {

    ResponseEntity get(String endpointUrl, Class resultClass);

}
