import java.io.*;
/*
* Encrypts and/or decrypts text through passed arguments to the code
* (uses one set of array for encryption and decryption)
*/
public class Enc_Dec_2 {
    public static void main(String[] args) {
        int key = 0;
        String data = "";
        String mode = "enc";
        String file_in = null;
        String file_out = null;

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
                }
            }
        }
        if ((data == "" && file_in == null) || data != "") {
            if (file_out == null) {
                System.out.println(change(data, key, mode));
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_out))) {
                    writer.write(change(data, key, mode));
                } catch (IOException f) {
                    System.out.println("Error: can't write to the file.");
                }
            }
        } else {
            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(file_in))) {
                if (file_out == null){
                    while((line = reader.readLine()) != null){
                        System.out.println(change(line, key, mode));
                    }
                }
                else{
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(file_out))){
                        while ((line = reader.readLine()) != null){
                            writer.write(change(line, key, mode));
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
    public static String change (String data, int key, String mode){
        String result = "";
        char[] set = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '@', '!', '~', '^', '%', '$', '&', '#', '*',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', '_', '\\', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (char each : data.toCharArray()){
            for (int i = 0; i < set.length; i++){
                if (each == set[i]){
                    if (mode.equals("enc")){
                        each = set[(i + key) % set.length];
                    }
                    else if (mode.equals("dec")){
                        each = set[(set.length + (i - key)) % set.length];
                    }
                    i = set.length;
                }
            }
            result = result + each;
        }
        return result;
    }

}