package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void writeFile (ArrayList<Car> carList) {
        try {
            tryWriteFile(carList);
        } catch (IOException e) {
           System.err.println(e.getMessage());
        }
}
public static void tryWriteFile (ArrayList<Car> carList) throws IOException {
    FileWriter fw = new FileWriter("adat.txt");
    for (Car car:carList) {
        fw.write(car.getId().toString());
        fw.write(":");
        fw.write(car.getPlate());
        fw.write(":");
        fw.write(car.getYear().toString());
        fw.write(":");
        fw.write(car.getBrand());
        fw.write(":");
        fw.write(car.getPrice().toString());
        fw.write("\n");

    }
    fw.close();
}

public static ArrayList<Car> readFile () {
    try {
        return tryReadFile();
    } catch (FileNotFoundException e) {
        System.err.println(e.getMessage());
         return new ArrayList<>();
    }



   

}

private static ArrayList<Car> tryReadFile() throws FileNotFoundException {
    ArrayList<Car> carList = new ArrayList<>();
    File file = new File("adat.txt");
    try(Scanner sc = new Scanner(file)) {
        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] array = line.split(":");
            Car car = new Car();
            car.setId(Integer.parseInt(array[0]));
            car.setPlate(array[1]);
            car.setYear(Integer.parseInt(array[2]));
            car.setBrand(array[3]);
            car.setPrice(Integer.parseInt(array[4]));
            carList.add(car);
        }
        
    }
    return carList;
}

}
