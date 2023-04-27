package com.example.service;

import com.example.dto.CounselDTO.Request;
import com.example.dto.CounselDTO.Response;

public interface CounselService {
  Response create(Request request);
}
