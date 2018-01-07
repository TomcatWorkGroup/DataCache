package com.itdreamworks.datacacheoutput.receiver;

import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class BaseReceiver {
    static Charset CHAR_SET = Charset.forName("utf-8");
}
