package com.dupenghao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 杜鹏豪 on 2022/8/25.
 */
@Component("dupenghao")
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "dph")
@Data
public class Person {

    private String name;
    private int age;
    private String address;


}
