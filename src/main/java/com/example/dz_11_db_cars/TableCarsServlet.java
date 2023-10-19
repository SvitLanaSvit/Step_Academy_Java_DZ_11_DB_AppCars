package com.example.dz_11_db_cars;

import com.example.dz_11_db_cars.entity.Cars;
import com.example.dz_11_db_cars.entity.ManufacturerCountCar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "tableCarsServlet", value = "/table-cars")
public class TableCarsServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "bestuser", "bestuser");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/cars.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String dataFromForm = request.getParameter("param");
        if(dataFromForm.equals("connect")){
            connect();
            String str = "Connect success";
            request.setAttribute("message", str);
            getServletContext().getRequestDispatcher("/cars.jsp").forward(request,response);
        }else if(dataFromForm.equals("insert")){
            insert();
            String str = "Insert into table cars success";
            request.setAttribute("message", str);
            getServletContext().getRequestDispatcher("/cars.jsp").forward(request, response);
        }else if(dataFromForm.equals("allCars")){
            List<Cars> cars = showAllCars();
            request.setAttribute("cars", cars);
            getServletContext().getRequestDispatcher("/allCars.jsp").forward(request, response);
        }else if(dataFromForm.equals("allVehicle")){
            List<String> vehicles = getAllVehicle();
            request.setAttribute("vehicles", vehicles);
            getServletContext().getRequestDispatcher("/showAllVehicle.jsp").forward(request, response);
        }else if(dataFromForm.equals("countCars")){
            List<ManufacturerCountCar> cars = getManufacturerCountCars();
            request.setAttribute("cars", cars);
            getServletContext().getRequestDispatcher("/carsByVehicles.jsp").forward(request, response);
        }else if(dataFromForm.equals("maxCars")){
            ManufacturerCountCar manufacturer = getManufacturerWithMaxCars();
            request.setAttribute("manufacturer", manufacturer);
            getServletContext().getRequestDispatcher("/maxCars.jsp").forward(request, response);
        }else if(dataFromForm.equals("minCars")){
            ManufacturerCountCar manufacturer = getManufacturerWithMinCars();
            request.setAttribute("manufacturer", manufacturer);
            getServletContext().getRequestDispatcher("/minCars.jsp").forward(request, response);
        }else if(dataFromForm.equals("searchByYear")){
            String year = request.getParameter("year");
            int yearInt = Integer.parseInt(year);
            List<Cars> cars = searchCarsByYear(yearInt);
            request.setAttribute("cars", cars);
            getServletContext().getRequestDispatcher("/carsByYear.jsp").forward(request,response);
        }
    }

    private void connect(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS Cars (" +
                    "car_id INT AUTO_INCREMENT PRIMARY KEY, manufacturer VARCHAR(255) NOT NULL, " +
                    "vehicle_name VARCHAR(255) NOT NULL, " +
                    "engine_volume DECIMAL(4, 2) NOT NULL, " +
                    "year_of_issue YEAR NOT NULL, " +
                    "car_color VARCHAR(50) NOT NULL, " +
                    "car_type ENUM('sedan', 'hatchback', 'station_wagon') NOT NULL);";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insert(){
        try {
            String sql = "INSERT INTO cars(manufacturer, vehicle_name, engine_volume, year_of_issue, car_color, car_type)" +
                    "VALUES('Toyota', 'Camry', 2.5, 2022, 'Blue', 'sedan')," +
                    "('Honda', 'Civic', 1.8, 2021, 'Red', 'sedan')," +
                    "('Volkswagen', 'Golf', 1.4, 2020, 'Silver', 'hatchback')," +
                    "('Ford', 'Focus', 2.0, 2022, 'White', 'hatchback')," +
                    "('Subaru', 'Outback', 3.6, 2021, 'Green', 'station_wagon')," +
                    "('Honda', 'Fit', 1.5, 2020, 'White', 'hatchback')," +
                    "('Honda', 'Accord', 2.4, 2022, 'Silver', 'sedan')," +
                    "('Toyota', 'Corolla', 1.8, 2022, 'Silver', 'sedan')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Cars> showAllCars(){
        try {
            String sql = "SELECT * FROM cars";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Cars> cars = new ArrayList<>();
            while (resultSet.next()){
                Cars car = new Cars();
                int id = resultSet.getInt("car_id");
                String manufacturer = resultSet.getString("manufacturer");
                String vehicleName = resultSet.getString("vehicle_name");
                BigDecimal engineVolume = resultSet.getBigDecimal("engine_volume");
                int yearOfIssue = resultSet.getInt("year_of_issue");
                String carColor = resultSet.getString("car_color");
                String carType = resultSet.getString("car_type");

                car.setCar_id(id);
                car.setManufacturer(manufacturer);
                car.setVehicle_name(vehicleName);
                car.setEngine_volume(engineVolume);
                car.setYear_of_issue(yearOfIssue);
                car.setCar_color(carColor);
                car.setCar_type(carType);

                cars.add(car);
            }

            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getAllVehicle(){
        try {
            String sql = "SELECT DISTINCT manufacturer FROM Cars";
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> vehicles = new ArrayList<>();

            while(resultSet.next()){
                String vehicle = resultSet.getString("manufacturer");
                vehicles.add(vehicle);
            }

            return  vehicles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ManufacturerCountCar> getManufacturerCountCars(){
        try {
            String sql = "SELECT manufacturer, COUNT(*) AS number_of_cars FROM cars GROUP BY manufacturer;";
            ResultSet resultSet = statement.executeQuery(sql);
            List<ManufacturerCountCar> countCarsList = new ArrayList<>();

            while(resultSet.next()){
                ManufacturerCountCar countCars = new ManufacturerCountCar();
                countCars.setManufacturer(resultSet.getString("manufacturer"));
                countCars.setCountCars(resultSet.getInt("number_of_cars"));

                countCarsList.add(countCars);
            }
            return countCarsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ManufacturerCountCar getManufacturerWithMaxCars(){
        try {
            String sql = "SELECT manufacturer, COUNT(*) AS total_cars FROM Cars GROUP BY manufacturer " +
                    "ORDER BY total_cars DESC LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(sql);
            ManufacturerCountCar manufacturerCountCar = new ManufacturerCountCar();

            while(resultSet.next()){
                manufacturerCountCar.setManufacturer(resultSet.getString("manufacturer"));
                manufacturerCountCar.setCountCars(resultSet.getInt("total_cars"));
            }
            return manufacturerCountCar;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ManufacturerCountCar getManufacturerWithMinCars(){
        try {
            String sql = "SELECT manufacturer, COUNT(*) AS total_cars FROM Cars GROUP BY manufacturer " +
                    "ORDER BY total_cars LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(sql);
            ManufacturerCountCar manufacturerCountCar = new ManufacturerCountCar();

            while(resultSet.next()){
                manufacturerCountCar.setManufacturer(resultSet.getString("manufacturer"));
                manufacturerCountCar.setCountCars(resultSet.getInt("total_cars"));
            }
            return manufacturerCountCar;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Cars> searchCarsByYear(int year){

        try {
            String sql = "SELECT * FROM cars WHERE year_of_issue = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Cars> carsList = new ArrayList<>();

            while(resultSet.next()){
                Cars cars = new Cars();
                cars.setCar_id(resultSet.getInt("car_id"));
                cars.setManufacturer(resultSet.getString("manufacturer"));
                cars.setVehicle_name(resultSet.getString("vehicle_name"));
                cars.setEngine_volume(resultSet.getBigDecimal("engine_volume"));
                cars.setYear_of_issue(resultSet.getInt("year_of_issue"));
                cars.setCar_color(resultSet.getString("car_color"));
                cars.setCar_type(resultSet.getString("car_type"));

                carsList.add(cars);
            }
            return carsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
