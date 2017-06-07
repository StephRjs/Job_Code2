package com.example.usuario.job_code;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cesar on 25/5/2017.
 */
public class ServiceTest {

    private Service mService;

    @Before
    public void setUp() throws Exception {

        mService = new Service();

        mService.setCompanyName("paula");

        //mInternship2.setDescription("paula");
    }

    @Test
    public void testCompanyName() throws Exception {
        assertEquals(mService.getCompanyName(), "paula");
    }

}