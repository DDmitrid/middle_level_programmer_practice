package com.globallogic;

import org.junit.Test;

public class AmountOfAvailableMemoryToAllocate {

    @Test
    public void getAmountOfAvailableMemoryToAllocate() {
        System.out.println("The maximum amount of memory that the Java virtual machine will attempt to use.\n "
                + Runtime.getRuntime().maxMemory() + " bytes");
        //1726480384 bytes
    }

}
