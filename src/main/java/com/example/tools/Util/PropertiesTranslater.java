package com.example.tools.Util;

public class PropertiesTranslater {
    public static void main(String[] args) {
        if (args == null || args[0] == null) {
            System.out.println("__错误参数配置__");
            return;
        }
        if (args.length == 2 && args[1] != null && "-reverse".equals(args[1])) {
            String result = PropertiesTranslater.Unicode2GBK(args[0]);
            System.out.print(result);
        } else {
            String result = PropertiesTranslater.GBK2Unicode(args[0]);
            System.out.print(result);
        }
    }

    /**
     * 中文转unicode
     *
     * @param str
     * @return 反回unicode编码
     */
    public static String GBK2Unicode(String str) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char chr1 = (char) str.charAt(i);
            if (!PropertiesTranslater.isNeedConvert(chr1)) {
                result.append(chr1);
                continue;
            }
            result.append("//u" + Integer.toHexString((int) chr1));
        }
        return result.toString();
    }

    /**
     * unicode转中文
     *
     * @param str
     * @return 中文
     */
    public static String Unicode2GBK(String dataStr) {
        int index = 0;
        StringBuffer buffer = new StringBuffer();
        while (index < dataStr.length()) {
            if (!"//u".equals(dataStr.substring(index, index + 2))) {
                buffer.append(dataStr.charAt(index));
                index++;
                continue;
            }
            String charStr = "";
            charStr = dataStr.substring(index + 2, index + 6);
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(letter);
            index += 6;
        }
        return buffer.toString();
    }

    public static boolean isNeedConvert(char para) {
        return ((para & (0x00FF)) != para);
    }
}
