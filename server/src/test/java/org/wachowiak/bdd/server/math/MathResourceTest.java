package org.wachowiak.bdd.server.math;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.wachowiak.bdd.common.math.Constants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathResourceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addMethodAddTwoNumbers() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/{0}/{1}", ENDPOINT_MATH, ENDPOINT_ADD)
                        .param(PARAM_A, "2")
                        .param(PARAM_B, "3"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("5", result.getResponse().getContentAsString());
    }

    @Test
    public void subMethodSubtractsTwoNumbers() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/{0}/{1}", ENDPOINT_MATH, ENDPOINT_SUB)
                        .param(PARAM_A, "2")
                        .param(PARAM_B, "3"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("-1", result.getResponse().getContentAsString());
    }

    @Test
    public void mulMethodMultipliesTwoNumbers() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/{0}/{1}", ENDPOINT_MATH, ENDPOINT_MUL)
                        .param(PARAM_A, "2")
                        .param(PARAM_B, "3"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("6", result.getResponse().getContentAsString());
    }

    @Test
    public void divMethodDividesTwoNumbers() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/{0}/{1}", ENDPOINT_MATH, ENDPOINT_DIV)
                        .param(PARAM_A, "4")
                        .param(PARAM_B, "3"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("1", result.getResponse().getContentAsString());
    }

    @Test
    public void divMethodReturns400WhenDivBy0() throws Exception {

        mockMvc.perform(
                get("/{0}/{1}", ENDPOINT_MATH, ENDPOINT_DIV)
                        .param(PARAM_A, "4")
                        .param(PARAM_B, "0"))
                .andExpect(status().isBadRequest());
    }
}