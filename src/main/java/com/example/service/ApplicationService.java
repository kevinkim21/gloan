package com.example.service;

import com.example.dto.ApplicationDTO.Request;
import com.example.dto.ApplicationDTO.Response;

public interface ApplicationService {

  Response create(Request request);

  Response get(Long applicationId);

}
