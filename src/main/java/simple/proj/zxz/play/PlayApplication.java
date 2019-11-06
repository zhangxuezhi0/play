package simple.proj.zxz.play;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import simple.proj.zxz.play.prop.CommProp;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@Slf4j
public class PlayApplication {

    @Autowired
    private CommProp commProp;

    @PostConstruct
    void started() {
        //时区设置：中国上海
        TimeZone.setDefault(TimeZone.getTimeZone(commProp.getTimeZone()));
    }

    public static void main(String[] args) {
        log.info("*** starting application PLAY ***");
        SpringApplication.run(PlayApplication.class, args);
        log.info("*** application PLAY started ***");
    }

}
