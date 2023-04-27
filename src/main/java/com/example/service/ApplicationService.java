package com.example.service;

import com.example.dto.ApplicationDTO.Request;
import com.example.dto.ApplicationDTO.Response;

public interface ApplicationService {

  Response create(Request request);

  Response get(Long applicationId);

  Response update(Long applicationId, Request request);

  void delete(Long applicationId);

}
