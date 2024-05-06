import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static class MoreDataException extends Exception {
        public MoreDataException() {
            super("Введено больше данных, чем нужно");
        }
    }

    public static class LessDataException extends Exception {
        public LessDataException() {
            super("Введено меньше данных, чем нужно");
        }
    }

    public static class WrongTypeOfData extends Exception {
        public WrongTypeOfData() {
            super("Введен неверный формат данных");
        }
    }

    public static void main(String[] args) throws Exception {
        writeToFile();

    }

    public static void writeToFile() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите Фамилия Имя Отчество датарождения номертелефона пол, данные в произвольном порядке, разделенные пробелом");
        String data = scanner.nextLine();
        String[] newdata = data.split(" ");

        if (newdata.length > 6) {
            throw new MoreDataException();
        } else if (newdata.length < 6) {
            throw new LessDataException();
        }

        List<String> sorteddata = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            sorteddata.add("");
        }
        int count = 0;

        for (String el : newdata) {
            if (el.contains(".")) {
                sorteddata.set(3, el);
            } else if (el.contains("f") || el.contains("m")) {
                sorteddata.set(5, el);
            } else {
                sorteddata.set(count, el);
                if (count == 2)
                    count += 2;
                else
                    count++;
            }
        }
        try {
            Integer.parseInt(sorteddata.get(4));
        } catch (Exception e) {
            throw new WrongTypeOfData();
        }
        String[] date = sorteddata.get(3).split("\\.");
        if (date.length != 3 || date[0].length() != 2 || date[1].length() != 2 || date[2].length() != 4) {
            throw new WrongTypeOfData();
        }

        StringBuilder res = new StringBuilder();
        for (String el : sorteddata) {
            res.append(el + " ");
        }
        System.out.println(res);

    }
}