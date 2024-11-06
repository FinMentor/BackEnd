package com.example.springboot.example.controller;

import com.example.springboot.example.dto.ExampleDTO;
import com.example.springboot.example.dto.ExampleResponseDTO;
import com.example.springboot.example.service.ExampleService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/example")
@RequiredArgsConstructor
@Slf4j
public class ExampleController {
    private final ExampleService exampleService;

    @GetMapping("")
    public ResponseResult<ExampleResponseDTO> getExample(@RequestParam String example) {
        ExampleDTO exampleDTO = exampleService.getExample(example);

        if (log.isInfoEnabled()) {
            log.info("getListExample Controller Success : {}", exampleDTO.toString());
        }
        return ResponseResult.success(ExampleResponseDTO.of(exampleDTO));
    }
}
