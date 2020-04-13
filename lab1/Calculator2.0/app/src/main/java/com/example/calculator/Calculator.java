package com.example.calculator;

import java.util.Stack;

public class Calculator {

    public Calculator() {

    }

    public double decide (String expression){
        String prepared = preparingExpression(expression);
        String rpn = expressionToRPN(prepared);
        return rpnToAnswer(rpn);
    }

    private String preparingExpression (String expression){

        String expression_ = expression.replaceFirst("Cos", "c");
        expression_ = expression_.replaceFirst("Sin", "s");

        String preparedExpression = new String();

        for(int token = 0; token < expression_.length(); token++){
            char symbol = expression_.charAt(token);

            if(symbol == '-') {
                if(token == 0)
                    preparedExpression += '0';
                else if(expression_.charAt(token - 1) == '(')
                    preparedExpression += '0';
            }
            preparedExpression += symbol;

            if(symbol != '-' && symbol != '+' && symbol != '/' && symbol != 's' && symbol != 'c' && symbol != '*') {
                if(token < expression_.length() -1){
                    char symbolNext = expression_.charAt(token + 1);
                    //char symbolPrev = expression_.charAt(token - 1);
                    if(symbol == '(' && symbolNext == '('){
                       // preparedExpression = preparedExpression;
                    } else if(symbolNext == 's' || symbolNext == 'c' || symbolNext == '('){
                        preparedExpression += '*';
                    }
                }

            }

        }

        return preparedExpression;
    }

    private String expressionToRPN (String expr){
        String current = "";
        Stack<Character> stack = new Stack<>();
        int priority;

        for(int i = 0; i < expr.length(); i++){
            priority = getPriority(expr.charAt(i));

            switch (priority){
                case 0:
                    current += expr.charAt(i);
                    break;
                case 1:
                    stack.push(expr.charAt(i));
                    break;
                case -1:
                    current += ' ';
                    while (getPriority(stack.peek()) != 1) {
                        current += stack.pop();
                    }
                    stack.pop();
                    break;
            }

            if(priority > 1) {
                current += ' ';

                while (!stack.empty()){
                    if(getPriority(stack.peek()) >= priority){
                        current += stack.pop();
                    } else break;
                }

                stack.push(expr.charAt(i));
            }

        }

        while(!stack.empty()) {
            current += stack.pop();
        }

        return current;
    }

    private double rpnToAnswer (String rpn){
        String operand;
        Stack<Double> stack = new Stack<>();

        for(int i = 0; i < rpn.length(); i++){
            if(rpn.charAt(i) == ' ') continue;
            if(getPriority(rpn.charAt(i)) == 0){
                operand = new String();

                while(rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0 ){
                    operand += rpn.charAt(i++);
                    if(i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand));

            }
            try{
                if(getPriority(rpn.charAt(i)) > 1) {
                    double a = 0, b = 0;
                    if (stack.size() == 1){
                        a = stack.pop();
                    } else {
                        a = stack.pop();
                        if(stack.size() != 0){
                            b = stack.pop();
                        } else{
                            b = a;
                            a = 0;
                        }
                    }

                    char s = rpn.charAt(i);
                    if(rpn.charAt(i) == '+') stack.push(b + a);
                    if(rpn.charAt(i) == '-') stack.push(b - a);
                    if(rpn.charAt(i) == '*') {
                        double result = b * a;
                        result *= 100;
                        int i_ = (int) Math.round(result);
                        result = (double) i_ / 100;
                        stack.push(result);
                    }
                    if(rpn.charAt(i) == '/') {
                        double result = 0;
                        if(a != 0){
                            result = b / a;
                            result *= 1000;
                            int i_ = (int) Math.round(result);
                            result = (double) i_ / 1000;
                            stack.push(result);
                        }
                    }
                    if(rpn.charAt(i) == 's') {
                        stack.push(b);
                        double result = 0;
                        if(a != 0){
                            result = Math.sin(Math.toRadians(a));
                            result *= 10000;
                            int i_ = (int) Math.round(result);
                            result = (double) i_ / 10000;
                            stack.push(result);
                        }
                        //stack.push();
                    }
                    if(rpn.charAt(i) == 'c') {
                        stack.push(b);
                        double result = 0;
                        if(a != 0){
                            result = Math.cos(Math.toRadians(a));
                            result *= 10000;
                            int i_ = (int) Math.round(result);
                            result = (double) i_ / 10000;
                            stack.push(result);
                        }
                    }

                }
            }
            catch (Exception e){
                return stack.pop();
            }

        }
        return stack.pop();
    }

    private int getPriority(char token){
        if (token == 's' || token == 'c') return 4;
        else if(token == '*' || token == '/') return 3;
        else if(token == '+' || token == '-') return 2;
        else if(token == '(' ) return 1;
        else if(token == ')') return -1;
        else return 0;
    }
}
