package com.example.service;

import com.example.domain.Application;
import com.example.dto.ApplicationDTO.Request;
import com.example.dto.ApplicationDTO.Response;
import com.example.exception.BaseException;
import com.example.exception.ResultType;
import com.example.repository.ApplicationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

  private final ApplicationRepository applicationRepository;
  private final ModelMapper modelMapper;

  @Override
  public Response create(Request request) {
    Application application = modelMapper.map(request, Application.class);
    application.setAppliedAt(LocalDateTime.now());

    Application applied = applicationRepository.save(application);

    return modelMapper.map(applied, Response.class);
  }

  @Override
  public Response get(Long applicationId) {
    Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    return modelMapper.map(application, Response.class);
  }

  @Override
  public Response update(Long applicationId, Request request) {
    Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    application.setName(request.getName());
    application.setCellPhone(request.getCellPhone());
    application.setEmail(request.getEmail());
    application.setHopeAmount(request.getHopeAmount());

    applicationRepository.save(application);

    return modelMapper.map(application, Response.class);
  }

  @Override
  public void delete(Long applicationId) {
    Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    application.setIsDeleted(true);

    applicationRepository.save(application);
  }

}
