package com.balenko.vodafone.controller;

import com.balenko.vodafone.VodafoneApplication;
import com.balenko.vodafone.dto.MobileSubscriberDto;
import com.balenko.vodafone.dto.ResponseDto;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VodafoneApplication.class)
@WebAppConfiguration
public class MobileSubscriberControllerTest {

    @Mock
    private MobileSubscriberService mobileSubscriberServiceMock;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MobileSubscriberController mobileSubscriberController = new MobileSubscriberController(mobileSubscriberServiceMock);
        mockMvc = standaloneSetup(mobileSubscriberController).build();
    }

    private ResponseDto<List<MobileSubscriberDto>> createResponseDto(int id, String mobileNumber) {
        ResponseDto<List<MobileSubscriberDto>> responseDto = new ResponseDto<>();

        MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto(id, mobileNumber);
        responseDto.setData(Arrays.asList(mobileSubscriberDto));
        return responseDto;
    }

    @Test
    public void testGetAllMobileNumbers() throws Exception {
        ResponseDto<List<MobileSubscriberDto>> expected = createResponseDto(1, "+380991234567");
        when(mobileSubscriberServiceMock.getAllMobileNumbers()).thenReturn(expected.getData());

        mockMvc.perform(get("/api/mobiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].mobileNumber", is("+380991234567")));
    }

    @Test
    public void testGetSearchMobileNumbers() throws Exception {
        ResponseDto<List<MobileSubscriberDto>> expected = createResponseDto(1, "+380991234567");
        when(mobileSubscriberServiceMock.getSearchMobileNumbers("+380991234567")).thenReturn(expected.getData());

        mockMvc.perform(get("/api/mobiles/search?number=+380991234567"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].mobileNumber", is("+380991234567")));
    }

    @Test
    public void testAddMobileNumber() throws Exception {
        MobileSubscriberDto inputData = new MobileSubscriberDto();
        inputData.setMobileNumber("+380991234567");
        when(mobileSubscriberServiceMock.addMobileNumber(eq(inputData))).thenReturn(new MobileSubscriberDto(1, "+380991234567"));
        String request = "{\"mobileNumber\": \"+380991234567\"}";

        mockMvc.perform(post("/api/mobiles").contentType(MediaType.APPLICATION_JSON).content(request).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.mobileNumber", is("+380991234567")));
    }

    @Test
    public void testChangeMobileSubscriberData() throws Exception {
        MobileSubscriberDto inputData = new MobileSubscriberDto();
        inputData.setOwnerId(123);

        MobileSubscriberDto expected = new MobileSubscriberDto();
        expected.setId(1);
        expected.setOwnerId(123);
        when(mobileSubscriberServiceMock.changeMobileSubscriberData(1, inputData)).thenReturn(expected);
        String request = "{\"ownerId\": 123}";

        mockMvc.perform(put("/api/mobiles/1").contentType(MediaType.APPLICATION_JSON).content(request).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.ownerId", is(123)));
    }

    @Test
    public void testDeleteMobileNumber() throws Exception {
        mockMvc.perform(delete("/api/mobiles/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
