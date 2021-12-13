package com.ServiceUtils;

import junit.framework.TestCase;

public class MessageServiceTest extends TestCase {
    public void testSendMessage() {
        String[] params ={ "322980"};
        String phone  = "15061110469";
        MessageService.sendMessage(params,phone);
    }
}