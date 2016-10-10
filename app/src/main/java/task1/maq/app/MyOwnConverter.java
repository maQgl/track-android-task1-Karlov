package task1.maq.app;

public class MyOwnConverter {

    private String[] teens = {
            "десять",
            "одиннадцать",
            "двенадцать",
            "тринадцать",
            "четырнадцать",
            "пятнадцать",
            "шестнадцать",
            "семнадцать",
            "восемнадцать",
            "девятнадцать"
    };

    private String[][] words = {
            {
                    "один",
                    "два",
                    "три",
                    "четыре",
                    "пять",
                    "шесть",
                    "семь",
                    "восемь",
                    "девять"
            },
            {
                    "десять",
                    "двадцать",
                    "тридцать",
                    "сорок",
                    "пятьдесят",
                    "шестьдесят",
                    "семьдесят",
                    "восемьдесят",
                    "девяносто"
            },
            {
                    "сто",
                    "двести",
                    "триста",
                    "четыреста",
                    "пятьсот",
                    "шестьсот",
                    "семьсот",
                    "восемьсот",
                    "девятьсот"
            }
    };

    private String checkNum(int num, int pos) {
        int val = num / (int) Math.pow(10, pos) - num / (int) Math.pow(10, pos + 1) * 10;
        if (val == 0) {
            return null;
        }
        return words[pos][val-1];
    }

    private String checkTeens(int num) {
        if ((num / 10 - num / 100 * 10) != 1) {
            return null;
        }
        int val = num - num / 10 * 10;
        return teens[val];
    }

    public String convertNumber(int num) {
        if (num == 1000) {
            return "тысяча";
        }
        if (num > 1000 || num < 1) {
            return null;
        }
        String res = checkNum(num, 2);
        String teens = checkTeens(num);
        if (teens != null) {
            if (res != null) {
                res += " " + teens;
            } else {
                res = teens;
            }
        } else {
            if (res != null && checkNum(num, 1) != null) {
                res += " " + checkNum(num, 1);
            } else if (checkNum(num, 1) != null){
                res = checkNum(num, 1);
            }
            if (res != null && checkNum(num, 0) != null) {
                res += " " + checkNum(num, 0);
            } else if (checkNum(num, 0) != null){
                res = checkNum(num, 0);
            }
        }
        return  res;
    }
}
