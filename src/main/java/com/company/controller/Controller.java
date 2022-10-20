package com.company.controller;

import com.company.dto.CounterResponseDto;
import com.company.dto.TextToCountDto;
import com.company.services.WordCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final String GREETING_MESSAGE = "hello world";

    @Autowired
    WordCounterService counterService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>(GREETING_MESSAGE, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CounterResponseDto> print(@RequestBody TextToCountDto textToCountDto) {
        return new ResponseEntity<>(counterService.calculate(textToCountDto), HttpStatus.OK);
    }
}