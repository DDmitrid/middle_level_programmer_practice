package com.globallogic;

import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class PhysicallyAvailableMemory {

    @Test
    public void getPhysicallyAvailableMemory() {
        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
                java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        long physicalMemorySize = os.getTotalPhysicalMemorySize();
        System.out.println("physicalMemorySize = " + physicalMemorySize + " bytes");
        //7766491136
    }


    @Test
    public void getPhysicallyAvailableMemoryAnotherWay() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        Object TotalPhysicalMemorySize = mBeanServer.getAttribute(new ObjectName("java.lang", "type", "OperatingSystem"), "TotalPhysicalMemorySize");
        System.out.println("anoterh way physicalMemorySize =" + TotalPhysicalMemorySize.toString() + " bytes");
        //7766491136
    }

}
