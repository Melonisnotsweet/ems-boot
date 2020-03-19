package cn.pqz.emsboot.entity.warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderList implements Serializable {
    @TableId(value = "ID" , type = IdType.AUTO)
    private Integer id;
    private String name;
    private String orderNum;//订单号
    private Double price;//价格
    private Integer count;//数量
    private Boolean pay;//是否支付
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;//时间
    private Boolean transport;//是否发货
    private Integer orderState;//订单状态
}
