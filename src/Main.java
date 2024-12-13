import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ArrayList<String> orderingRules = new ArrayList<>();
        ArrayList<String> updates = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("day5.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] splitted = line.split(" ");
                if (Arrays.toString(splitted).contains("|")) {
                    orderingRules.add(Arrays.toString(splitted));
                }
                if (Arrays.toString(splitted).contains(",")) {
                    updates.add(Arrays.toString(splitted));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("Ordering Rules: " + orderingRules);
//        System.out.println("Updates: " + updates);

        ArrayList<ArrayList<Integer>> updateNumbers = new ArrayList<>();

        for (int i = 0; i < updates.size(); i++) {
            StringBuilder newNumber = new StringBuilder();
            ArrayList<Integer> currentUpdate = new ArrayList<>();
            for (int j = 0; j < updates.get(i).length(); j++) {
                char current = updates.get(i).charAt(j);
                if ((int) current >= 48 && (int) current <= 57) {
                    newNumber.append(current);
                } else if ((int) current == 44) {
                    currentUpdate.add(Integer.parseInt(newNumber.toString()));
                    newNumber.setLength(0);
                }
                if (j == updates.get(i).length() - 1) {
                    currentUpdate.add(Integer.parseInt(newNumber.toString()));
                    newNumber.setLength(0);
                }
            }
            updateNumbers.add(currentUpdate);
        }

//        System.out.println("updateNumbers list... " + updateNumbers);

        ArrayList<ArrayList<Integer>> orderingRulesNumbers = new ArrayList<>();

        for (int i = 0; i < orderingRules.size(); i++) {
            StringBuilder newNumber = new StringBuilder();
            ArrayList<Integer> currentRule = new ArrayList<>();
            for (int j = 0; j < orderingRules.get(i).length(); j++) {
                char current = orderingRules.get(i).charAt(j);
                if ((int) current >= 48 && (int) current <= 57) {
                    newNumber.append(current);
                } else if ((int) current == 124) {
                    currentRule.add(Integer.parseInt(newNumber.toString()));
                    newNumber.setLength(0);
                }
                if (j == orderingRules.get(i).length() - 1) {
                    currentRule.add(Integer.parseInt(newNumber.toString()));
                    newNumber.setLength(0);
                }
            }
            orderingRulesNumbers.add(currentRule);
        }

//        System.out.println("orderingRulesNumbers list... " + orderingRulesNumbers);

        int successfulUpdates = 0;
        int middlePageSum = 0;

        for (int i = 0; i < updateNumbers.size(); i++) {
            int unsuccessfulCount = 0;

            for (int j = 0; j < updateNumbers.get(i).size() - 1; j++) {
                for (int k = j + 1; k < updateNumbers.get(i).size(); k++) {
                    int firstNumber = updateNumbers.get(i).get(j);
                    int secondNumber = updateNumbers.get(i).get(k);

                    for (int l = 0; l < orderingRulesNumbers.size(); l++) {
                        if (orderingRulesNumbers.get(l).get(1) == firstNumber
                                && orderingRulesNumbers.get(l).get(0) == secondNumber) {
                            unsuccessfulCount++;
                        }
                    }
                }
            }
            if (unsuccessfulCount == 0) {
                successfulUpdates++;
                System.out.println("successful update : " + updateNumbers.get(i));
                int middleNumber = updateNumbers.get(i).get((updateNumbers.get(i).size() / 2));
                middlePageSum += middleNumber;
//                System.out.println("middle no is ..." + middleNumber);
            }
        }
        System.out.println("Successful update count: " + successfulUpdates);
        System.out.println("Sum of middle pages: " + middlePageSum);
    }
}