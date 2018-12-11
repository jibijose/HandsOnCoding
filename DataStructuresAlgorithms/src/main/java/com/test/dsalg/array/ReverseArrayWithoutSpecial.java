package com.test.dsalg.array;

public class ReverseArrayWithoutSpecial {

    public static void main(String[] args) {
        ReverseArrayWithoutSpecial reverseArrayWithoutSpecial = new ReverseArrayWithoutSpecial();
        reverseArrayWithoutSpecial.invoke();
    }

    private void invoke() {
        String strInout = "Ab,c,de!$";
        System.out.println("String is " + strInout);
        char[] chars = strInout.toCharArray();

        int numOfChars = chars.length;
        int left = 0;
        int right = numOfChars - 1;
        while (left < right) {
            if (!isCharacter(chars[left])) {
                left++;
                continue;
            }
            if (!isCharacter(chars[right])) {
                right--;
                continue;
            }

            swap(chars, left, right);
            left++;
            right--;
        }
        System.out.println("Reversed String is " + new String(chars));
    }

    private void swap(char[] chars, int left, int right) {
        char tempChar = chars[left];
        chars[left] = chars[right];
        chars[right] = tempChar;
    }

    private boolean isCharacter(char aChar) {
        if ((aChar >= 'a' && aChar <= 'z') || (aChar >= 'A' && aChar <= 'Z')) {
            return true;
        }
        return false;
    }


}
