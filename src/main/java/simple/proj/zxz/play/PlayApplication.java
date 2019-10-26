package simple.proj.zxz.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class PlayApplication {

    /*@PostConstruct
    void started() {
        //时区设置：中国上海
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }*/

    public static void main(String[] args) {
        SpringApplication.run(PlayApplication.class, args);
    }

}
