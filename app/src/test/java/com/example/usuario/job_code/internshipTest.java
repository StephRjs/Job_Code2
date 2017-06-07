package com.example.usuario.job_code;

import org.junit.Before;
import org.junit.Test;
import android.app.Activity;
import android.widget.Button;
import com.example.usuario.job_code.internship;
import org.mockito.Mock;

import static org.junit.Assert.*;
import org.hamcrest.*;

/**
 * Created by cesar on 24/5/2017.
 */
public class internshipTest {


    private internship mInternship;
    private internship mInternship2;

    @Before
    public void setUp() throws Exception {

        mInternship = new internship();

        mInternship2 = new internship();

        mInternship.setCompanyName("paula");

        //mInternship2.setDescription("paula");
    }

    @Test
    public void testCompanyName() throws Exception {
        assertEquals(mInternship.getCompanyName(), "paula");
    }


    public void testDescription() throws Exception {
        assertEquals(mInternship2.getDescription(), "paula");
    }

}