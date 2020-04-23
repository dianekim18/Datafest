import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class census_ave {
    public static void main (String[] args) throws IOException {
        int stat = 13;
        Scanner s = new Scanner(new File("data'/census/"));
        LinkedHashMap<Integer, ArrayList<Double>> myMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, Double> myMap2 = new LinkedHashMap<>();

        int counter = 0;
        for (int i=0; i<57745; i++) {
            String line = s.nextLine();
            String[] arr = line.split(",");
            Integer fips = -99;
            if (!(arr[0].equals(""))) fips = Integer.valueOf(arr[0]);
            Double pm = -99.0;
            if (arr.length > 2) pm = Double.valueOf(arr[stat]);
            myMap.putIfAbsent(fips, new ArrayList<Double>());
            myMap.get(fips).add(pm);
        }

        s.close();


        for (Integer i : myMap.keySet()){
            Double val = 0.0;
            ArrayList<Double> db = new ArrayList<>();
            for (Double dd : myMap.get(i)){
                if (dd != -99.0) db.add(dd);
                val += dd;
            }
            if(db.size()==0) myMap2.putIfAbsent(i,-99.0);
            myMap2.putIfAbsent(i,val/db.size());
        }

        for (Integer i : myMap2.keySet()){
            System.out.println(myMap2.get(i));
        }


    }
}
