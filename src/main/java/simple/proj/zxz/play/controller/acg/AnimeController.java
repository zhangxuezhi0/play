package simple.proj.zxz.play.controller.acg;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.proj.zxz.play.pojo.vo.acg.AnimeQueryInVO;
import simple.proj.zxz.play.pojo.vo.comm.CommOutVO;

/**
 * anime控制器
 *
 * @author zhangxz
 * 2019/10/25
 */

@Slf4j
@RestController
@RequestMapping("/v0.0.1/anime")
@Api(tags = "acg", description = "动漫")
public class AnimeController {


    @GetMapping
    @ApiOperation("获取列表")
    public CommOutVO getAnimeList(AnimeQueryInVO animeQueryInVO) {
        //TODO 这里应该研究下，如何使用aop去把所有接口的入参记载到日志里面
        log.info(JSON.toJSONString(animeQueryInVO, true));
        return CommOutVO.getSuccess();
    }


}
