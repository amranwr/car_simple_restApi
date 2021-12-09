package com.car.yeloAssesment.controllers;

import com.car.yeloAssesment.exceptions.NotFoundException;
import com.car.yeloAssesment.models.Car;
import com.car.yeloAssesment.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    @Mock
    private CarService carService;
    @InjectMocks
    private CarController carController;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    void carNotFound() throws Exception {
        when(carService.getCarById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/car/5"))
                .andExpect(status().isNotFound());
        verify(carService).getCarById(anyLong());
    }

    @Test
    void getCarById() throws Exception {
        Car car = new Car("amr","amr","amr","amr",false);
        car.setId(1);
        when(carService.getCarById(anyLong())).thenReturn(car);
        mockMvc.perform(get("/car/5"))
                .andExpect(status().isOk());
        verify(carService).getCarById(anyLong());
    }



    @Test
    void addNewCar() throws Exception {
        Car car = new Car("amr","amr","amr","amr",false);
        car.setId(1);
        when(carService.saveCar(any())).thenReturn(car);
        String in = "{\n" +
                "        \"name\": \"cerato\",\n" +
                "        \"color\": \"red\",\n" +
                "        \"model\": \"kia\",\n" +
                "        \"owner\": \"amr\"\n" +
                "}";
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content(in)
                        .param("car",""))
                .andExpect(status().isCreated());
        verify(carService).saveCar(any());
    }

    @Test
    void badReqExcAddNewCar() throws Exception {
        mockMvc.perform(post("/car").contentType(MediaType.APPLICATION_JSON).content("")
                        .param("car",""))
                .andExpect(status().isBadRequest());
    }

}