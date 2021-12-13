package com.ServiceUtils;

import junit.framework.TestCase;

public class MessageServiceTest extends TestCase {
    public void testSendMessage() {
        String phone  = "18550626563";
        MessageService.sendMessage(phone);
    }
}