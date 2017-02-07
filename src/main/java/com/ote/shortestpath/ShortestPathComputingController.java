package com.ote.shortestpath;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/trigger")
@Slf4j
public class ShortestPathComputingController {

    @RequestMapping(value = "/shortest-path", method = RequestMethod.POST)
    public ResponseEntity computeShortestPath(){



        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
