package cn.edu.ctbu.sbadmin.lablog.service;

import cn.edu.ctbu.sbadmin.lablog.domain.DimTermDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DimTermServiceTest {

    @Autowired
    private DimTermService dimTermService;

    @Test
    public void GetAll(){
        List<DimTermDO> dimTermDOS=dimTermService.findAll();

        System.out.println(dimTermDOS);
    }

}