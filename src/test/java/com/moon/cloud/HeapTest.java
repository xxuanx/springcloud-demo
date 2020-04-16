package com.moon.cloud;

import java.util.ArrayList;

public class HeapTest {

    public byte[] a = new byte[4096];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(5);
        }
    }
}
