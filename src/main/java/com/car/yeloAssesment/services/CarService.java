package com.car.yeloAssesment.services;

import com.car.yeloAssesment.exceptions.NotFoundException;
import com.car.yeloAssesment.models.Car;
import com.car.yeloAssesment.repositories.CarRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarService {
    private final CarRepo carRepo;
    private final static Pageable pageable = PageRequest.of(0,100,Sort.Direction.ASC,"name","model");
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public List<Car> findAll(){
        List<Car> cars = new ArrayList<>();
        carRepo.findAll(pageable)
                .iterator()
                .forEachRemaining(cars::add);
        return cars;
    }

    public Car getCarById(long id){
        Optional<Car> carOptional = this.carRepo.findById(id);
        if(carOptional.isPresent()) return carOptional.get();
        else throw new NotFoundException("car not found id : "+ id);
    }

    public Car saveCar(Car car){
        return this.carRepo.save(car);
    }

    public Car updateCar(Car updatedCar , long id){
        Optional<Car> car = this.carRepo.findById(id);
        if(car.isPresent()){
            Car originalCar = car.get();
            originalCar.setModel(updatedCar.getModel());
            originalCar.setName(updatedCar.getName());
            originalCar.setColor(updatedCar.getColor());
            originalCar.setHideMe(updatedCar.isHideMe());
            originalCar.setOwner(updatedCar.getOwner());
            return this.carRepo.save(originalCar);
        }else {
            throw new NotFoundException("car not found id : "+ id);
        }
    }

    public void deleteCarById(long id){
        this.carRepo.deleteById(id);
    }

    public List<Car> getCarByName(String name){
        List<Car> cars = new ArrayList<>();
        this.carRepo.findByName(name, pageable)
            .iterator()
            .forEachRemaining(cars::add);
        return cars;
     }
    public List<Car> getCarByOwner(String owner){
        List<Car> cars = new ArrayList<>();
        this.carRepo.findByOwner(owner, pageable)
                .iterator()
                .forEachRemaining(cars::add);
        return cars;
    }

    public List<Car> search(String name){
        List<Car> cars = new ArrayList<>();
        this.carRepo.findByNameOrOwner(name,pageable)
                .iterator()
                .forEachRemaining(cars::add);
        return cars;
    }
}
