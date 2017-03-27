package com.ote.graph.controller;

import com.ote.graph.model.ShortestPathResult;
import com.ote.graph.service.IShortestPathComputingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graphs")
@Slf4j
public class GraphComputingController {

    @Autowired
    private IShortestPathComputingService shortestPathComputingService;

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public ShortestPathResult computeShortestPath(@RequestParam(name = "source") String source,
                                                  @RequestParam(name = "destination") String destination) {

        return shortestPathComputingService.findPath(source, destination);
    }
}
