/*
 * @(#)Game.java 1.0 22.12.2016
 */

package ru.solpro.game.server.model;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class GameOnline {

    /**
     * Размер игрового поля.
     */
    private static final int FIELD_SIZE = 10;

    // константы направления корабля
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;

    /**
     * Массив для игрового поля игрока инициирующего текущий бой.
     */
    private int[][] arrayPlayer1;

    /**
     * Массив для игрового поля присоединившегося игрока.
     */
    private int[][] arrayPlayer2;

    /**
     * Признак хода player2 (false - ходит Player1)
     */
    private boolean player2Course;

    // Признак конца игры
    // (0-игра идет, 1-победил игрок,2-победил компьютер)
    private int gameStatus;

    // Конструктор класса
    public GameOnline() {
        //Создаем массив 10x10 - игровое поле игрока
        arrayPlayer1 = new int[FIELD_SIZE][FIELD_SIZE];
        arrayPlayer2 = new int[FIELD_SIZE][FIELD_SIZE];
    }

    /**
     * Запуск игры - начало игры
     */
    public void start() {
        //Очищаем игровое поле игрока
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                arrayPlayer1[i][j] = 0;
                arrayPlayer2[i][j] = 0;
            }
        }
        //Обнуляем признак чьей-то победы
        gameStatus = 0;
        //Передаем первый ход player1
        player2Course = false;
        //Расставляем корабли player1
        shipPlacement(arrayPlayer1);
        //Расставляем корабли player2
        shipPlacement(arrayPlayer2);
    }

    /**
     * Выстрел player1.
     * @param i
     * @param j
     */
    public void player1Shot(int i, int j) {
        // При выстреле прибавляем число 7
        arrayPlayer2[i][j] += 7;
        //Проверяем убит ли корабль
        checkShipDeath(arrayPlayer2, i, j);
        //Проверяем конец игры
        testEndGame();
        // Если был промах - передаем ход
        if (arrayPlayer2[i][j] < 8) {
            player2Course = true; // передаем ход player2
        }
    }

    /**
     * Выстрел player2.
     * @param i
     * @param j
     */
    public void player2Shot(int i, int j) {
        // При выстреле прибавляем число 7
        arrayPlayer1[i][j] += 7;
        //Проверяем убит ли корабль
        checkShipDeath(arrayPlayer1, i, j);
        //Проверяем конец игры
        testEndGame();
        // Если был промах - передаем ход
        if (arrayPlayer1[i][j] < 8) {
            player2Course = false; // передаем ход player1
        }
    }

    /**
     * Чей ход.
     * @return true - ход player2, false - ход player1.
     */
    public boolean isPlayer2Course() {
        return player2Course;
    }

    /**
     * Статус игры.
     * @return 0-игра идет, 1-победил player1, 2-победил player2
     */
    public int getGameStatus() {
        return gameStatus;
    }

    public int[][] getArrayPlayer1() {
        return arrayPlayer1;
    }

    public int[][] getArrayPlayer2() {
        return arrayPlayer2;
    }

    /**
     * Расстановка кораблей.
     */
    private void shipPlacement(int[][] array) {
        //Создаем один четырехпалубный корабль
        createShip(array, 4);
        //Создаем два трехпалубных корабля
        for (int i = 1; i <= 2; i++) {
            createShip(array, 3);
        }
        //Создаем три двухпалубных корабля
        for (int i = 1; i <= 3; i++) {
            createShip(array, 2);
        }
        //Создаем четыре однопалубных корабля
        for (int i = 1; i <= 4; i++) {
            createShip(array, 1);
        }
    }

    /**
     * Создание корабля с несколькими палубами.
     * @param array          массив для размещения
     * @param numberDecks    количество палуб
     */
    private void createShip(int[][] array, int numberDecks) {
        while (true) {
            boolean flag = false;
            // Координаты головы корабля
            int i = 0;
            int j = 0;
            // Создание первой палубы - головы корабля
            // Получение случайной строки
            i = (int) (Math.random() * FIELD_SIZE);
            // Получение случайной колонки
            j = (int) (Math.random() * FIELD_SIZE);
            // Выбираем случайное направление построения корабля
            // 0 - вверх, 1 -вправо, 2 - вниз, 3 - влево
            int direction = (int) (Math.random() * 4);
            if (isTestNewPaluba(array, i, j)) {
                switch (direction) {
                    case UP: // вверх
                        // Если можно расположить палубу
                        if (isTestNewPaluba(array, i - (numberDecks - 1), j))
                            flag = true;
                        break;
                    case RIGHT: // вправо
                        // Если можно расположить палубу
                        if (isTestNewPaluba(array, i, j + (numberDecks - 1)))
                            flag = true;
                        break;
                    case BOTTOM: // вниз
                        // Если можно расположить палубу
                        if (isTestNewPaluba(array, i + (numberDecks - 1), j))
                            flag = true;
                        break;
                    case LEFT: // влево
                        // Если можно расположить палубу
                        if (isTestNewPaluba(array, i, j - (numberDecks - 1)))
                            flag = true;
                        break;
                }
            }
            if (flag) {
                //Помещаем в ячейку число палуб
                array[i][j] = numberDecks;
                // Окружаем минус двойками
                setCellAround(array, i, j, -2);
                switch (direction) {
                    case UP:
                        for (int k = numberDecks - 1; k >= 1; k--) {
                            //Помещаем в ячейку число палуб
                            array[i - k][j] = numberDecks;
                            //Окружаем минус двойками
                            setCellAround(array, i - k, j, -2);
                        }
                        break;
                    case RIGHT:
                        for (int k = numberDecks - 1; k >= 1; k--) {
                            //Помещаем в ячейку число палуб
                            array[i][j + k] = numberDecks;
                            //Окружаем минус двойками
                            setCellAround(array, i, j + k, -2);
                        }
                        break;
                    case BOTTOM:
                        for (int k = numberDecks - 1; k >= 1; k--) {
                            //Помещаем в ячейку число палуб
                            array[i + k][j] = numberDecks;
                            //Окружаем минус двойками
                            setCellAround(array, i + k, j, -2);
                        }
                        break;
                    case LEFT:
                        for (int k = numberDecks - 1; k >= 1; k--) {
                            //Помещаем в ячейку число палуб
                            array[i][j - k] = numberDecks;
                            //Окружаем минус двойками
                            setCellAround(array, i, j - k, -2);
                        }
                        break;
                }
                break;
            }
        }
        //Конечное окружение
        setEndEnvironment(array);
    }

    /**
     * Запись значения в массив с проверкой границ массива.
     * @param array    массив
     * @param i        строка
     * @param j        столбец
     * @param value    значение
     */
    private void setArrayValue(int[][] array, int i, int j, int value) {
        // Если не происходит выход за границы массива
        if (isBoundsArray(i, j)) {
            // Записываем значение в массив
            array[i][j] = value;
        }
    }

    /**
     * Проверка невыхода за границы массива
     * @param i    строка
     * @param j    столбец
     * @return true - позиция корректна, иначе false
     */
    private boolean isBoundsArray(int i, int j) {
        if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
            return true;
        }
        return false;
    }

    /**
     * Установить один элемент окружения
     * @param array    массив
     * @param i        строка
     * @param j        столбец
     * @param val      значение
     */
    private void setOneElementEnvironment(int[][] array, int i, int j, int val) {
        // Если не происходит выход за пределы массива
        // и в ячейке нулевое значение
        if (isBoundsArray(i, j) && (array[i][j] == 0)) {
            // Устанавливаем необходимое значение
            setArrayValue(array, i, j, val);
        }
    }

    /**
     * Окружение одной ячейки вокруг
     * @param mas
     * @param i
     * @param j
     * @param val
     */
    private void setCellAround(int[][] mas, int i, int j, int val) {
        setOneElementEnvironment(mas, i-1, j-1, val); // сверху слева
        setOneElementEnvironment(mas, i-1, j, val); // сверху
        setOneElementEnvironment(mas, i-1, j+1, val); // сверху справа
        setOneElementEnvironment(mas, i, j+1, val); // справа
        setOneElementEnvironment(mas, i+1, j+1, val); // снизу справа
        setOneElementEnvironment(mas, i+1, j, val); // снизу
        setOneElementEnvironment(mas, i+1, j-1, val); // снизу слева
        setOneElementEnvironment(mas, i, j-1, val); // слева
    }

    /**
     * Конечное окружение
     * @param mas    массив
     */
    private void setEndEnvironment(int[][] mas) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                // Если значение элемента массива -2,
                // то заменяем его на -1
                if (mas[i][j] == -2) {
                    mas[i][j] = -1;
                }
            }
        }
    }

    /**
     * Проверка ячейки для возможности размещения в ней палубы корабля
     * @param array
     * @param i
     * @param j
     * @return
     */
    private boolean isTestNewPaluba(int[][] array, int i, int j) {
        // Если выход за границы массива
        if (!isBoundsArray(i, j)) {
            return false;
        }
        // Если в этой ячейке 0 или -2, то она нам подходит
        if ((array[i][j] == 0) || (array[i][j] == -2)) {
            return true;
        }
        return false;
    }

    /**
     * Проверка окончания игры.
     */
    private void testEndGame() {
        // Тестовое число = 15*4+16*2*3+17*3*2+18*4
        // Ситуация, когда все корабли убиты
        int testNumber = 330;
        int kolComp=0; // Сумма убитых палуб компьютера
        int kolPlay=0; // Сумма убитых палуб игрока
        // Перебираем все элементы сразу двух массивов
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                // Суммируем подбитые палубы игрока
                if (arrayPlayer1[i][j] >= 15) {
                    kolPlay += arrayPlayer1[i][j];
                }
                // Суммируем подбитые палубы компьютера
                if (arrayPlayer2[i][j] >= 15) {
                    kolComp += arrayPlayer2[i][j];
                }
            }
        }
        if (kolPlay == testNumber) {
            gameStatus = 2; // Если победил игрок
        } else if (kolComp == testNumber) {
            gameStatus = 1; // Если победил компьютер
        }
    }

    /**
     * Установить один элемент окружения подбитого корабля.
     * @param array
     * @param i
     * @param j
     */
    private void setPaddedCell(int[][] array, int i, int j) {
        // Если не происходит выход за пределы массива
        if (isBoundsArray(i, j)) {
            //Устанавливаем необходимое значение
            if ((array[i][j] == -1) || (array[i][j] == 6)) {
                array[i][j]--;
            }
        }
    }

    /**
     * Окружение одной ячейки подбитого вокруг
     * @param array
     * @param i
     * @param j
     */
    private void surroundPaddedCell(int[][] array, int i, int j) {
        setPaddedCell(array, i - 1, j - 1); // сверху слева
        setPaddedCell(array, i - 1, j); // сверху
        setPaddedCell(array, i - 1, j + 1); // сверху справа
        setPaddedCell(array, i, j + 1); // справа
        setPaddedCell(array, i + 1, j + 1); // снизу справа
        setPaddedCell(array, i + 1, j); // снизу
        setPaddedCell(array, i + 1, j - 1); // снизу слева
        setPaddedCell(array, i, j - 1); // слева
    }

//    /**
//     * Выстрел компьютера -
//     * возвращает истину - если попал
//     * @return
//     */
//    private boolean isPcShot() {
//        // Признак попадания в цель
//        boolean result = false;
//        // Признак выстрела в раненый
//        // корабль
//        boolean flag = false;
//        _for1:
//        //Пробегаем все игровое поле игрока
//        for (int i = 0; i < FIELD_SIZE; i++) {
//            for (int j = 0; j < FIELD_SIZE; j++) {
//                // Если находим раненую палубу
//                if ((arrayPlayer1[i][j] >= 9) && (arrayPlayer1[i][j] <= 11))
//                {
//                    flag = true;
//                    // ячейка сверху
//                    // Проверяем, что можно сделать выстрел
//                    if (isBoundsArray(i - 1, j)
//                            && (arrayPlayer1[i - 1][j] <= 4)
//                            && (arrayPlayer1[i - 1][j] != -2)) {
//                        //делаем выстрел
//                        arrayPlayer1[i - 1][j] += 7;
//                        //проверяем, что убит
//                        checkShipDeath(arrayPlayer1, i - 1, j);
//                        // если произошло попадание
//                        if (arrayPlayer1[i - 1][j] >= 8) {
//                            result = true;
//                        }
//                        //прерываем сразу все циклы
//                        break _for1;
//                    } else if (isBoundsArray(i + 1, j)
//                            && (arrayPlayer1[i + 1][j] <= 4)
//                            && (arrayPlayer1[i + 1][j] != -2)) {
//                        // ячейка снизу
//                        // Проверяем, что можно сделать выстрел
//                        //делаем выстрел
//                        arrayPlayer1[i + 1][j] += 7;
//                        //проверяем, что убит
//                        checkShipDeath(arrayPlayer1, i + 1, j);
//                        // если произошло попадание
//                        if (arrayPlayer1[i + 1][j] >= 8) result = true;
//                        //прерываем сразу все циклы
//                        break _for1;
//                    }
//                    // ячейка слева
//                    // Проверяем, что можно сделать выстрел
//                    if (isBoundsArray(i, j - 1)
//                            && (arrayPlayer1[i][j - 1] <= 4)
//                            && (arrayPlayer1[i][j - 1] != -2)) {
//                        //делаем выстрел
//                        arrayPlayer1[i][j - 1] += 7;
//                        //проверяем, что убит
//                        checkShipDeath(arrayPlayer1, i, j - 1);
//                        // если произошло попадание
//                        if (arrayPlayer1[i][j - 1] >= 8) {
//                            result = true;
//                        }
//                        //прерываем сразу все циклы
//                        break _for1;
//                    } else if (isBoundsArray(i, j + 1)
//                            && (arrayPlayer1[i][j + 1] <= 4)
//                            && (arrayPlayer1[i][j + 1] != -2)) {
//                        // ячейка справа
//                        // Проверяем, что можно сделать выстрел
//                        //делаем выстрел
//                        arrayPlayer1[i][j + 1] += 7;
//                        //проверяем, что убит
//                        checkShipDeath(arrayPlayer1, i, j + 1);
//                        // если произошло попадание
//                        if (arrayPlayer1[i][j + 1] >= 8) {
//                            result = true;
//                        }
//                        //прерываем сразу все циклы
//                        break _for1;
//                    }
//                }
//            }
//        }
//        // если не было выстрела в раненую палубу
//        if (flag == false) {
//            // делаем 100 случайных попыток выстрела
//            // в случайное место
//            for (int l = 1; l <= 100; l++) {
//                // Находим случайную позицию на игровом поле
//                int i = (int) (Math.random() * FIELD_SIZE);
//                int j = (int) (Math.random() * FIELD_SIZE);
//                // Проверяем, что можно сделать выстрел
//                if ((arrayPlayer1[i][j] <= 4)
//                        && (arrayPlayer1[i][j] != -2)) {
//                    // делаем выстрел
//                    arrayPlayer1[i][j] += 7;
//                    // проверяем, что убит
//                    checkShipDeath(arrayPlayer1, i, j);
//                    // если произошло попадание
//                    if (arrayPlayer1[i][j] >= 8)
//                        result = true;
//                    // выстрел произошел
//                    flag = true;
//                    // прерываем цикл
//                    break;
//                }
//            }
//            // если выстрела еще не было
//            if (flag == false) {
//                //начинаем пробегать весь массив от начала до конца
//                _for2:
//                for (int i = 0; i < FIELD_SIZE; i++) {
//                    for (int j = 0; j < FIELD_SIZE; j++) {
//                        // Проверяем, что можно сделать выстрел
//                        if ((arrayPlayer1[i][j] <= 4)
//                                && (arrayPlayer1[i][j] != -2)) {
//                            // делаем выстрел
//                            arrayPlayer1[i][j] += 7;
//                            // проверяем, что убит
//                            checkShipDeath(arrayPlayer1, i, j);
//                            // если произошло попадание
//                            if (arrayPlayer1[i][j] >= 8)
//                                result = true;
//                            // прерываем сразу все циклы
//                            break _for2;
//                        }
//                    }
//                }
//            }
//        }
//        //проверяем конец игры
//        testEndGame();
//        //возвращаем результат
//        return result;
//    }

    /**
     * Проверка убит ли корабль.
     * @param array    массив
     * @param i        строка
     * @param j        столбец
     */
    private void checkShipDeath(int[][] array, int i, int j) {
        //Если однопалубный
        if (array[i][j]==8) {
            // делаем выстрел
            array[i][j] += 7;
            // окружаем убитый корабль
            surroundPaddedCell(array, i, j);
        } else if (array[i][j] == 9) { // Если двухпалубный
            analysisShipDeath(array, i, j, 2);
        } else if (array[i][j] == 10) { // Если трехпалубный
            analysisShipDeath(array, i, j, 3);
        } else if (array[i][j] == 11) { // Если четырехпалубный
            analysisShipDeath(array, i, j, 4);
        }
    }

    /**
     * Анализ убитого корабля.
     * @param array
     * @param i
     * @param j
     * @param numberDecks
     */
    private void analysisShipDeath(int[][] array, int i, int j, int numberDecks) {
        // Количество раненых палуб
        int numberWoundedDecks=0;
        // Выполняем подсчет раненых палуб
        for (int k = i - (numberDecks - 1); k <= i + (numberDecks - 1); k++) {
            for (int g = j - (numberDecks - 1); g <= j + (numberDecks - 1); g++) {
                // Если это палуба раненого корабля
                if ((isBoundsArray(k, g)) && (array[k][g] == numberDecks + 7)) {
                    numberWoundedDecks++;
                }
            }
        }
        // Если количество раненых палуб совпадает с количеством палуб
        // корабля, то он убит - прибавляем число7
        if (numberWoundedDecks == numberDecks) {
            for (int k = i - (numberDecks - 1); k <= i + (numberDecks - 1); k++) {
                for (int g = j - (numberDecks - 1); g <= j + (numberDecks - 1); g++) {
                    // Если это палуба раненого корабля
                    if ((isBoundsArray(k, g)) && (array[k][g] == numberDecks + 7)) {
                        // помечаем палубой убитого корабля
                        array[k][g] += 7;
                        // окружаем палубу убитого корабля
                        surroundPaddedCell(array, k, g);
                    }
                }
            }
        }
    }
}
