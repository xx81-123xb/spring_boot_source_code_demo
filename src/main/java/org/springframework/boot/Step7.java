package org.springframework.boot;

import com.dupenghao.util.MapUtil;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

//输出banner信息
public class Step7 {
    public static void main(String[] args) {
        ApplicationEnvironment env = new ApplicationEnvironment();
        SpringApplicationBannerPrinter printer = new SpringApplicationBannerPrinter(
                new DefaultResourceLoader(),
                new SpringBootBanner()
        );
        // 测试文字 banner
//        env.getPropertySources().addLast(new MapPropertySource("custom", MapUtil.of("spring.banner.location","banner1.txt")));
        // 测试图片 banner
        env.getPropertySources().addLast(new MapPropertySource("custom", MapUtil.of("spring.banner.image.location","test.png")));
        // 版本号的获取
//        System.out.println(SpringBootVersion.getVersion());
        printer.print(env, Step7.class, System.out);
    }
}
