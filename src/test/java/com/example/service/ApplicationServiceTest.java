package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.domain.Application;
import com.example.dto.ApplicationDTO.Request;
import com.example.dto.ApplicationDTO.Response;
import com.example.repository.ApplicationRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

  @InjectMocks
  ApplicationServiceImpl applicationService;

  @Mock
  private ApplicationRepository applicationRepository;

  @Test
  void Should_ReturnResponseOfNewApplyEntity_When_RequestApply() {
    Application entity = Application.builder()
        .name("Member Kim")
        .cellPhone("010-1111-2222")
        .email("mail@abc.de")
        .hopeAmount(BigDecimal.valueOf(50000000))
        .build();

    Request request = Request.builder()
        .name("Member Kim")
        .cellPhone("010-1111-2222")
        .email("mail@abc.de")
        .hopeAmount(BigDecimal.valueOf(50000000))
        .build();

    when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);

    Response actual = applicationService.create(request);

    assertThat(actual.getName()).isSameAs(entity.getName());
  }

  @Test
  void Should_ReturnResponseOfExistApplicationEntity_When_RequestExistApplicationId() {
    Long findId = 1L;

    Application entity = Application.builder()
        .applicationId(1L)
        .build();

    when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

    Response actual = applicationService.get(1L);

    assertThat(actual.getApplicationId()).isSameAs(findId);
  }

}