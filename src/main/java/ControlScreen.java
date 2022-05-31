import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlScreen extends JPanel implements ActionListener {

    JLabel antsNumberText;
    JTextField antsNumberTextField;

    JLabel hornetNumberText;
    JTextField hornetNumberTextField;

    JLabel flowerNumberText;
    JTextField flowerNumberTextField;

    JLabel dataFromFileText;
    JTextField dataFromFileTextField;

    JLabel saveDataText;
    JTextField saveDataTextField;

    JButton startButton;
    JButton stopButton;

    JLabel stepsText;

    JLabel stepSetText;
    JTextField stepSetTextField;

    JLabel setFpsText;
    JTextField setFpsTextField;

    SimScreen simScreen;
    Simulation simulation;

    private int numberOfAnts = 0;
    private int numberOfHornets = 0;
    private int numberOfFlowers = 0;

    public ControlScreen(Simulation simulation, SimScreen simScreen) {
        this.simScreen = simScreen;
        this.simulation = simulation;

        antsNumberText = new JLabel("ants number: ");
        antsNumberText.setBounds(10, 20, 100, 30);
        this.add(antsNumberText);

        antsNumberTextField = new JTextField();
        antsNumberTextField.setBounds(100, 20, 50, 30);
        antsNumberTextField.addActionListener(this);
        this.add(antsNumberTextField);

        hornetNumberText = new JLabel("hornet number: ");
        hornetNumberText.setBounds(10, 60, 100, 30);
        this.add(hornetNumberText);

        hornetNumberTextField = new JTextField();
        hornetNumberTextField.setBounds(100, 60, 50, 30);
        hornetNumberTextField.addActionListener(this);
        this.add(hornetNumberTextField);

        flowerNumberText = new JLabel("flower number: ");
        flowerNumberText.setBounds(10, 100, 100, 30);
        this.add(flowerNumberText);

        flowerNumberTextField = new JTextField();
        flowerNumberTextField.setBounds(100, 100, 50, 30);
        flowerNumberTextField.addActionListener(this);
        this.add(flowerNumberTextField);

        dataFromFileText = new JLabel("load from file: ");
        dataFromFileText.setBounds(10, 140, 100, 30);
        this.add(dataFromFileText);

        dataFromFileTextField = new JTextField(simulation.getFileNameInput());
        dataFromFileTextField.setBounds(100, 140, 50, 30);
        dataFromFileTextField.addActionListener(this);
        this.add(dataFromFileTextField);

        saveDataText = new JLabel("save to: ");
        saveDataText.setBounds(10, 180, 100, 30);
        this.add(saveDataText);

        saveDataTextField = new JTextField(simulation.getFileNameOutput());
        saveDataTextField.setBounds(100, 180, 50, 30);
        saveDataTextField.addActionListener(this);
        this.add(saveDataTextField);

        startButton = new JButton("START");
        startButton.setBounds(30, 220, 100, 30);
        startButton.addActionListener(this);
        this.add(startButton);

        stopButton = new JButton("STOP");
        stopButton.setBounds(30, 260, 100, 30);
        stopButton.addActionListener(this);
        this.add(stopButton);

        stepsText = new JLabel("Steps: 0");
        stepsText.setBounds(30, 300, 100, 30);
        this.add(stepsText);

        stepSetText = new JLabel("set steps: ");
        stepSetText.setBounds(10, 340, 100, 30);
        this.add(stepSetText);

        stepSetTextField = new JTextField(Integer.toString(simulation.getSetSteps()));
        stepSetTextField.setBounds(100, 340, 50, 30);
        stepSetTextField.addActionListener(this);
        this.add(stepSetTextField);

        setFpsText = new JLabel("set fps: ");
        setFpsText.setBounds(10, 380, 100, 30);
        this.add(setFpsText);

        setFpsTextField = new JTextField(Integer.toString(simulation.getSetSteps()));
        setFpsTextField.setBounds(100, 380, 50, 30);
        setFpsTextField.addActionListener(this);
        this.add(setFpsTextField);
    }

    public void update() {
        stepsText.setText("Steps: " + simScreen.getSteps());
    }

    public void updateParameters(ArrayList<Integer> parameters) {
        numberOfAnts = parameters.get(0);
        antsNumberTextField.setText(Integer.toString(numberOfAnts));
        simScreen.setNumberOfAnts(numberOfAnts);

        numberOfHornets = parameters.get(1);
        simScreen.setNumberOfHornets(numberOfHornets);
        hornetNumberTextField.setText(Integer.toString(numberOfHornets));

        numberOfFlowers = parameters.get(2);
        simScreen.setNumberOfFlowers(numberOfFlowers);
        flowerNumberTextField.setText(Integer.toString(numberOfFlowers));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //buttons
        if (e.getSource() == startButton && !simScreen.isRunning()) {
            simScreen.setRunning(true);
            if (simScreen.getSteps() == simulation.getSetSteps()) {
                simScreen.resetSteps();
                simScreen.init();
            }

        }
        if (e.getSource() == stopButton) {
            if (!simScreen.isRunning()) {
                simScreen.resetSteps();
                simScreen.init();
            }

            simScreen.setRunning(false);
        }

        //textFields
        if (e.getSource() == antsNumberTextField) {
            try {
                simScreen.setNumberOfAnts(Integer.parseInt(antsNumberTextField.getText()));
            } catch (NumberFormatException er) {
                simScreen.setNumberOfAnts(0);
            }
        }
        if (e.getSource() == stepSetTextField) {
            try {
                System.out.println(Integer.parseInt(stepSetTextField.getText()));
                simulation.setSetSteps(Integer.parseInt(stepSetTextField.getText()));
            } catch (NumberFormatException er) {
                simulation.setSetSteps(0);
            }
        }
        if (e.getSource() == setFpsTextField) {
            try {
                simulation.setFPS(Integer.parseInt(setFpsTextField.getText()));
            } catch (NumberFormatException er) {
                simulation.setFPS(0);
            }
        }
        if (e.getSource() == dataFromFileTextField) {

            String fileName = dataFromFileTextField.getText();
            if (fileName.contains(".txt")) {

                Parameters.loadDataFromFile("/" + fileName);
                updateParameters(Parameters.getParameters(0));
            }

        }


    }


}

