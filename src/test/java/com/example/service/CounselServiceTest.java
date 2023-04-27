package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.domain.Counsel;
import com.example.dto.CounselDTO.Request;
import com.example.dto.CounselDTO.Response;
import com.example.exception.BaseException;
import com.example.exception.ResultType;
import com.example.repository.CounselRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CounselServiceTest {

  @InjectMocks
  CounselServiceImpl counselService;

  @Mock
  private CounselRepository counselRepository;

  @Spy
  private ModelMapper modelMapper;

  @Test
  void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
    Counsel entity = Counsel.builder()
        .name("Member Kim")
        .cellPhone("010-1111-2222")
        .email("mail@abc.de")
        .memo("I hope to get a loan")
        .zipCode("123456")
        .address("Somewhere in Gangnam-gu, Seoul")
        .addressDetail("What Apartment No. 101, 1st floor No. 101")
        .build();

    Request request = Request.builder()
        .name("Member Kim")
        .cellPhone("010-1111-2222")
        .email("mail@abc.de")
        .memo("I hope to get a loan")
        .zipCode("123456")
        .address("Somewhere in Gangnam-gu, Seoul")
        .addressDetail("What Apartment No. 101, 1st floor No. 101")
        .build();

    when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

    Response actual = counselService.create(request);

    assertThat(actual.getName()).isSameAs(entity.getName());
  }

  @Test
  void Should_ReturnResponseOfExistCounselEntity_When_RequestExistCounselId() {
    Long findId = 1L;

    Counsel entity = Counsel.builder()
        .counselId(1L)
        .build();

    when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

    Response actual = counselService.get(1L);

    assertThat(actual.getCounselId()).isSameAs(findId);
  }

  @Test
  void Should_ThrowException_When_RequestNotExistCounselId() {
    Long findId = 2L;

    when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

    Assertions.assertThrows(BaseException.class, () -> counselService.get(2L));
  }

  @Test
  void Should_ReturnUpdatedResponseOfExistCounselEntity_When_RequestUpdateExistCounselInfo() {
    Long findId = 1L;

    Counsel entity = Counsel.builder()
        .counselId(1L)
        .name("Member Kim")
        .build();

    Request request = Request.builder()
        .name("Member Kang")
        .build();

    when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);
    when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

    Response actual = counselService.update(findId, request);

    assertThat(actual.getCounselId()).isSameAs(findId);
    assertThat(actual.getName()).isSameAs(request.getName());
  }

}
