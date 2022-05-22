import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public ControlScreen(SimScreen simScreen) {
        this.simScreen = simScreen;

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
        this.add(hornetNumberTextField);

        flowerNumberText = new JLabel("flower number: ");
        flowerNumberText.setBounds(10, 100, 100, 30);
        this.add(flowerNumberText);

        flowerNumberTextField = new JTextField();
        flowerNumberTextField.setBounds(100, 100, 50, 30);
        this.add(flowerNumberTextField);

        dataFromFileText = new JLabel("load from file: ");
        dataFromFileText.setBounds(10, 140, 100, 30);
        this.add(dataFromFileText);

        dataFromFileTextField = new JTextField();
        dataFromFileTextField.setBounds(100, 140, 50, 30);
        this.add(dataFromFileTextField);

        saveDataText = new JLabel("save to: ");
        saveDataText.setBounds(10, 180, 100, 30);
        this.add(saveDataText);

        saveDataTextField = new JTextField();
        saveDataTextField.setBounds(100, 180, 50, 30);
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

        stepSetTextField = new JTextField();
        stepSetTextField.setBounds(100, 340, 50, 30);
        stepSetTextField.setText("10");
        this.add(stepSetTextField);

        setFpsText = new JLabel("set fps: ");
        setFpsText.setBounds(10, 380, 100, 30);
        this.add(setFpsText);

        setFpsTextField = new JTextField();
        setFpsTextField.setBounds(100, 380, 50, 30);
        setFpsTextField.setText("10");
        this.add(setFpsTextField);

    }


    public void update() {
        stepsText.setText("Steps: " + simScreen.getSteps());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //buttons
        if (e.getSource() == startButton && !simScreen.isRunning()) {
            simScreen.setRunning(true);
            if (simScreen.getSteps() == getSetStepsFromTextField())
                simScreen.resetSteps();
        }
        if (e.getSource() == stopButton) {
            if (!simScreen.isRunning())
                simScreen.resetSteps();
            simScreen.setRunning(false);
        }

        //textFields
        if (e.getSource() == antsNumberTextField) {
            System.out.println(antsNumberTextField.getText());
        }
    }

    public int getSetStepsFromTextField() {
        try {
            int steps = Integer.parseInt(stepSetTextField.getText());
            return steps;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public int getSetFpsFromTextField() {
        try {
            int steps = Integer.parseInt(setFpsTextField.getText());
            return steps;
        } catch (NumberFormatException e) {
            return 1;
        }
    }

}

