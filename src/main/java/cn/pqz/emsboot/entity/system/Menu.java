package cn.pqz.emsboot.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {
    @TableId(value = "ID" , type = IdType.AUTO)
    private Integer id;
    private String pattern;
    private String path;
    private Integer level;
    private String name;
    private String icon;
    private Integer parentId;
    private Boolean enable;
    @TableField(exist = false)
    private List<Role> roles;
    @TableField(exist = false)
    private List<Menu> children;
}
