package com.example.javabase.function;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/10 13:11
 */
public class PrinterDemo {
    public static void main(String[] args) {
        usePrinter(s-> System.out.println(s.toLowerCase()));
        PrintString printString = new PrintString();
        usePrinter(printString::printUpper);
        usePrinter(printString::setName);
    }
    private static void usePrinter(Printer printer) {
        printer.printUpperCase("Helloworld");
    }
}
