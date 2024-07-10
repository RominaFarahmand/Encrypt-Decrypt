import java.util.Scanner;

//Encrypts and/or decrypts user-specified message and key

public class Encrypt_Decrypt_User {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to encrypt or decrypt message? (enter enc or dec)");
        String type = scanner.nextLine().toLowerCase();
        System.out.println("Enter the message: ");
        String message = scanner.nextLine();
        System.out.println("Enter the key: (the number)");
        int key = scanner.nextInt();
        scanner.close();
        char[] divided_message = message.toCharArray();
        System.out.println(change(divided_message, key, type));

    }
    public static String change (char[] input, int Key, String type){
        String result = "";
        char[] set = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '@', '!', '~', '^', '%', '$', '&', '#', '*',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', '_', '\\', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (char each : input){
            for (int i = 0; i < set.length; i++){
                if (each == set[i]){
                    if (type.equals("enc")){
                        each = set[(i + Key) % set.length];
                    }
                    else if (type.equals("dec")){
                        each = set[(set.length + (i - Key)) % set.length];
                    }
                    i = set.length;
                }
            }
            result = result + each;
        }
        return result;
    }
}

