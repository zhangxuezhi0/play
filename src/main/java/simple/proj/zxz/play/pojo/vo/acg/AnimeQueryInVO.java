package simple.proj.zxz.play.pojo.vo.acg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * acg 入参vo
 *
 * @author zhangxz
 * 2019/10/25
 */

@Data
@ApiModel("动漫查询入参")
public class AnimeQueryInVO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;


}
