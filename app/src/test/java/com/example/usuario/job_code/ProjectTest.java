package com.example.usuario.job_code;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cesar on 25/5/2017.
 */
public class ProjectTest {

    private Project mProject;

    @Before
    public void setUp() throws Exception {

        mProject = new Project();

        mProject.setCompanyName("paula");

        //mInternship2.setDescription("paula");
    }

    @Test
    public void testCompanyName() throws Exception {
        assertEquals(mProject.getCompanyName(), "paula");
    }
}