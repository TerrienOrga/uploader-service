package com.ote.graph.service;

import java.io.BufferedReader;

public interface IFileIntegrationService {

    long persist(BufferedReader fileReader);
}
