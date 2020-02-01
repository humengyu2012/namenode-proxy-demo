package com.hu.demo;

import java.util.Arrays;

public class DemoProtocolImpl implements DemoProtocol {

    @Override
    public int sum(int... arr) {
        return Arrays.stream(arr).sum();
    }
}
