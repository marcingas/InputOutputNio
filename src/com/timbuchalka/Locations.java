package com.timbuchalka;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by timbuchalka on 2/04/2016.
 */
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {
        Path locationPath = FileSystems.getDefault().getPath("locations.dat");
        try(ObjectOutputStream locationFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locationPath)))){
            for(Location location : locations.values()){
                locationFile.writeObject(location);
            }

        }



//        Path locationPath = FileSystems.getDefault().getPath("locations_big.txt");
//        Path directionPath = FileSystems.getDefault().getPath("directions_big.txt");
//        try (BufferedWriter localFile = Files.newBufferedWriter(locationPath);
//             BufferedWriter directFile = Files.newBufferedWriter(directionPath)) {
//            for (Location location : locations.values()) {
//                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                for (String direction : location.getExits().keySet()) {
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        directFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
//
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("IOException: " + e.getMessage());
//        }
    }


    static {
        Path locationPath = FileSystems.getDefault().getPath("locations.dat");
        try(ObjectInputStream locationFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locationPath)))){
            boolean eof = false;
            while (!eof){
                try{
                    Location location = (Location) locationFile.readObject();
                    locations.put(location.getLocationID(), location);

                }catch (EOFException e){
                    eof = true;
                }
            }
        }catch (InvalidClassException e){
            System.out.println("InvalidCLassException " + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException " + e.getMessage());
        }catch (ClassNotFoundException e ){
            System.out.println("ClassNotFoundException " + e.getMessage());
        }


//        Path locationPath = FileSystems.getDefault().getPath("locations_big.txt");
//        Path directionPath = FileSystems.getDefault().getPath("directions_big.txt");
//        try (Scanner scanner = new Scanner(Files.newBufferedReader(locationPath))) {
//            scanner.useDelimiter(",");
//            while (scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("imported loc: " + loc + ": " + description);
//                locations.put(loc, new Location(loc, description, null));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (BufferedReader dirFile = Files.newBufferedReader(directionPath)) {
//            String input;
//            while ((input = dirFile.readLine()) != null) {
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//                System.out.println(loc + ": " + direction + ": " + destination);
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
