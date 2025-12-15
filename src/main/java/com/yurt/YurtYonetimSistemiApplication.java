package com.yurt;

import com.yurt.bildirim.AbstractBildirim;
import com.yurt.bildirim.UygulamaBaslatildiBildirim;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YurtYonetimSistemiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YurtYonetimSistemiApplication.class, args);
    }

    @PostConstruct
    public void uygulamaBasladi() {
        AbstractBildirim bildirim =
                new UygulamaBaslatildiBildirim();
        bildirim.gonder();
    }
}
