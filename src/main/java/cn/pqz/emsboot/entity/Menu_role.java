package cn.pqz.emsboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Menu_role {
    @TableId(value = "ID" , type = IdType.AUTO)
    private Integer id;
    private Integer rid;
    private Integer mid;
}
