package com.globallogic;

import org.junit.Test;

public class MaxSizeOfSingleBlockAvailableInHeapToAllocate {
// -Xmx2048M -Xms2048M
// -Xmx500M -Xms500M

    @Test
    public void getMaxSizeOfSingleBlockAvailableInHeapToAllocate() {
        int[] array = new int[1];
        int step1Kb = 256;// 1byte = 1024
        int step1Mb = 262144;//1MegaByte = 1_048_576;
        int step10Mb = 2621440;
        int step100Mb = 26214400;
        int step1Gb = 268435456;//1Gbyte = 1_073_741_824;

        try {
            while (true) {
                array = new int[array.length + step10Mb];
            }
        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("MaxSizeOfSingleBlockAvailableInHeapToAllocate = " + array.length * 4 + " bytes");
        }
//        650_117_124 bytes

    }

}
