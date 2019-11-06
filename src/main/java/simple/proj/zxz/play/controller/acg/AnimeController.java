package simple.proj.zxz.play.controller.acg;

import com.alibaba.fastjson.JSON;
import first.zxz.tools.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.proj.zxz.play.pojo.vo.acg.AnimeQueryInVO;
import simple.proj.zxz.play.pojo.vo.acg.AnimeQueryOutVO;
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


    /**
     * 获取动漫列表
     *
     * @param animeQueryInVO 请求参数
     * @return simple.proj.zxz.play.pojo.vo.comm.CommOutVO
     * @author Zxz
     * @date 2019/11/6 12:14
     **/
    @GetMapping
    @ApiOperation("获取列表")
    public CommOutVO<AnimeQueryOutVO> getAnimeList(AnimeQueryInVO animeQueryInVO) {
        //TODO 这里应该研究下，如何使用aop去把所有接口的入参记载到日志里面

        log.info(JSON.toJSONString(animeQueryInVO, true));

        AnimeQueryOutVO animeQueryOutVO = new AnimeQueryOutVO();
        animeQueryOutVO.setDirector("宫崎骏");
        animeQueryOutVO.setName("千与千寻");
        animeQueryOutVO.setReleaseTime(DateUtil.newDate("2001-07-20"));
        return new CommOutVO<>(animeQueryOutVO);

    }


}
