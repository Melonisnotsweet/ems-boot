package cn.pqz.emsboot;

import cn.pqz.emsboot.entity.warehouse.Output;
import cn.pqz.emsboot.entity.warehouse.OutputLog;
import cn.pqz.emsboot.mapper.*;
import cn.pqz.emsboot.service.warehouse.OrderService;
import cn.pqz.emsboot.service.warehouse.OutputService;
import cn.pqz.emsboot.util.OrderNumUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

@SpringBootTest
class EmsBootApplicationTests {
    @Autowired
   private OutputMapper outputMapper;
    @Autowired
    private OutputService outputService;
    @Autowired
    private OutputLogMapper outputLogMapper;
    @Test
    void test(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("oid",3);
        queryWrapper.eq("orderNum","");
        queryWrapper.eq("orderName","茶叶");
        List<OutputLog> outputLogs=outputLogMapper.selectList(queryWrapper);
        for (OutputLog outputLog:outputLogs )
        {
            if (outputLog.getEndTime()==null)
            System.out.println(outputLog);
        }
    }

}
