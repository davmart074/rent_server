package com.carrental.JavaServer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class DbUtil {

    public static boolean isCarAvailable(String carid,String startDate, String endDate) throws SQLException {

        ResultSet carrawdata = DbConnect.sendQuery(String.format("SELECT * FROM `megrendelo` WHERE carid = %s", carid));
        ArrayList<HashMap<String, String>> cardata = DbUtil.getJsonFromResultSet(carrawdata);

        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);
        AtomicBoolean isAvailable = new AtomicBoolean(true);

        cardata.forEach((singledata) -> {
            LocalDateTime existingStartDate = LocalDateTime.parse(singledata.get("startDate").replace(' ', 'T'));
            LocalDateTime existingEndDate = LocalDateTime.parse(singledata.get("endDate").replace(' ', 'T'));

            if (startDateTime.compareTo(existingStartDate) >= 0 && startDateTime.compareTo(existingEndDate) <= 0){
                isAvailable.set(false);
            }
            System.out.println(endDateTime.compareTo(existingStartDate));
            System.out.println(endDateTime.compareTo(existingEndDate));

            if (endDateTime.compareTo(existingStartDate) >= 0 && endDateTime.compareTo(existingEndDate) <= 0){
                isAvailable.set(false);
            }
            if (startDateTime.compareTo(existingStartDate) >= 0 && endDateTime.compareTo(existingEndDate) <= 0){
                isAvailable.set(false);
            }

        });

        System.out.println(isAvailable.get());

        return isAvailable.get();
    }

    public static boolean isRealInterval(String startDate, String endDate){
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);
        int result = startDateTime.compareTo(endDateTime);

        return result == -1 ? true : false;

    }

    public static String getStringFromResultSet(ResultSet queryResult) throws SQLException {
        String strCategories = "";

        ResultSetMetaData metaData = queryResult.getMetaData();
        int columnNum = metaData.getColumnCount();

        for (int i = 1; i <= columnNum; i++) {
            strCategories += metaData.getColumnName(i) + "; ";
        }

        strCategories += "<br>";

        while (queryResult.next()) {

            for (int i = 1; i <= columnNum; i++) {
                strCategories += queryResult.getString(i) + "; ";
            }

            strCategories += "<br>";
        }

        return strCategories;
    }

    static ArrayList<HashMap<String, String>> getJsonFromResultSet(ResultSet queryResult) throws SQLException {
        ResultSetMetaData metaData = queryResult.getMetaData();
        int columnCount = metaData.getColumnCount();

        String[] columnNames = new String[ columnCount ];

        for (int i = 1; i <= columnCount; i++) {
            columnNames[i-1] = metaData.getColumnName(i);
        }
        System.out.println(Arrays.toString(columnNames));

        ArrayList< HashMap<String, String> > resultData = new ArrayList<>();

        while( queryResult.next() ) {
            HashMap<String, String> rowData = new HashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                rowData.put(metaData.getColumnName(i), queryResult.getString(i));
            }

            resultData.add(rowData);
        }

        return resultData;
    }

}
