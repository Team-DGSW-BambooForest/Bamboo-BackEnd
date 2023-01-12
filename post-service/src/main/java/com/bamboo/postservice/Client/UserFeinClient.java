package com.bamboo.postservice.Client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user", url = "")
public class UserFeinClient {

}
