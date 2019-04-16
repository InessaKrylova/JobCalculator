package main;

import java.util.*;

public class MainClass {

	 static int totalAmount, combSize;
	 static Set<String> combinationsSet;
	 static List<Set<InputFactor>> allCombinations;
	 static int[] array;
	 static Map<Set<InputFactor>, Set<OutputCharacteristic>> result;
	
	 static void combinations(int position, int maxUsed) {
		 combinationsSet = new HashSet<>();
		 combSize = maxUsed;
		 array = new int[combSize];
		 comb(0, 0);
		 
		 for(String line : combinationsSet){
			 Set<InputFactor> combination = new HashSet<>();
			 for (int i = 0; i < line.length(); i++){				 
				 int index = Integer.parseInt(String.valueOf(line.charAt(i)));
				 combination.add(InputFactor.values()[index-1]);
			 }
			 allCombinations.add(combination);
		 }
	 }	 
	 
	 static void comb(int position, int maxUsed) {
        if (position == combSize) {
        	String s = "";
            for (int j=0; j< array.length; j++){
            	s+=array[j];
            }
            combinationsSet.add(s);
        } else {
            for(int i = maxUsed+1; i <= totalAmount; i++) {
            	array[position] = i;
                comb(position+1,i);
            }
        }
    }
	
	public static void main(String[] args) {
		combinationsSet = new HashSet<>();
		allCombinations = new ArrayList<>();
		array = new int[combSize];
		totalAmount = 4;
		
		combinations(totalAmount, 1);
		combinations(totalAmount, 2);
		combinations(totalAmount, 3);
		combinations(totalAmount, 4);

		System.out.println("Все комбинации:");
		int cSize = 0;
		for (Set<InputFactor> combination : allCombinations) {
			if (combination.size() != cSize) {
				cSize = combination.size();
				System.out.println("\tПо "+cSize+":");
			}
			System.out.println("\t\t"+combination.toString());
		}
		System.out.println();
		
		processing();
		
	}

	private static void processing() {
		System.out.println("Результаты:");
		result = new HashMap<>();
		for (Set<InputFactor> combination : allCombinations){
			Set<OutputCharacteristic> outputSet = new HashSet<>();
			
			boolean highSalary = 
					combination.contains(InputFactor.Востребованность) && combination.contains(InputFactor.Профессионализм);
			boolean middleSalary = false;
			boolean smallSalary = false;
			boolean joy = combination.contains(InputFactor.ЛюбимоеДело);
			boolean noabuse = combination.contains(InputFactor.ИП); //TODO
			
			if (highSalary) outputSet.add(OutputCharacteristic.ГарантияБольшойЗарплаты);
			if (middleSalary) outputSet.add(OutputCharacteristic.ГарантияСреднейЗарплаты);
			if (smallSalary) outputSet.add(OutputCharacteristic.ГарантияМаленькойЗарплаты);
			if (joy) outputSet.add(OutputCharacteristic.Удовольствие);
			if (noabuse) outputSet.add(OutputCharacteristic.НетАбьюза);
				
			result.put(combination, outputSet);
		}
		for (Set<InputFactor> key : result.keySet()) {
			System.out.println("\t"+key + " = " + result.get(key)+"\n");
		}
		
	}

}
