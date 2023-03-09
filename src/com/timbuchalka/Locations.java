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
        Path locationPath = FileSystems.getDefault().getPath("locations_big.txt");
        Path directionPath = FileSystems.getDefault().getPath("directions_big.txt");
        try (BufferedWriter localFile = Files.newBufferedWriter(locationPath);
             BufferedWriter directFile = Files.newBufferedWriter(directionPath)) {
            for (Location location : locations.values()) {
                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        directFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }


    static {
        Path locationPath = FileSystems.getDefault().getPath("locations_big.txt");
        Path directionPath = FileSystems.getDefault().getPath("directions_big.txt");
        try (Scanner scanner = new Scanner(Files.newBufferedReader(locationPath))){
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()){
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("imported loc: " + loc + ": " + description);
                locations.put(loc, new Location(loc, description, null));
            }
        }catch (IOException e){
            e.printStackTrace();
        }




//        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean eof = false;
//            while (!eof) {
//                try {
//                    Location location = (Location) locFile.readObject();
//                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
//                    System.out.println("Found " + location.getExits().size() + " exits");
//
//                    locations.put(location.getLocationID(), location);
//                } catch (EOFException e) {
//                    eof = true;
//                }
//            }
//        } catch (InvalidClassException e) {
//            System.out.println("InvalidClassException " + e.getMessage());
//        } catch (IOException io) {
//            System.out.println("IO Exception " + io.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println("ClassNotFoundException " + e.getMessage());
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
