package com.carrental.JavaServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.Objects;

@RestController
public class RestApi {

    @PostMapping("/post-message")
    public String postMessage(
            @RequestParam(value="name",defaultValue = "")
            String name,
            @RequestParam(value="email",defaultValue = "")
            String email,
            @RequestParam(value="phone",defaultValue = "")
            String phone,
            @RequestParam(value="message",defaultValue = "")
            String message


    ) {

        try {
        DbConnect.executeUpdate(String.format("INSERT INTO `kapcsolat`(`name`, `email`, `phone`, `message`) VALUES ('%s','%s','%s','%s')",name,email,phone,message));
        } catch (SQLException e) {
            return "operationFail";
        }
        return message;
    }


    @PostMapping("/post-reservation")
    public String postReservation(
            @RequestParam(value="name",defaultValue = "")
            String name,
            @RequestParam(value="email",defaultValue = "")
            String email,
            @RequestParam(value="phone",defaultValue = "")
            String phone,
            @RequestParam(value="licenseNumber",defaultValue = "")
            String licenseNumber,
            @RequestParam(value="creditCard",defaultValue = "")
            String creditCard,
            @RequestParam(value="creditCardExp",defaultValue = "")
            String creditCardExp,
            @RequestParam(value="cvv",defaultValue = "")
            String cvv,
            @RequestParam(value="startDate",defaultValue = "")
            String startDate,
            @RequestParam(value="endDate",defaultValue = "")
            String endDate,
            @RequestParam(value="licenseTime",defaultValue = "")
            String licenseTime,
            @RequestParam(value="insure",defaultValue = "")
            String insure,
            @RequestParam(value="carid",defaultValue = "")
            String carid
    ) {

        try {

            if (!DbUtil.isRealInterval(startDate, endDate)){
                return "bad date";
            }
            if (!DbUtil.isCarAvailable(carid, startDate, endDate)){
                return "not available at this interval";
            }

            System.out.println(Objects.equals(licenseTime, "on"));
            int intLicenseTime= Objects.equals(licenseTime, "on") ? 1:0;
            int intInsure= Objects.equals(insure, "on") ? 1:0;
            String Query = String.format(
                    "INSERT INTO `megrendelo`(`name`, `email`, `phone`, `licenseNumber`, `creditCard`, `creditCardExp`, `cvv`, `startDate`, `endDate`, `licenseTime`, `insure`, `carid`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    name,email,phone,licenseNumber,creditCard,creditCardExp,cvv,startDate,endDate,intLicenseTime,intInsure,carid
            );

            DbUtil.isCarAvailable(carid, startDate, endDate);
            DbConnect.executeUpdate(Query);
        } catch (SQLException e) {
            System.out.println(e);
            return "operationFail";
        }
        return "ok";
    }
}
