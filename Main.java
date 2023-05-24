import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("Введите выражение из двух арабских или римских чисел");
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.nextLine();
        System.out.println(calc(exp));
    }
    public static String calc(String input) throws Exception{
        int a,b;
        String sign;
        String[] expression = input.split("[+\\-*/]");
        if (expression.length != 2) throw new Exception("Неккоректное выражение");
        sign = arithmetic(input);
        boolean roman;
        if (Roman.ifRoman(expression[0]) && Roman.ifRoman(expression[1])){
            a = Roman.toArabian(expression[0]);
            b = Roman.toArabian(expression[1]);
            roman = true;
        }
        else if (!Roman.ifRoman(expression[0]) && !Roman.ifRoman(expression[1])){
            a = Integer.parseInt(expression[0]);
            b = Integer.parseInt(expression[1]);
            roman = false;
        }
        else {
            throw new Exception("Оба числа должны быть либо арабскими, либо римскими");
        }
        if (a > 10 || b > 10) {
            throw new Exception("Введите числа от 1 до 10");
        }
        int arabic = transfer(a, b, sign);
        String result;
        if (roman) {
            if (arabic < 1) {
                throw new Exception("Римское число не может быть отрицательным или нулевым");
            }
            result = Roman.toRoman(arabic);
        } else {
            result = String.valueOf(arabic);
        }
        return result;
    }
    static String arithmetic(String input){
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }
    static int transfer(int a, int b, String sign){

        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;};
    }
}
class Roman {
    static String[] romanNumbers = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

static boolean ifRoman(String spqr){
    for (String romanNumber : romanNumbers) {
        if (spqr.equals(romanNumber)) {
            return true;
        }
    }
        return false;
    }
static int toArabian(String roman){
        for (int i = 0; i < romanNumbers.length; i++){
            if (roman.equals(romanNumbers[i])){
                return i;
            }
        }
        return -1;
    }
static String toRoman(int arabian) {
        return romanNumbers[arabian];
    }
}
