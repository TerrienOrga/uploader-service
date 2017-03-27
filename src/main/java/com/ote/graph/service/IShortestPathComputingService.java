package com.ote.graph.service;

import com.ote.graph.model.ShortestPathResult;

public interface IShortestPathComputingService {

    ShortestPathResult findPath(String source, String destination);
}
