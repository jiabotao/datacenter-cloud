package org.datacenter.kafka.manager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/offset")
public class OffSetController {

    @PostMapping("/getOffSetDetail")
    public void getOffSetDetail(String topic){

    }
}
