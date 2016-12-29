/*
 * @(#)Game.java 1.0 22.12.2016
 */

package ru.solpro.game.battleship;

/**
 * Created by Администратор on 22.12.2016.
 *
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Game {

    /**
     * Массив для игрового поля игрока.
     */
    private int[][] arrayPlay;

    /**
     * Массив для игрового поля компьютера.
     */
    private int[][] arrayComp;

    //Признак хода компьютера (false - ходит игрок)
    private boolean computerCourse;

    // Признак конца игры
    // (0-игра идет, 1-победил игрок,2-победил компьютер)
    private int gameStatus;

    // Конструктор класса
    public Game() {
        //Создаем массив 10x10 - игровое поле игрока
        arrayPlay = new int[10][10];
        arrayComp = new int[10][10];
    }

    /**
     * Запуск игры - начало игры
     */
    public void start() {
        //Очищаем игровое поле игрока
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                arrayPlay[i][j] = 0;
                arrayComp[i][j] = 0;
            }
        }
        //Обнуляем признак чьей-то победы
        gameStatus = 0;
        //Передаем первый ход игроку
        computerCourse = false;
        //Расставляем корабли игрока
        shipPlacement(arrayPlay);
        //Расставляем корабли компьютера
        shipPlacement(arrayComp);
    }

    /**
     * Чей ход.
     * @return true - ход компьютера, false - ход игрока.
     */
    public boolean isComputerCourse() {
        return computerCourse;
    }

    /**
     * Статус игры.
     * @return 0-игра идет, 1-победил игрок, 2-победил компьютер
     */
    public int getGameStatus() {
        return gameStatus;
    }

    public int[][] getArrayComp() {
        return arrayComp;
    }

    public int[][] getArrayPlay() {
        return arrayPlay;
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
        //Глухой цикл
        while (true) {
            boolean flag = false;
            // Координаты головы корабля
            int i = 0;
            int j = 0;
            // Создание первой палубы - головы корабля
            // Получение случайной строки
            i = (int) (Math.random() * 10);
            // Получение случайной колонки
            j = (int) (Math.random() * 10);
            // Выбираем случайное направление построения корабля
            // 0 - вверх, 1 -вправо, 2 - вниз, 3 - влево
            int napr = (int) (Math.random() * 4);
            if (testNewPaluba(array, i, j)) {
                if (napr == 0) {// вверх
                    // Если можно расположить палубу
                    if (testNewPaluba(array, i - (numberDecks - 1), j))
                        flag = true;
                } else if (napr == 1) {// вправо
                    // Если можно расположить палубу
                    if (testNewPaluba(array, i, j + (numberDecks - 1)))
                        flag = true;
                } else if (napr == 2) {// вниз
                    // Если можно расположить палубу
                    if (testNewPaluba(array, i + (numberDecks - 1), j))
                        flag = true;
                } else if (napr == 3) {// влево
                    // Если можно расположить палубу
                    if (testNewPaluba(array, i, j - (numberDecks - 1)))
                        flag = true;
                }
            }
            if (flag) {
                //Помещаем в ячейку число палуб
                array[i][j] = numberDecks;
                // Окружаем минус двойками
                setCellAround(array, i, j, -2);
                if (napr == 0) {
                    // вверх
                    for (int k = numberDecks - 1; k >= 1; k--) {
                        //Помещаем в ячейку число палуб
                        array[i -k][j] = numberDecks;
                        //Окружаем минус двойками
                        setCellAround(array, i - k, j, -2);
                    }
                } else if (napr == 1) {
                    // вправо
                    for (int k = numberDecks - 1; k >= 1; k--) {
                        //Помещаем в ячейку число палуб
                        array[i][j + k] = numberDecks;
                        //Окружаем минус двойками
                        setCellAround(array, i, j + k, -2);
                    }
                } else if (napr == 2) {
                    // вниз
                    for (int k = numberDecks - 1; k >= 1; k--) {
                        //Помещаем в ячейку число палуб
                        array[i + k][j] = numberDecks;
                        //Окружаем минус двойками
                        setCellAround(array, i + k, j, -2);
                    }
                } else {
                    // влево
                    for (int k = numberDecks - 1; k >= 1; k--) {
                        //Помещаем в ячейку число палуб
                        array[i][j -k] = numberDecks;
                        //Окружаем минус двойками
                        setCellAround(array, i, j - k, -2);
                    }
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
        if (testArrayPosition(i, j)) {
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
    private boolean testArrayPosition(int i, int j) {
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
        if (testArrayPosition(i, j) && (array[i][j] == 0)) {
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
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
     * @param mas
     * @param i
     * @param j
     * @return
     */
    private boolean testNewPaluba(int[][] mas, int i, int j) {
        // Если выход за границы массива
        if (!testArrayPosition(i, j)) {
            return false;
        }
        // Если в этой ячейке 0 или -2, то она нам подходит
        if ((mas[i][j] == 0) || (mas[i][j] == -2)) {
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Суммируем подбитые палубы игрока
                if (arrayPlay[i][j] >= 15) {
                    kolPlay += arrayPlay[i][j];
                }
                // Суммируем подбитые палубы компьютера
                if (arrayComp[i][j] >= 15) {
                    kolComp += arrayComp[i][j];
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
     * @param mas
     * @param i
     * @param j
     */
    private void setOkrPodbit(int[][] mas, int i, int j) {
        // Если не происходит выход за пределы массива
        if (testArrayPosition(i, j)) {
            //Устанавливаем необходимое значение
            if ((mas[i][j] == -1) || (mas[i][j] == 6)) {
                mas[i][j]--;
            }
        }
    }

    /**
     * Окружение одной ячейки подбитого вокруг
     * @param mas
     * @param i
     * @param j
     */
    private void okrPodbit(int[][] mas, int i, int j) {
        setOkrPodbit(mas, i - 1, j - 1); // сверху слева
        setOkrPodbit(mas, i - 1, j); // сверху
        setOkrPodbit(mas, i - 1, j + 1); // сверху справа
        setOkrPodbit(mas, i, j + 1); // справа
        setOkrPodbit(mas, i + 1, j + 1); // снизу справа
        setOkrPodbit(mas, i + 1, j); // снизу
        setOkrPodbit(mas, i + 1, j - 1); // снизу слева
        setOkrPodbit(mas, i, j - 1); // слева
    }

    /**
     * Выстрел компьютера -
     * возвращает истину - если попал
     * @return
     */
    private boolean pcShot() {
        // Признак попадания в цель
        boolean result = false;
        // Признак выстрела в раненый
        // корабль
        boolean flag = false;
        _for1:
        //Пробегаем все игровое поле игрока
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Если находим раненую палубу
                if ((arrayPlay[i][j] >= 9) && (arrayPlay[i][j] <= 11))
                {
                    flag = true;
                    // ячейка сверху
                    // Проверяем, что можно сделать выстрел
                    if (testArrayPosition(i - 1, j)
                            && (arrayPlay[i - 1][j] <= 4)
                            && (arrayPlay[i - 1][j] != -2)) {
                        //делаем выстрел
                        arrayPlay[i - 1][j] += 7;
                        //проверяем, что убит
                        checkShipDeath(arrayPlay, i - 1, j);
                        // если произошло попадание
                        if (arrayPlay[i - 1][j] >= 8) {
                            result = true;
                        }
                        //прерываем сразу все циклы
                        break _for1;
                    } else if (testArrayPosition(i + 1, j)
                            && (arrayPlay[i + 1][j] <= 4)
                            && (arrayPlay[i + 1][j] != -2)) {
                        // ячейка снизу
                        // Проверяем, что можно сделать выстрел
                        //делаем выстрел
                        arrayPlay[i + 1][j] += 7;
                        //проверяем, что убит
                        checkShipDeath(arrayPlay, i + 1, j);
                        // если произошло попадание
                        if (arrayPlay[i + 1][j] >= 8) result = true;
                        //прерываем сразу все циклы
                        break _for1;
                    }
                    // ячейка слева
                    // Проверяем, что можно сделать выстрел
                    if (testArrayPosition(i, j - 1)
                            && (arrayPlay[i][j - 1] <= 4)
                            && (arrayPlay[i][j - 1] != -2)) {
                        //делаем выстрел
                        arrayPlay[i][j - 1] += 7;
                        //проверяем, что убит
                        checkShipDeath(arrayPlay, i, j - 1);
                        // если произошло попадание
                        if (arrayPlay[i][j - 1] >= 8) {
                            result = true;
                        }
                        //прерываем сразу все циклы
                        break _for1;
                    } else if (testArrayPosition(i, j + 1)
                            && (arrayPlay[i][j + 1] <= 4)
                            && (arrayPlay[i][j + 1] != -2)) {
                        // ячейка справа
                        // Проверяем, что можно сделать выстрел
                        //делаем выстрел
                        arrayPlay[i][j + 1] += 7;
                        //проверяем, что убит
                        checkShipDeath(arrayPlay, i, j + 1);
                        // если произошло попадание
                        if (arrayPlay[i][j + 1] >= 8) {
                            result = true;
                        }
                        //прерываем сразу все циклы
                        break _for1;
                    }
                }
            }
        }
        // если не было выстрела в раненую палубу
        if (flag == false) {
            // делаем 100 случайных попыток выстрела
            // в случайное место
            for (int l = 1; l <= 100; l++) {
                // Находим случайную позицию на игровом поле
                int i = (int) (Math.random() * 10);
                int j = (int) (Math.random() * 10);
                // Проверяем, что можно сделать выстрел
                if ((arrayPlay[i][j] <= 4)
                        && (arrayPlay[i][j] != -2)) {
                    // делаем выстрел
                    arrayPlay[i][j] += 7;
                    // проверяем, что убит
                    checkShipDeath(arrayPlay, i, j);
                    // если произошло попадание
                    if (arrayPlay[i][j] >= 8)
                        result = true;
                    // выстрел произошел
                    flag = true;
                    // прерываем цикл
                    break;
                }
            }
            // если выстрела еще не было
            if (flag == false) {
                //начинаем пробегать весь массив от начала до конца
                _for2:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        // Проверяем, что можно сделать выстрел
                        if ((arrayPlay[i][j] <= 4)
                                && (arrayPlay[i][j] != -2)) {
                            // делаем выстрел
                            arrayPlay[i][j] += 7;
                            // проверяем, что убит
                            checkShipDeath(arrayPlay, i, j);
                            // если произошло попадание
                            if (arrayPlay[i][j] >= 8)
                                result = true;
                            // прерываем сразу все циклы
                            break _for2;
                        }
                    }
                }
            }
        }
        //проверяем конец игры
        testEndGame();
        //возвращаем результат
        return result;
    }

    /**
     * Выстрел игрока.
     * @param i
     * @param j
     */
    public void playerShot(int i, int j) {
        // При выстреле прибавляем число 7
        arrayComp[i][j] += 7;
        //Проверяем убит ли корабль
        checkShipDeath(arrayComp, i, j);
        //Проверяем конец игры
        testEndGame();
        // Если был промах - передаем ход компьютеру
        if (arrayComp[i][j] < 8) {
            computerCourse = true; // передаем ход компьютеру
            // Ходит компьютер - пока попадает в цель
            while (computerCourse) {
                computerCourse = pcShot();
            }
        }
    }

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
            okrPodbit(array, i, j);
        } else if (array[i][j]==9) { // Если двухпалубный
            analysisShipDeath(array, i, j, 2);
        } else if (array[i][j]==10) { // Если трехпалубный
            analysisShipDeath(array, i, j, 3);
        } else if (array[i][j]==11) { // Если четырехпалубный
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
        //Количество раненых палуб
        int numberWoundedDecks=0;
        //Выполняем подсчет раненых палуб
        for (int k=i-(numberDecks-1);k<=i+(numberDecks-1);k++) {
            for (int g=j-(numberDecks-1);g<=j+(numberDecks-1);g++) {
                // Если это палуба раненого корабля
                if (testArrayPosition(k, g)&&(array[k][g]==numberDecks+7)) {
                    numberWoundedDecks++;
                }
            }
        }
        // Если количество раненых палуб совпадает с количеством палуб
        //корабля, то он убит - прибавляем число7
        if (numberWoundedDecks == numberDecks) {
            for (int k=i-(numberDecks-1);k<=i+(numberDecks-1);k++) {
                for (int g=j-(numberDecks-1);g<=j+(numberDecks-1);g++) {
                    // Если это палуба раненого корабля
                    if (testArrayPosition(k, g)&&(array[k][g]==numberDecks+7)) {
                        // помечаем палубой убитого корабля
                        array[k][g] += 7;
                        // окружаем палубу убитого корабля
                        okrPodbit(array, k, g);
                    }
                }
            }
        }
    }
}
