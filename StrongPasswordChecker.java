import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StrongPasswordChecker {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a password as input.");
            return;
        }

        String password = args[0];
        HashTableSeparateChaining htChain1 = new HashTableSeparateChaining(1000);
        HashTableSeparateChaining htChain2 = new HashTableSeparateChaining(1000);
        HashTableLinearProbing htLinearProbe1 = new HashTableLinearProbing(20000);
        HashTableLinearProbing htLinearProbe2 = new HashTableLinearProbing(20000);

        BufferedReader br = new BufferedReader(new FileReader("wordlist10000.txt"));
        String word;
        int lineNumber = 1;
        while ((word = br.readLine()) != null) {
            htChain1.insert(word, lineNumber, true);
            htChain2.insert(word, lineNumber, false);
            htLinearProbe1.insert(word, lineNumber, true);
            htLinearProbe2.insert(word, lineNumber, false);
            lineNumber++;
        }
        br.close();

        boolean isStrong = true;
        if (password.length() < 8) {
            isStrong = false;
            System.out.println("Password is not strong: Less than 8 characters.\n");
        } else if (htChain1.contains(password, true) || htChain2.contains(password, false) ||
                htLinearProbe1.contains(password, true) || htLinearProbe2.contains(password, false)) {
            isStrong = false;
            System.out.println("Password is not strong: It is a common dictionary word.\n");
        } else {
            for (int i = 0; i < password.length(); i++) {
                String wordPart = password.substring(0, i);
                String digitPart = password.substring(i);

                if ((htChain1.contains(wordPart, true)  && digitPart.matches("\\b\\w+\\d\\b")) || 
                    (htChain2.contains(wordPart, false) && digitPart.matches("\\b\\w+\\d\\b")) ||
                    (htLinearProbe1.contains(wordPart, true) && digitPart.matches("\\b\\w+\\d\\b")) ||
                    (htLinearProbe2.contains(wordPart, false) && digitPart.matches("\\b\\w+\\d\\b"))) {
                    isStrong = false;
                    System.out.println("Password is not strong: It is a common dictionary word followed by a digit.\n");
                    break;
                }
            }
        }

        if (isStrong) {
            System.out.println("Password is strong.");
        }

        System.out.println("Separate Chaining HashTable (hashCode1) comparisons: " + htChain1.getComparisons());
        System.out.println("Separate Chaining HashTable (hashCode2) comparisons: " + htChain2.getComparisons());
        System.out.println("Linear Probing HashTable (hashCode1) comparisons: " + htLinearProbe1.getComparisons());
        System.out.println("Linear Probing HashTable (hashCode2) comparisons: " + htLinearProbe2.getComparisons() + "\n");
    }    
}
