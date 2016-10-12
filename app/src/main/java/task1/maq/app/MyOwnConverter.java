package task1.maq.app;

import android.content.Context;

public class MyOwnConverter {

    private String[] teens;

    private String[][] words;

    private String thousand;

    public MyOwnConverter(Context context) {
        thousand = context.getString(R.string.num1000);

        teens = new String[]{
                context.getString(R.string.num10),
                context.getString(R.string.num11),
                context.getString(R.string.num12),
                context.getString(R.string.num13),
                context.getString(R.string.num14),
                context.getString(R.string.num15),
                context.getString(R.string.num16),
                context.getString(R.string.num17),
                context.getString(R.string.num18),
                context.getString(R.string.num19)
        };
        
        words = new String[][]{
                {
                        context.getString(R.string.num1),
                        context.getString(R.string.num2),
                        context.getString(R.string.num3),
                        context.getString(R.string.num4),
                        context.getString(R.string.num5),
                        context.getString(R.string.num6),
                        context.getString(R.string.num7),
                        context.getString(R.string.num8),
                        context.getString(R.string.num9)
                },
                {
                        context.getString(R.string.num10),
                        context.getString(R.string.num20),
                        context.getString(R.string.num30),
                        context.getString(R.string.num40),
                        context.getString(R.string.num50),
                        context.getString(R.string.num60),
                        context.getString(R.string.num70),
                        context.getString(R.string.num80),
                        context.getString(R.string.num90)
                },
                {
                        context.getString(R.string.num100),
                        context.getString(R.string.num200),
                        context.getString(R.string.num300),
                        context.getString(R.string.num400),
                        context.getString(R.string.num500),
                        context.getString(R.string.num600),
                        context.getString(R.string.num700),
                        context.getString(R.string.num800),
                        context.getString(R.string.num900)
                }
        };
    }

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
            return thousand;
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
