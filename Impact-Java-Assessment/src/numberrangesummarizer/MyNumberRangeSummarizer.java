/**
 * @author Ryan Josias
 **/

package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;

public class MyNumberRangeSummarizer implements NumberRangeSummarizer {

    public static void main(String[] args) {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        System.out.println("*** Number Range Summarizer ***");
        System.out.println("Please enter a sequence of numbers separated by comma:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Collection<Integer> collection = summarizer.collect(input);
        String summary = summarizer.summarizeCollection(collection);
        System.out.println(summary);
    }

    @Override
    public Collection<Integer> collect(String input) {
        
        //remove all white space
        input = input.replaceAll("\\s+", "");

        //Throw an exception if input string is empty or null
        if(input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("String input cannot be empty or null");
        }

        String[] stringArray = input.split(",");

        //Throw an exception if the array contains any invalid characters
        for (String str : stringArray) {
            if (!str.matches("-?\\d+")) {
                throw new IllegalArgumentException("Invalid input. Input must be in the form of numbers separated by comma.");
            }
        }

        List<Integer> numbers = new ArrayList<>();

        //Loop through the array and add it to an Array list object
        for (int i = 0; i < stringArray.length; i++) {
            numbers.add(Integer.parseInt(stringArray[i]));
        }

        Collections.sort(numbers);

        //Returns a sorted ArrayList
        return numbers; 
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        
        //Use hashset to retrieve only unique values from input
        HashSet<Integer> set = new HashSet<>(input);
        
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(set);
        Collections.sort(numbers);
        List<String> ranges = new ArrayList<>();
        int rangeStart = numbers.get(0);

        //Loop through each item in the ArrayList
        for (int i = 1; i <= numbers.size(); i++) {

            //If i is the last element OR i is not sequential to the previous element's value, enter the if statement , else do nothing and continue through the for loop
            if (i == numbers.size() || numbers.get(i) != numbers.get(i - 1) + 1) {
               
                //If i-1 was not part of a sequence, add i-1 to the ranges List
                if (rangeStart == numbers.get(i - 1)) {
                    ranges.add(String.valueOf(rangeStart));
                    
                //Else if i-1 was part of a sequence, add the sequence range to the ranges List
                }
                else {
                    ranges.add(rangeStart + "-" + numbers.get(i - 1));
                }
                
                //If i is not the last element, reset the range start
                if (i < numbers.size()) {
                    rangeStart = numbers.get(i);
                }
            }
        }
        String output = ranges.stream().collect(Collectors.joining(", "));
        return output;
    }
}
