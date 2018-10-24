package com.johnsonyoung630.hanlpext.io;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-04-10.
 */
public class HDFSIOAdapterTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void open() throws Exception {
        String test = "姚明当选新一届中国篮协主席。";
        List<Term> termList = HanLP.segment(test);
        System.out.println(termList);
    }

    @Test
    public void create() throws Exception {
    }

}