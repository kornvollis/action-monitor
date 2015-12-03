package com.betvictor.controller;


    import com.betvictor.ActionMonitorApplication;
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

/**
     * Unit tests for the GreetingController using Spring MVC Mocks.
     *
     * These tests utilize the Spring MVC Mock objects to simulate sending actual
     * HTTP requests to the Controller component. This test ensures that the
     * RequestMappings are configured correctly. Also, these tests ensure that the
     * request and response bodies are serialized as expected.
     *
     * @author Matt Warman
     */
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
        public void testGetGreetings() throws Exception {

            String uri = "/";

            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

            String content = result.getResponse().getContentAsString();
            int status = result.getResponse().getStatus();

            Assert.assertEquals("failure - expected HTTP status", 200, status);
            Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
        }

}
