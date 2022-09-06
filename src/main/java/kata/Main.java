package kata;

        import org.springframework.util.StopWatch;

        import java.security.PublicKey;
        import java.security.SignatureException;
        import java.util.*;

public class Main {

    //    Операнды и операции следует вводить через пробел
    public static void main(String[] args) {
        System.out.println("Введите выражение");
        Scanner scan1 = new Scanner(System.in);
        String expression = scan1.nextLine();
        String[] expArrey = expression.split(" ");
        check(expArrey);
        String result = calc(expArrey);
        System.out.println(result);

    }

    static boolean numberFlag0;
    static boolean numberFlag1;
    static boolean numberFlag2;

    public static String calc(String[] expArrey) {
        String result = "";
        if (numberFlag0 == false) {
            if (expArrey[1].equals("+")) {
                result = Integer.toString(Integer.parseInt(expArrey[0]) + Integer.parseInt(expArrey[2]));
            } else if (expArrey[1].equals("-")) {
                result = Integer.toString(Integer.parseInt(expArrey[0]) - Integer.parseInt(expArrey[2]));
            } else if (expArrey[1].equals("*")) {
                result = Integer.toString(Integer.parseInt(expArrey[0]) * Integer.parseInt(expArrey[2]));
            } else if (expArrey[1].equals("/")) {
                result = Integer.toString(Integer.parseInt(expArrey[0]) / Integer.parseInt(expArrey[2]));
            }
        } else if (expArrey[1].equals("+")) {
            Integer preResult = latino.get(expArrey[0]) + latino.get(expArrey[2]);
            String preResultString = Integer.toString(preResult);
            String[] preResultArrey = preResultString.split("");
            switch (preResultArrey.length) {

                case (2):
                    String[] resultArrey2 = new String[2];
                    resultArrey2[0] = arabTransRomDes.get(preResultArrey[0]);
                    resultArrey2[1] = arabTransRomEdin.get(preResultArrey[1]);

                    result = resultArrey2[0] + resultArrey2[1];
                    break;
                case (1):
                    result = arabTransRomEdin.get(preResultArrey[0]);
                    break;
            }
        } else if (expArrey[1].equals("-")) {
            Integer preResult = latino.get(expArrey[0]) - latino.get(expArrey[2]);
            String preResultString = Integer.toString(preResult);
            String[] preResultArrey = preResultString.split("");
            switch (preResultArrey.length) {
                case (2):
                    String[] resultArrey2 = new String[2];
                    resultArrey2[0] = arabTransRomDes.get(preResultArrey[0]);
                    resultArrey2[1] = arabTransRomEdin.get(preResultArrey[1]);
                    result = resultArrey2[0] + resultArrey2[1];
                case (1):
                    result = arabTransRomEdin.get(preResultArrey[0]);
                    break;
            }
        } else if (expArrey[1].equals("*")) {
            Integer preResult = latino.get(expArrey[0]) * latino.get(expArrey[2]);
            String preResultString = Integer.toString(preResult);
            String[] preResultArrey = preResultString.split("");
            switch (preResultArrey.length) {
                case (3):
                    result = "C";
                    break;
                case (2):
                    String[] resultArrey2 = new String[2];
                    resultArrey2[0] = arabTransRomDes.get(preResultArrey[0]);
                    resultArrey2[1] = arabTransRomEdin.get(preResultArrey[1]);
                    result = resultArrey2[0] + resultArrey2[1];
                    break;
                case (1):
                    result = arabTransRomEdin.get(preResultArrey[0]);
                    break;
            }
        } else if (expArrey[1].equals("/")) {
            Integer preResult = latino.get(expArrey[0]) / latino.get(expArrey[2]);
            String preResultString = Integer.toString(preResult);
            String[] preResultArrey = preResultString.split("");
            switch (preResultArrey.length) {
                case (2):
                    String[] resultArrey2 = new String[2];
                    resultArrey2[0] = arabTransRomDes.get(preResultArrey[0]);
                    resultArrey2[1] = arabTransRomEdin.get(preResultArrey[1]);
                    result = resultArrey2[0] + resultArrey2[1];
                    break;
                case (1):
                    result = arabTransRomEdin.get(preResultArrey[0]);
                    break;
            }
        }
        return result;
    }


    static HashMap<String, Integer> latino = new HashMap<>(Map.of("I", 1, "II", 2, "III", 3, "IV", 4, "V", 5,
            "VI", 6, "VII", 7, "VIII", 8, "IX", 9, "X", 10));
    /*static HashMap<String, Integer> cLatino = new HashMap<>(Map.of("X", 1, "XX", 2, "XXX", 3, "XL", 4, "L", 5,
            "LX", 6, "LXX", 7, "LXXX", 8, "XC", 9, "C", 1));
     */
    static HashMap<String, String> arabTransRomEdin = new HashMap<>(Map.of("1", "I", "2", "II", "3", "III",
            "4", "IV", "5", "V", "6", "VI", "7", "VII", "8", "VIII", "9", "IX", "0", ""));
    static HashMap<String, String> arabTransRomDes = new HashMap<>(Map.of("1", "X", "2", "XX", "3", "XXX",
            "4", "XL", "5", "L", "6", "LX", "7", "LXX", "8", "LXXX", "9", "XC", "10", "C"));

    public static void check(String[] expArrey) throws RuntimeException {
        if (expArrey.length != 3) {
            throw new NegativeArraySizeException("Не верный формат данных!");
        }
        try {
            Integer.parseInt(expArrey[0]);
            Integer.parseInt(expArrey[2]);

        } catch (Exception e) {
            numberFlag0 = latino.containsKey(expArrey[0]);
            numberFlag2 = latino.containsKey(expArrey[2]);
            if (numberFlag0 != numberFlag2) {
                throw new RuntimeException("Не верный формат данных!");
            }
        }
        if (numberFlag0 == true) {
            if ((expArrey[1].equals("/") && (latino.get(expArrey[0]) < latino.get(expArrey[2])))
            ||  (expArrey[1].equals("-") && (latino.get(expArrey[0]) <= latino.get(expArrey[2])))) {
                throw new RuntimeException("Некорректный результат!");
            }
        } else {
            if (!(expArrey[1].equals("+") || expArrey[1].equals("-") || expArrey[1].equals("*") || expArrey[1].equals("/"))) {
                throw new RuntimeException("Не верный формат данных!");
            }
            if (expArrey[1].equals("/") && expArrey[2].equals("0")) {
                throw new ArithmeticException("На ноль делить нельзя!");
            }
            if (expArrey[0].contains(".")) {
                throw new RuntimeException("Работает только для целых чисел!");
            }
            if (expArrey[0].contains(",")) {
                throw new RuntimeException("Работает только для целых чисел!");
            }
            if (expArrey[2].contains(".")) {
                throw new RuntimeException("Работает только для целых чисел!");
            }
            if (expArrey[2].contains(",")) {
                throw new RuntimeException("Работает только для целых чисел!");
            }
            if (Integer.parseInt(expArrey[0]) < 1) {
                throw new RuntimeException("Не верный формат данных!");
            }
            if (Integer.parseInt(expArrey[0]) > 10) {
                throw new RuntimeException("Не верный формат данных!");
            }
            if (Integer.parseInt(expArrey[2]) < 1) {
                throw new RuntimeException("Не верный формат данных!");
            }
            if (Integer.parseInt(expArrey[2]) > 10) {
                throw new RuntimeException("Не верный формат данных!");
            }

        }
    }
}

