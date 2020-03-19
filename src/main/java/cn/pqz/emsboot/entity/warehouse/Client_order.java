package cn.pqz.emsboot.entity.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Client_order implements Serializable {
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;
    private Integer cid;
    private Integer oid;
}
