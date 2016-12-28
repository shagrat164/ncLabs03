/*
 * @(#)Battle.java 1.0 22.12.2016
 */

package ru.solpro.game.battleship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Protsvetov Danila
 * @version 1.0
 */
public class Battle extends JPanel {
    // Таймера отрисовки и изменения логики игры
    private Timer tmDraw;
    // Изображения, используемые в игре
    private Image fon, paluba, ubit, ranen, end1, end2, bomba;
    // Две кнопки
    private JButton btn1,btn2;
    // Переменная для реализации логики игры
    private Game myGame;
    // Координаты курсора мыши
    private int mX, mY;

    public void setmX(int mX) {
        this.mX = mX;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }

    public Game getMyGame() {
        return myGame;
    }

//    public class myMouse1 implements MouseListener {
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            for (int i = 0; i < myGame.masComp.length; i++) {
//                for (int j = 0; j < myGame.masComp[i].length; j++) {
//                    System.out.print(myGame.masComp[i][j] + "\t\t");
//                }
//                System.out.println();
//            }
//            System.out.println("===========================");
//        }
//
//        // При нажатии кнопки мыши
//        @Override
//        public void mousePressed(MouseEvent e) {
//            // Если сделано одиночное нажатие левой клавишей мыши
//            if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
//                // Получаем текущие координаты курсора мыши
//                mX = e.getX();
//                mY = e.getY();
//                // Если курсор мыши внутри игрового поля компьютера
//                if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
//                    // Если не конец игры и ход игрока
//                    if ((myGame.endg == 0) && (myGame.compHod == false)) {
//                        // Вычисляем номер строки в массиве
//                        int i = (mY - 100) / 30;
//                        // Вычисляем номер элемента в строке в массиве
//                        int j = (mX - 100) / 30;
//                        // Если ячейка подходит для выстрела
//                        if (myGame.masComp[i][j] <= 4 && myGame.masComp[i][j] >= -1)
//                            // Производим выстрел
//                            myGame.vistrelPlay(i, j);
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {}
//
//        @Override
//        public void mouseEntered(MouseEvent e) {}
//
//        @Override
//        public void mouseExited(MouseEvent e) {}
//    }
//
//    public class myMouse2 implements MouseMotionListener {
//
//        @Override
//        public void mouseDragged(MouseEvent e) {}
//
//        // При перемещении курсора мыши
//        @Override
//        public void mouseMoved(MouseEvent e) {
//            // Получаем координаты курсора
//            mX = e.getX();
//            mY = e.getY();
//            // Если курсор в области поля игрока
//            if ((mX >= 100) && (mY >= 100) && (mX <= 400) && (mY <= 400)) {
//                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
//            } else {
//                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//            }
//        }
//    }

    public Battle() {
        myGame = new Game();
        myGame.start();
        try {
            fon = ImageIO.read(new File("res\\fon.png"));
            paluba = ImageIO.read(new File("res\\paluba.png"));
            ranen = ImageIO.read(new File("res\\ranen.png"));
            ubit = ImageIO.read(new File("res\\ubit.png"));
            end1 = ImageIO.read(new File("res\\end1.png"));
            end2 = ImageIO.read(new File("res\\end2.png"));
            bomba = ImageIO.read(new File("res\\bomba.png"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

//        tmDraw = new Timer(50, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                repaint();
//            }
//        });
//        tmDraw.start();

        // Включаем возможность произвольного размещения
        //элементов интерфейса на панели
//        setLayout(null);

        //Создаем кнопку Новая игра
//        btn1 = new JButton();
//        btn1.setText("Новая игра");
//        btn1.setForeground(Color.BLUE);
//        btn1.setFont(new Font("serif",0,30));
//        btn1.setBounds(130, 450, 200, 80);
//        btn1.addActionListener(new ActionListener() {
//                                   @Override
//                                   public void actionPerformed(ActionEvent e) {
//                                       myGame.start();
//                                   }
//                               });
//        add(btn1);

        //Создаем кнопку Выход
//        btn2 = new JButton();
//        btn2.setText("Выход");
//        btn2.setForeground(Color.RED);
//        btn2.setFont(new Font("serif",0,30));
//        btn2.setBounds(530, 450, 200, 80);
//        btn2.addActionListener(new ActionListener() {
//                                   @Override
//                                   public void actionPerformed(ActionEvent e) {
//                                       System.exit(0);
//                                   }
//                               });
//        add(btn2);

//        addMouseListener(new myMouse1());
//        addMouseMotionListener(new myMouse2());
//        setFocusable(true); // Передаем фокус панели
    }

    //Метод отрисовки
    @Override
    public void paintComponent(Graphics gr) {
        //Очищение игрового поля
        super.paintComponent(gr);
        //Отрисовка фона
        gr.drawImage(fon,0,0,900,600,null);
        //Установка шрифта
        gr.setFont(new Font("serif",3,40));
        //Установка цвета
        gr.setColor(Color.BLUE);
        //Выведение надписей
        gr.drawString("Компьютер", 150, 50);
        gr.drawString("Игрок", 590, 50);

        // Отрисовка игровых полей Компьютера и Игрока на основании массивов
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Игровое поле компьютера
                if (myGame.masComp[i][j] != 0) {
                    // Если это подбитая палуба корабля
                    if ((myGame.masComp[i][j] >= 8) && (myGame.masComp[i][j] <= 11)) {
                        gr.drawImage(ranen, 100 + j * 30, 100 + i * 30, 30, 30,null);
                    } else if (myGame.masComp[i][j] >= 15) { //Если это палуба полностью подбитого корабля
                        gr.drawImage(ubit, 100 + j * 30, 100 + i * 30, 30, 30,null);
                    }
                    // Если был выстрел
                    if (myGame.masComp[i][j] >= 5 || myGame.masComp[i][j] == -2) {
                        gr.drawImage(bomba, 100 + j * 30, 100 + i * 30, 30, 30,null);
                    }
                }
                // Игровое поле игрока
                if (myGame.masPlay[i][j] != 0) {
                    // Если это палуба корабля
                    if ((myGame.masPlay[i][j] >= 1) && (myGame.masPlay[i][j] <= 4)) {
                        gr.drawImage(paluba, 500 + j * 30, 100 + i * 30, 30, 30, null);
                    }
                    // Если это подбитая палуба корабля
                    else if ((myGame.masPlay[i][j] >= 8) && (myGame.masPlay[i][j] <= 11)) {
                        gr.drawImage(ranen, 500 + j * 30, 100 + i * 30, 30, 30,null);
                    }
                    // Если это палуба полностью подбитого корабля
                    else if (myGame.masPlay[i][j] >= 15) {
                        gr.drawImage(ubit, 500 + j * 30, 100 + i * 30, 30, 30,null);
                    }
                    // Если был выстрел
                    if (myGame.masPlay[i][j] >= 5 || myGame.masPlay[i][j] == -2) {
                        gr.drawImage(bomba, 500 + j * 30, 100 + i * 30, 30, 30,null);
                    }
                }
            }
        }

        gr.setColor(Color.RED); // Красный цвет
        // Если курсор мыши внутри игрового поля компьютера
        if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
            // Если не конец игры и ход игрока
            if ((myGame.endg == 0) && (myGame.compHod == false)) {
                // Вычисляем номер строки в массиве
                int i = (mY - 100) / 30;
                // Вычисляем номер элемента в строке в массиве
                int j = (mX - 100) / 30;
                // Если ячейка подходит для выстрела
                if (myGame.masComp[i][j] <= 4 && myGame.masComp[i][j] >= -1)
                    // Рисуем квадрат с заливкой
                    gr.fillRect(100 + j * 30, 100 + i * 30, 30, 30);
            }
        }

        gr.setColor(Color.BLUE);
        for (int i = 0; i <= 10; i++) {
            // Рисование линий сетки игрового поля Компьютера
            gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
            gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
            // Рисование линий сетки игрового поля Игрока
            gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
            gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
        }
        //Установка шрифта
        gr.setFont(new Font("serif",0,20));
        //Установка цвета
        gr.setColor(Color.RED);
        //Введение цифр и букв слева и сверху от игровых полей
        for (int i = 1; i <= 10; i++) {
            // Вывод цифр
            gr.drawString("" + i, 73, 93 + i * 30);
            gr.drawString("" + i, 473, 93 + i * 30);
            // Вывод букв
            gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
            gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
        }

        // Вывод изображения конца игры - при окончании игры
        if (myGame.endg == 1) {// Если победил Игрок
            gr.drawImage(end1, 300, 200, 300, 100, null);
        } else if (myGame.endg == 2) {// Если победил Компьютер
            gr.drawImage(end2, 300, 200, 300, 100, null);
        }
    }
}
