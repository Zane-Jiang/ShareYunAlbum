package com.ServiceUtils.MessageService;

import junit.framework.TestCase;
import org.junit.Test;

public class AutoCodeImplTest extends TestCase {

    @Test
    public void testCreate(){
        AutoCodeImpl autoCode = new AutoCodeImpl();
        System.out.println(autoCode.create("18550626563").getCode());
    }


    public void testCheck(){
        AutoCodeImpl autoCode = new AutoCodeImpl();
        autoCode.find("18550626563");
    }



}