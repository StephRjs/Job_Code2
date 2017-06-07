package com.example.usuario.job_code;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cesar on 25/5/2017.
 */
public class LoginTest {



    private Login mLogin;

    @Before
    public void setUp() throws Exception {

        mLogin = new Login();

        mLogin.setLoginStatus(true);
        //mInternship2.setDescription("paula");
    }

    @Test
    public void getLoginStatus() throws Exception {
        assertEquals(mLogin.getLoginStatus(), true);
    }

}