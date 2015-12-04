package com.betvictor.data;

import com.betvictor.ActionMonitorApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ActionMonitorApplication.class)
@WebAppConfiguration
public class EmployeeTests {

    private Employee employee;


    @Test
    public void testEmployees() throws Exception {
        employee = new Employee(1L, "bet", "victor", "bet@victor.com");

        Assert.assertEquals(employee.getId(), new Long(1));
        Assert.assertEquals(employee.getFirstName(), "bet");
        Assert.assertEquals(employee.getLastName(), "victor");
        Assert.assertEquals(employee.getEmail(), "bet@victor.com");
    }

}