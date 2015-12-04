package com.betvictor.controller;

import com.betvictor.ActionMonitorApplication;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ActionMonitorApplication.class)
@WebAppConfiguration
public class EmployeeControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testShowEmployees() throws Exception {

        String uri = "/";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertNotNull(result.getModelAndView().getModel().get("employees"));
        Assert.assertEquals(result.getModelAndView().getViewName(), "home");
        Assert.assertEquals("failure - expected HTTP status", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
    }

    @Test
    public void testDeleteEmployee() throws Exception {

        String uri = "/employee/delete/1";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status", 200, status);
        Assert.assertEquals("response is 'deleted'", content, "deleted");
    }

//        @Test
//        public void testAddEmployee() throws Exception {
//
//            String uri = "/employee/add";
//
//            String addContent = new JSONObject().put("id", "10")
//                    .put("firstName", "name")
//                    .put("lastName", "name")
//                    .put("email", "email").toString();
//
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(addContent).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
//
//            String content = result.getResponse().getContentAsString();
//            int status = result.getResponse().getStatus();
//
//            Assert.assertEquals("failure - expected HTTP status", 200, status);
//            Assert.assertNotNull(content);
//        }

    @Test
    public void testUpdateEmployee() throws Exception {

        String uri = "/employee/update/1";

        String updateContent = new JSONObject().put("id", 1)
                .put("firstName", "name")
                .put("lastName", "name")
                .put("email", "email").toString();


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(updateContent).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status", 200, status);
        Assert.assertNotNull(content);
    }
}
