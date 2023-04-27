package com.example.service;

import com.example.dto.TermsDTO.Request;
import com.example.dto.TermsDTO.Response;
import java.util.List;

public interface TermsService {

  Response create(Request request);

  List<Response> getAll();
}
