package com.dupenghao;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by 杜鹏豪 on 2022/12/21.
 */
public class TempTest {

    public static void main(String[] args) {

        String path=System.getProperty("user.dir")+"/tomcat/";
        FileSystemResource resource = new FileSystemResource(path);
        System.out.println(resource.exists());
    }

}
