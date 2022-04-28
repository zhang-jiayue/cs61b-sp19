class addStrings {
    public static String addStrings(String num1, String num2) {
        //failed solution
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int carry = 0;
        String resu = "";
        while (index1 >= 0 && index2 >= 0) {
            int temp = (num1.charAt(index1) - '0') + (num2.charAt(index2) - '0') + carry;
            carry = temp / 10;
            int remainder = temp % 10;
            resu = remainder + resu;
            index1--;
            index2--;
        }
        while (index1 >= 0) {
            int temp = (num1.charAt(index1) - '0') + carry;
            carry = temp / 10;
            int remainder = temp % 10;
            resu = remainder + resu;
            index1--;
        }
        while (index2 >= 0) {
            int temp = (num2.charAt(index2) - '0') + carry;
            carry = temp / 10;
            int remainder = temp % 10;
            resu = remainder + resu;
            index2--;
        }
        if (carry == 1) {
            resu = 1 + resu;
        }
        return resu;
    }

    public static void main(String[] args) {
        String input1 = "6913259244";
        String input2 = "71103343";

        System.out.println(addStrings(input1, input2));
    }
}
