package cn.pqz.emsboot;

import cn.pqz.emsboot.mapper.*;
import cn.pqz.emsboot.service.warehouse.OrderService;
import cn.pqz.emsboot.util.OrderNumUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmsBootApplicationTests {
    @Autowired
   private ClientMapper clientMapper;
    @Autowired
    private OrderService orderService;
    @Test
    void test(){

    }

}
