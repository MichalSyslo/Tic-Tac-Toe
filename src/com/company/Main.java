package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main extends JFrame implements ActionListener {
    int counter = 0;
    static int resO=0, resX=0;
    boolean isGameWon=false;

    JButton nextGameButton;
    JButton[] buttonArr = new JButton[9];
    JLabel printResultLabel, labelO, labelX;

    public Main(){
        int x=10, i;

        setSize(1100, 710);
        setTitle("Tic Tac Toe");
        setLayout(null);

        for(i=0; i<9; i++){
            if(i==3 ||i==6) { x=10; }
            buttonArr[i] = new JButton("");
            if(i<=2) {
                buttonArr[i].setBounds(x, 10, 200, 200);
                x+=230;
            }
            else if (i<=5){
                buttonArr[i].setBounds(x, 230, 200, 200);
                x+=230;
            }
            else {
                buttonArr[i].setBounds(x, 460, 200, 200);
                x+=230;
            }
            add(buttonArr[i]);
            buttonArr[i].addActionListener(this);
        }

        for (i=0; i<9; i++){
            buttonArr[i].setBackground(Color.WHITE);
        }

        nextGameButton = new JButton("Next Game");
        nextGameButton.setBounds(830, 620, 250, 50);
        add(nextGameButton);
        nextGameButton.addActionListener(new NextGameListener());

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(750,10,200,100);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 50));
        add(resultLabel);

        labelO = new JLabel("O :   " + resO);
        labelO.setBounds(750,100,150,70);
        labelO.setFont(new Font("Arial", Font.BOLD, 50));
        add(labelO);

        printResultLabel = new JLabel("");
        printResultLabel.setForeground(new Color(0,210,0));
        printResultLabel.setBounds(720,300,250,70);
        printResultLabel.setFont(new Font("Arial", Font.BOLD, 55));
        add(printResultLabel);

        labelX = new JLabel("X :  " + resX);
        labelX.setBounds(750,490,150,70);
        labelX.setFont(new Font("Arial", Font.BOLD, 50));
        add(labelX);
        setResizable(false);
        setVisible(true);
    }

    class NextGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0; i<9; i++){
                buttonArr[i].setText("");
                buttonArr[i].setBackground(Color.WHITE);
                printResultLabel.setText("");
                isGameWon = false;
                counter = 0;
            }
        }
    }

    public static void main(String[] args) {
	    Main game = new Main();
	    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed (ActionEvent e) {
        int buttonNum = 0, i;
        for (i = 0; i < 9; i++) {
            if (e.getSource() == buttonArr[i]) {
                buttonNum = i;
            }
        }
        if (buttonArr[buttonNum].getText().equals("") && !isGameWon) {
            if (counter % 2 == 0) {
                buttonArr[buttonNum].setForeground(Color.BLUE);
                buttonArr[buttonNum].setFont(new Font("Arial", Font.BOLD, 50));
                buttonArr[buttonNum].setText("O");
            } else {
                buttonArr[buttonNum].setForeground(Color.RED);
                buttonArr[buttonNum].setFont(new Font("Arial", Font.BOLD, 50));
                buttonArr[buttonNum].setText("X");
            }
            if (counter > 3) {
                checkWinner();
            }
            counter++;
        }
    }
    public void checkWinner(){
        int i;
        for(i=0;i<9;i+=3) {
            if (buttonArr[i].getText().equals(buttonArr[i+1].getText()) && buttonArr[i+1].getText().equals(buttonArr[i+2].getText()) && buttonArr[i].getText().length()>0){
                buttonArr[i].setBackground(Color.GREEN);
                buttonArr[i+1].setBackground(Color.GREEN);
                buttonArr[i+2].setBackground(Color.GREEN);
                actualizeLabels(i);
                isGameWon = true;
            }
        }
        for(i=0; i<3;i++){
            if (buttonArr[i].getText().equals(buttonArr[i+3].getText()) && buttonArr[i+3].getText().equals(buttonArr[i+6].getText()) && buttonArr[i].getText().length()>0){
                buttonArr[i].setBackground(Color.GREEN);
                buttonArr[i+3].setBackground(Color.GREEN);
                buttonArr[i+6].setBackground(Color.GREEN);
                actualizeLabels(i);
                isGameWon = true;
            }
        }
        if (buttonArr[0].getText().equals(buttonArr[4].getText()) && buttonArr[4].getText().equals(buttonArr[8].getText()) && buttonArr[0].getText().length()>0) {
            buttonArr[0].setBackground(Color.GREEN);
            buttonArr[4].setBackground(Color.GREEN);
            buttonArr[8].setBackground(Color.GREEN);
            actualizeLabels(0);
            isGameWon = true;
        }
        if (buttonArr[2].getText().equals(buttonArr[4].getText()) && buttonArr[4].getText().equals(buttonArr[6].getText()) && buttonArr[2].getText().length()>0) {
            buttonArr[2].setBackground(Color.GREEN);
            buttonArr[4].setBackground(Color.GREEN);
            buttonArr[6].setBackground(Color.GREEN);
            actualizeLabels(2);
            isGameWon = true;
        }
        if(counter==8 && !isGameWon){
            printResultLabel.setText("DRAW");
        }
    }
    public void actualizeLabels(int i){
        if(buttonArr[i].getText().equals("X")) {
            printResultLabel.setText("X Won!!!");
            resX += 1;
            labelX.setText("X :   " + resX);
        } else {
            printResultLabel.setText("O Won!!!");
            resO += 1;
            labelO.setText("O :   " + resO);
        }
    }
}
