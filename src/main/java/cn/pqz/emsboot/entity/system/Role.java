package cn.pqz.emsboot.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String nameZh;
    private String remark;
    @TableField(exist = false)
    private List<Menu> menus;
}