package com.change.springboot;

import com.change.HippoSpringBootServerApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午2:28
 * 使用test rest template来模拟请求
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HippoSpringBootServerApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class UserControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }

    @Test
    public void testUserController() throws Exception {

    }

    @Test
    public void testUapLogin(){

    }
}
