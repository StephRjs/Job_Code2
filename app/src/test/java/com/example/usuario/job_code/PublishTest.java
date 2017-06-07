package com.example.usuario.job_code;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cesar on 25/5/2017.
 */
public class PublishTest {

    private Publish mPublish;

    @Before
    public void setUp() throws Exception {

        mPublish = new Publish();

        mPublish.setCompany("paula");

        //mInternship2.setDescription("paula");
    }

    @Test
    public void getCompany() throws Exception {
        assertEquals(mPublish.getCompany(), "paula");
    }

}