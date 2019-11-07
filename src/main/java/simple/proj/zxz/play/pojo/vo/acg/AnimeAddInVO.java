package simple.proj.zxz.play.pojo.vo.acg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * anime新增入参vo
 *
 * @author zhangxz
 * 2019/10/25
 */

@Data
@ApiModel("动漫新增入参")
public class AnimeAddInVO {

    @ApiModelProperty(value = "名称", required = true)
    @NotEmpty(message = "名称不能为空")
    private String name;

    @ApiModelProperty("导演")
    private String director;

    @ApiModelProperty("上映时间")
    private Date releaseTime;

}
