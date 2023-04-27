package com.example.controller;

import com.example.dto.CounselDTO.Request;
import com.example.dto.CounselDTO.Response;
import com.example.dto.ResponseDTO;
import com.example.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/counsels")
public class CounselController extends AbstractController {

  private final CounselService counselService;

  @PostMapping
  public ResponseDTO<Response> create(@RequestBody Request request) {
    return ok(counselService.create(request));
  }

  @GetMapping("/{counselId}")
  public ResponseDTO<Response> get(@PathVariable Long counselId) {
    return ok(counselService.get(counselId));
  }

  @PutMapping("/{counselId}")
  public ResponseDTO<Response> update(@PathVariable Long counselId, @RequestBody Request request) {
    return ok(counselService.update(counselId, request));
  }
}
