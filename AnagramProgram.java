import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AnagramProgram {

    static final int CHECK_ANAGRAM = 1;
    static final int PROVIDE_ANAGRAMS = 2;  
    static final int EXIT = 3;
    static final int REPEAT = 4;

    static int keyCounter = 1;
    static String[] stringInputs = new String[2];
    static Map<Integer, ArrayList<String> > mappedAnagrams = new LinkedHashMap<>();

    /**
	 * Checks if is anagram.
	 *
	 * @param string 
	 * @param string
	 * @return True if both string are anagram each other, False otherwise
	 */
	public static Boolean isAnagram(String s1, String s2) {
		Boolean toReturn = true;

		if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
			toReturn = false;
		} else {
			Map<Character, Integer> map1 = new LinkedHashMap<>();
			for (Character c : s1.toCharArray()) {
				if (Character.isLetter(c)) {
					Integer oldValue = map1.put(Character.toLowerCase(c), 1);
					if (oldValue != null) {
						map1.put(c, oldValue + 1);
					}
				}

			}
			Map<Character, Integer> map2 = new LinkedHashMap<>();
			for (Character c : s2.toCharArray()) {
				if (Character.isLetter(c)) {
					Integer oldValue = map2.put(Character.toLowerCase(c), 1);
					if (oldValue != null) {
						map2.put(c, oldValue + 1);
					}
				}

			}

			for (Character c : map1.keySet()) {
				if (!map1.get(c).equals(map2.get(c)))
					toReturn = false;
			}

		}
		return toReturn;
	}

    
    /**
	 * Handle the interactive part with the user
	 *
	 * @return The choice of the user
	 */
    public static int interactiveCLI () {
        String msg;

        Scanner interactiveScan = new Scanner(System.in);

        System.out.println("Choose among the following features:");
        System.out.println("A Checks if two texts are anagrams of each other");
        System.out.println("B From previous inputs provides all the anagrams for a given string or text");
        System.out.println("C Exit the program");
        System.out.println("Write your selection (A/B/C): ");
        
        msg = interactiveScan.nextLine();

        if (msg.equals("A")) {
            System.out.println("Provides the first string or text");
            stringInputs[0] = interactiveScan.nextLine();
            System.out.println("Provides the second string or text");
            stringInputs[1] = interactiveScan.nextLine();
            //interactiveScan.close();
            return CHECK_ANAGRAM;
        }
        else if (msg.equals("B")) {
            System.out.println("From the previous entries, provides one string or text");
            stringInputs[0] = interactiveScan.nextLine();
            return PROVIDE_ANAGRAMS;
        }
        else if (msg.equals("C")) {
            return EXIT;
        }
        else {            
            return REPEAT;
        }

    }

    /**
	 * Map and store the anagram strings, verifying if
     * - There is not duplication
     * - The anagrams belong to the same group of previos anagrams
	 *
	 */
    public static void MappingAnagrams() {
        int coincidenceFound = 0;
        ArrayList<String> temporal = new ArrayList<>();
        if (!mappedAnagrams.isEmpty()){
            for (Map.Entry<Integer,ArrayList<String>> entry : mappedAnagrams.entrySet()) {
                ArrayList<String> anagrams = entry.getValue();
                int keyValue = entry.getKey();
                                
                // Check that this group is anagram of another existing group
                if (isAnagram(stringInputs[0], anagrams.get(0))){
                    coincidenceFound++;
                    // to add new strings that are anagrams 
                    if (!anagrams.contains(stringInputs[0])){
                    anagrams.add(stringInputs[0]);
                    }
                    if (!anagrams.contains(stringInputs[1])){
                    anagrams.add(stringInputs[1]);
                    }
                }
                                
                if (coincidenceFound > 0){
                    mappedAnagrams.put(keyValue, anagrams);
                }
            }
            if (coincidenceFound == 0){
                temporal.add(stringInputs[0]);
                temporal.add(stringInputs[1]);
                mappedAnagrams.put(keyCounter, temporal);
                keyCounter++;
            }                                 
        }
        else{                            
            temporal.add(stringInputs[0]);
            temporal.add(stringInputs[1]);
            mappedAnagrams.put(keyCounter, temporal);
            keyCounter++;
        }
    }

    /**
	 * Provide the list of anagrams for a given string without counting the reference string.
	 *
	 */
    public static ArrayList<String> allMappedAnagrams(String s1) {
        ArrayList<String> listAnagrams = new ArrayList<>();

        for (Map.Entry<Integer,ArrayList<String>> entry : mappedAnagrams.entrySet()) {
            ArrayList<String> anagrams = entry.getValue();
            if (anagrams.contains(stringInputs[0])){
                System.out.println("Anagrams FOUND:");
                for (String text : anagrams){
                    if (!text.equals(s1)){
                        listAnagrams.add(text);
                    }
                }
                break;
            }
        } 
        return listAnagrams;
    }

    public static void main(String[] args){
        int response;
        ArrayList<String> listAnagrams = new ArrayList<>();

        do {
            response = interactiveCLI();
            
            switch (response) {
                case CHECK_ANAGRAM:
                    if (isAnagram(stringInputs[0], stringInputs[1])){
                        MappingAnagrams();
                        System.out.println("Text are ANAGRAMS");
                    }
                    else {
                        System.out.println("Text are NO ANAGRAMS");
                    }
                    break;
                case PROVIDE_ANAGRAMS:
                    listAnagrams = allMappedAnagrams(stringInputs[0]);
                    if (!listAnagrams.isEmpty()){
                        listAnagrams.forEach(anagram -> System.out.println(anagram));
                    }
                    else{                            
                        System.out.println("No Anagrams FOUND");
                    }
                    break;
                case REPEAT:
                    System.out.println("INVALID CHOICE");
                    break;
                default:
                    break;
            }
        } while (response != EXIT);         
        
    }

}
