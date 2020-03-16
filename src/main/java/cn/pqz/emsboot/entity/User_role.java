package cn.pqz.emsboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class User_role implements Serializable {
    @TableId(value = "ID" , type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private Integer rid;
}
