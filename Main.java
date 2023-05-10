import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception{

        System.out.println("Введите выражение из двух арабских или римских чисел");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws Exception{
        int a;
        int b;
        String sign;
        String[] regex = input.split("[+\\-*/]");
        if (regex.length != 2) throw new Exception("Неккоректное выражение");
        sign = arithmetic(input);
        boolean roman;
        if (Roman.ifRoman(regex[0]) && Roman.ifRoman(regex[1])){
            a = Roman.toArabian(regex[0]);
            b = Roman.toArabian(regex[1]);
            roman = true;
        }
        else if (!Roman.ifRoman(regex[0]) && !Roman.ifRoman(regex[1])){
            a = Integer.parseInt(regex[0]);
            b = Integer.parseInt(regex[1]);
            roman = false;
        }
        else {
            throw new Exception("Оба числа должны быть либо арабскими, либо римскими");
        }
        if (a > 10 || b > 10) {
            throw new Exception("Введите числа от 1 до 10");
        }
        int arabian = transfer(a, b, sign);
        String result;
        if (roman) {
            if (arabian <= 0) {
                throw new Exception("Римское число не может быть отрицательным");
            }
            result = Roman.toRoman(arabian);
        } else {
            result = String.valueOf(arabian);
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
            default -> a / b;
        };
    }

}
class Roman {
    static String[] romanNumbers = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};

static boolean ifRoman(String spqr){
        for (int i = 0; i < romanNumbers.length; i++){
            if (spqr.equals(romanNumbers[i])) {
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