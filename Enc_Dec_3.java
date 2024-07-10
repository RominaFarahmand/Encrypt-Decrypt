import java.io.*;
/*
* Encrypts and/or decrypts text through passed arguments to the code
* (uses multiple sets of arrays for encryption and decryption
* based upon whether special characters are to be used or not)
*/
public class Enc_Dec_3 {
    public static void main(String[] args) {
        int key = 0;
        String data = "";
        String mode = "enc";
        String file_in = null;
        String file_out = null;
        String alg = "shift";

        if (args.length != 0) {
            for (int i = 0; i < args.length - 1; i += 2) {
                if (args[i].equals("-mode")) {
                    mode = args[i + 1];
                } else if (args[i].equals("-key")) {
                    key = Integer.parseInt(args[i + 1]);
                } else if (args[i].equals("-data")) {
                    data = args[i + 1];
                } else if (args[i].equals("-in")) {
                    file_in = args[i + 1];
                } else if (args[i].equals("-out")) {
                    file_out = args[i + 1];
                } else if (args[i].equals("-alg")) {
                    alg = args[i + 1];
                }
            }
        }
        if ((data == "" && file_in == null) || data != "") {
            if (file_out == null) {
                System.out.println(change(data, key, mode, alg));
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_out))) {
                    writer.write(change(data, key, mode, alg));
                } catch (IOException f) {
                    System.out.println("Error: can't write to the file.");
                }
            }
        } else {
            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(file_in))) {
                if (file_out == null){
                    while((line = reader.readLine()) != null){
                        System.out.println(change(line, key, mode, alg));
                    }
                }
                else{
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(file_out))){
                        while ((line = reader.readLine()) != null){
                            writer.write(change(line, key, mode, alg));
                            writer.newLine();
                        }
                    }
                    catch(IOException f){
                        System.out.println("Error: can't write to file.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: can't read file.");
            }
        }
    }
    public static String change (String data, int key, String mode, String alg){
        String result = "";
        char[] set1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '@', '!', '~', '^', '%', '$', '&', '#', '*',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', '_', '\\', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        char[] set2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        char[] set3 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                        'U', 'V', 'W', 'X', 'Y', 'Z'};

        for (char each : data.toCharArray()){
            if (alg.equals("shift")) {
                if(each == Character.toLowerCase(each)){
                    for (int i = 0; i < set2.length; i++){
                        if (each == set2[i]){
                            if (mode.equals("enc")){
                                each = set2[(i + key) % set2.length];
                            }
                            else if(mode.equals("dec")){
                                each = set2[(set2.length + (i - key)) % set2.length];
                            }
                            i = set2.length;
                        }
                    }
                }
                else{
                    for (int i = 0; i < set3.length; i++){
                        if(each == set3[i]){
                            if (mode.equals("enc")){
                                each = set3[(i + key) % set3.length];
                            }
                            else if(mode.equals("dec")){
                                each = set3[(set3.length + (i - key)) % set3.length];
                            }
                            i = set3.length;
                        }
                    }
                }
            }
            else{
                for (int i = 0; i < set1.length; i++) {
                    if (each == set1[i]) {
                        if (mode.equals("enc")) {
                            each = set1[(i + key) % set1.length];
                        } else if (mode.equals("dec")) {
                            each = set1[(set1.length + (i - key)) % set1.length];
                        }
                        i = set1.length;
                    }
                }
            }
            result = result + each;
        }
        return result;
    }

}