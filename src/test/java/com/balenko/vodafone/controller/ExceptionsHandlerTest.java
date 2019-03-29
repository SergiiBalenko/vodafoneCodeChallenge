package com.balenko.vodafone.controller;

import com.balenko.vodafone.VodafoneApplication;
import com.balenko.vodafone.dto.MobileSubscriberDto;
import com.balenko.vodafone.exceptions.ResourceNotFoundException;
import com.balenko.vodafone.exceptions.ResourceNotUniqueException;
import com.balenko.vodafone.service.MobileSubscriberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VodafoneApplication.class)
@WebAppConfiguration
public class ExceptionsHandlerTest {

    @Mock
    private MobileSubscriberService mobileSubscriberServiceMock;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MobileSubscriberController mobileSubscriberController = new MobileSubscriberController(mobileSubscriberServiceMock);
        mockMvc = standaloneSetup(mobileSubscriberController).setControllerAdvice(new ExceptionsHandler()).build();
    }

    @Test
    public void testResourceNotUniqueException() throws Exception {
        MobileSubscriberDto inputData = new MobileSubscriberDto();
        inputData.setMobileNumber("+380991234567");
        when(mobileSubscriberServiceMock.addMobileNumber(inputData)).thenThrow(new ResourceNotUniqueException("Mobile number is not unique"));
        String request = "{\"mobileNumber\": \"+380991234567\"}";

        mockMvc.perform(post("/api/mobiles").contentType(MediaType.APPLICATION_JSON).content(request).characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error.message", is("Mobile number is not unique")));
    }

    @Test
    public void testChangeMobileSubscriberData() throws Exception {
        MobileSubscriberDto inputData = new MobileSubscriberDto();
        inputData.setOwnerId(123);

        when(mobileSubscriberServiceMock.changeMobileSubscriberData(1, inputData)).thenThrow(new ResourceNotFoundException("Mobile number is not found"));
        String request = "{\"ownerId\": 123}";

        mockMvc.perform(put("/api/mobiles/1").contentType(MediaType.APPLICATION_JSON).content(request).characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error.message", is("Mobile number is not found")));
    }

    @Test
    public void testServerError() throws Exception {
        MobileSubscriberDto inputData = new MobileSubscriberDto();
        inputData.setMobileNumber("+380991234567");
        when(mobileSubscriberServiceMock.addMobileNumber(inputData)).thenThrow(new RuntimeException("Server Error"));
        String request = "{\"mobileNumber\": \"+380991234567\"}";

        mockMvc.perform(post("/api/mobiles").contentType(MediaType.APPLICATION_JSON).content(request).characterEncoding("utf-8"))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.error.message", is("Server Error")));
    }
}
