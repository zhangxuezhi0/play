package simple.proj.zxz.play.controller.test;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.proj.zxz.play.pojo.vo.acg.AnimeQueryInVO;
import simple.proj.zxz.play.pojo.vo.comm.CommOutVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 测试控制器
 *
 * @author zhangxz
 * 2019/10/25
 */

@Slf4j
@RestController
@RequestMapping("/v0.0.1/test")
@Api(tags = "test", description = "测试")
public class TestController {


    @GetMapping
    @ApiOperation("获取列表")
    public CommOutVO getAnimeList(AnimeQueryInVO animeQueryInVO) {
        return CommOutVO.getSuccess();
    }

    @GetMapping("/OutOfMemoryError")
    @ApiOperation("测试：OutOfMemoryError: GC overhead limit exceeded")
    public CommOutVO<List<HashMap<String, String>>> testOutOfMemoryError() {

        return new CommOutVO<>(new ArrayList<>());
    }


}
