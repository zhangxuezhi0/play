package simple.proj.zxz.play.pojo.vo.acg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * anime出参vo
 *
 * @author zhangxz
 * 2019/10/25
 */

@Data
@ApiModel("动漫查询出参")
public class AnimeQueryOutVO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("导演")
    private String director;

    @ApiModelProperty("上映时间")
    private Date releaseTime;

}
