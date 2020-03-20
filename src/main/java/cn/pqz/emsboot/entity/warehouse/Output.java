package cn.pqz.emsboot.entity.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Output implements Serializable {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer total;
    private Double complete;
    private String note;
    private Boolean state;
    private Double percentage;
    private String orderNum;
    private String orderName;
}
