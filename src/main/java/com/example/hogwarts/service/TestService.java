package com.example.hogwarts.service;

import lombok.var;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestService {
    int sum = Stream.iterate(1, a -> a +1)
            .limit(1_000_000)
            .reduce(0, (a, b) -> a + b );
      var b= IntStream.rangeClosed(1,1_000_000).parallel().sum();
}
