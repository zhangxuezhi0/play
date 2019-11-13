package simple.proj.zxz.play.controller.acg;

import first.zxz.tools.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import simple.proj.zxz.play.exception.BusinessException;
import simple.proj.zxz.play.pojo.vo.acg.AnimeAddInVO;
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

        AnimeQueryOutVO animeQueryOutVO = new AnimeQueryOutVO();
        animeQueryOutVO.setDirector("宫崎骏");
        animeQueryOutVO.setName("千与千寻");
        animeQueryOutVO.setReleaseTime(DateUtil.parse("2001-07-20"));
        return new CommOutVO<>(animeQueryOutVO);

    }

    @PostMapping
    @ApiOperation("新增")
    public CommOutVO addAnime(@RequestBody @Validated AnimeAddInVO animeAddInVO) {

        //TODO 业务逻辑待增加

        //测试
        if (true) {
            throw new BusinessException();
        }
        return CommOutVO.getSuccess();

    }

}
