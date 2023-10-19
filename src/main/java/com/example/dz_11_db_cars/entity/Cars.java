package com.example.dz_11_db_cars.entity;

import jakarta.servlet.annotation.WebServlet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Cars {
    private int car_id;
    private String manufacturer;
    private String vehicle_name;
    private BigDecimal engine_volume;
    private int year_of_issue;
    private String car_color;
    private String car_type;
}
