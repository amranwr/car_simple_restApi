package com.car.yeloAssesment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@ApiModel
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @NotBlank
    @ApiModelProperty(value = "you can't leave the name property blank")
    private String name;
    @NotBlank
    @ApiModelProperty(value = "you can't leave the name property blank")
    private String color;
    @NotBlank
    @ApiModelProperty(value = "you can't leave the name property blank")
    private String model;
    private String owner;
    @JsonIgnore
    private boolean hideMe;

    public Car(String name, String color, String model, String owner, boolean hideMe) {
        this.name = name;
        this.color = color;
        this.model = model;
        this.owner = owner;
        this.hideMe = hideMe;
    }
}
