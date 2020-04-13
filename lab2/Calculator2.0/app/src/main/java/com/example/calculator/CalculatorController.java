package com.example.calculator;

public class CalculatorController {

    private CalculatorModel calculatorModel = new CalculatorModel();


    private StringBuilder inputStr = new StringBuilder();

    private Double memory = 0.0;

    public CalculatorController() {
    }

    public void onNumPressed(int buttonId) {
        int len = inputStr.length() -1;

       if(inputStr.length() < 14) {
           switch (buttonId){
               case R.id.zero:
                   if(inputStr.length() != 0) {
                       inputStr.append("0");
                   }
                   break;
               case R.id.one:
                   inputStr.append("1");
                   break;
               case R.id.two:
                   inputStr.append("2");
                   break;
               case R.id.three:
                   inputStr.append("3");
                   break;
               case R.id.four:
                   inputStr.append("4");
                   break;
               case R.id.five:
                   inputStr.append("5");
                   break;
               case R.id.six:
                   inputStr.append("6");
                   break;
               case R.id.seven:
                   inputStr.append("7");
                   break;
               case R.id.eight:
                   inputStr.append("8");
                   break;
               case R.id.nine:
                   inputStr.append("9");
                   break;
               case R.id.doobleZero:
                   inputStr.append("00");
                   break;
               case R.id.point:
                   String str = new String(inputStr);
                   if(str.indexOf('.') == -1) {
                       if (inputStr.length() == 0) {
                           inputStr.append("0");
                       }
                       inputStr.append(".");
                       break;
                   }
                   break;
               case R.id.plus:
                   if(inputStr.charAt(len) == '*' || inputStr.charAt(len) == '-' || inputStr.charAt(len) == '/' || inputStr.charAt(len) == '+'){
                       inputStr.setLength(inputStr.length() -1);
                   }
                   inputStr.append('+');
                   break;
               case R.id.minus:
                   if(inputStr.charAt(len) == '*' || inputStr.charAt(len) == '-' || inputStr.charAt(len) == '/' || inputStr.charAt(len) == '+'){
                       inputStr.setLength(inputStr.length() -1);
                   }
                   inputStr.append('-');
                   break;
               case R.id.multiply:
                   if(inputStr.charAt(len) == '*' || inputStr.charAt(len) == '-' || inputStr.charAt(len) == '/' || inputStr.charAt(len) == '+'){
                       inputStr.setLength(inputStr.length() -1);
                   }
                   inputStr.append('*');
                   break;
               case R.id.percent:
                   inputStr.append('%');
                   break;
               case R.id.sin:
                   inputStr.append("Sin(");
                   break;
               case R.id.cos:
                   inputStr.append("Cos(");
                   break;
               case R.id.bktOpen:
                   inputStr.append("(");
                   break;
               case R.id.bktClose:
                   inputStr.append(")");
                   break;
               case R.id.division:
                   if(inputStr.charAt(len) == '*' || inputStr.charAt(len) == '-' || inputStr.charAt(len) == '/' || inputStr.charAt(len) == '+'){
                       inputStr.setLength(inputStr.length() -1);
                   }
                   inputStr.append("/");
                   break;
           }
       }

    }

    public void onActionPressed(int actionId) {

        try {
            if (actionId == R.id.backspace) {
                if (inputStr.length() > 0) {
                    String str = new String(inputStr);
                    char symbol = str.charAt(str.length() -1);
                    if(symbol == 'n' || symbol == 's'){
                        inputStr.setLength(inputStr.length() - 3);
                    } else{
                        inputStr.setLength(inputStr.length() - 1);
                    }
                }
            }

            if (actionId == R.id.AC) {
                inputStr.setLength(0);
            }

            if (actionId == R.id.percent) {
                String str = new String(inputStr);
                int index = str.indexOf('*');
                double a = Double.parseDouble(str.substring(0, index));
                double b = Double.parseDouble(str.substring(index + 1, str.length()));
                inputStr.setLength(0);
                double result = a / 100 * b;
                result = result * 10000;
                int i = (int) Math.round(result);
                result = (double) i / 10000;
                inputStr.append(result);
            }

            try {
                if (actionId == R.id.equals) {
                    String str = new String(inputStr);
                    int index1 = str.indexOf("/0");
                    int index2 = str.indexOf("/0.");
                    if (index1 != -1 && index2 == -1) {
                        throw new ArithmeticException();
                    }
                    double result = calculatorModel.decide(str);
                    result = result * 10000;
                    int i = (int) Math.round(result);
                    result = (double) i / 10000;
                    inputStr.setLength(0);
                    inputStr.append(result);
                }
            }
            catch (ArithmeticException e){
                inputStr.setLength(0);
                inputStr.append("Divide by zero");
            }

            if (actionId == R.id.MC) {
                memory = 0.0;
            }

            if (actionId == R.id.MR) {
                inputStr.append(memory);
            }

            if (actionId == R.id.MMinus) {
                String str = new String(inputStr);
                memory -= Double.parseDouble(str);
            }

            if (actionId == R.id.MPlus) {
                String str = new String(inputStr);
                memory += Double.parseDouble(str);
            }
        }
        catch (Exception e){
            inputStr.setLength(0);
            inputStr.append("Error");
        }
        finally {

        }
    }

    public String getText() {
        return inputStr.toString();
    }
}
