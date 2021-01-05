package geekbrains.thirdVebinar;

import org.omg.CORBA.Current;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final int SIZE = 3;
    private static char[][] map;
    private static Scanner scanner = new Scanner(System.in);
    private static ThreadLocalRandom random = ThreadLocalRandom.current();


    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            isArrayPositive();
            humanTurn();
            printMap();
            if (isWinSecond(DOT_X)) {
                System.out.println("Победил кожаный мешок.");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья.");
                break;
            }
            aiTurn();
            printMap();
            if (isWinSecond(DOT_O)) {
                System.out.println("Победил ИИ.");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья.");
                break;
            }
        }
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    private static void isArrayPositive() {
        if (SIZE < 3) {
            System.out.println("Минимальный размер поля 3х3");
            System.exit(1);
        }
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
        }
    }

    private static void printMap() {

        for (int i = 0; i < SIZE; i++) {
            System.out.println();
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void aiTurn() {
        int x;
        int y;
        System.out.println("Ход сверхмашины.");
        do {
            x = random.nextInt(0, SIZE);
            y = random.nextInt(0, SIZE);
        } while (!isCorrect(x, y));
        map[y][x] = DOT_O;
    }

    private static void humanTurn() {
        int x = -1;
        int y = -1;
        do {
            try {
                System.out.println("Введите координаты X и Y от 1 до 3: ");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Введите числа");
                scanner = new Scanner(System.in);
            }
        } while (!isCorrect(x, y));
        map[y][x] = DOT_X;
    }

    //Проверяет Х и Y на заданные границы поля и что слот пустой.

    private static boolean isCorrect(int x, int y) {
        return x >= 0 && x < SIZE
                && y >= 0 && y < SIZE
                && map[y][x] == DOT_EMPTY;
    }


    private static boolean isWin(char symbol) {
        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol)
            return true;
        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol)
            return true;
        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol)
            return true;
        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol)
            return true;
        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol)
            return true;
        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol)
            return true;
        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol)
            return true;
        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol)
            return true;

        return false;
    }

    private static boolean isWinSecond(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            for (char val : map[i]) {
                if (val==symbol)
                    return true;

                //НЕ УВИДЕЛ, ЧТО ДАТА СДАЧИ СТОИТ 5 ЯНВАРЯ, ХОТЯ ЗАНЯТИЙ И НЕТ.
            }

        }
        return false;
    }
}