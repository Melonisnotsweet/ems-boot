package cn.pqz.emsboot.entity.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Transition {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String orderNum;
    private String name;
    private Integer plan;
    private Double complete;
    private Integer outputId;
}
