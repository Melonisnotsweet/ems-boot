package cn.pqz.emsboot.entity.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Client implements Serializable {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private String note;
    private Boolean enabled;
    @TableField(exist = false)
    List<OrderList> orders;
}
