package com.car.yeloAssesment.controllers;

import com.car.yeloAssesment.models.Car;
import com.car.yeloAssesment.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/{id}")
    public Car getCarById(@PathVariable long id){
        return this.carService.getCarById(id);
    }

    @GetMapping("")
    public List<Car> getAllCars(){
        return this.carService.findAll();
    }

    @DeleteMapping("/{id}/delete")
    public void deleteCar(@PathVariable long id){
        this.carService.deleteCarById(id);
    }

    @PostMapping("")
    public ResponseEntity createCar(@Valid @RequestBody Car car){
        Car newCar = new Car(car.getName(),car.getColor(),car.getModel(),car.getOwner(),car.isHideMe());
        Car savedCar = this.carService.saveCar(newCar);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @PutMapping("/{id}")
    public Car updateCar(@Valid @RequestBody Car car, @PathVariable long id){
        Car updatedCar = this.carService.updateCar(car,id);
        return updatedCar;
    }

    @GetMapping("/{name}/searchName")
    public List<Car> getCarByName(@PathVariable String name){
        List<Car> cars = this.carService.getCarByName(name);
        return cars;
    }

    @GetMapping("/{owner}/searchOwner")
    public List<Car> getCarByOwner(@PathVariable String owner){
        List<Car> cars = this.carService.getCarByOwner(owner);
        return cars;
    }

    @GetMapping("/{name}/search")
    public List<Car> searchByNameOrOwner(@PathVariable String name){
        List<Car> cars = this.carService.search(name);
        return cars;
    }
}
