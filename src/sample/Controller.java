package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Button btn0;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button changeSignBtn;
    @FXML
    private Button equationBtn;
    @FXML
    private Button minusBtn;
    @FXML
    private Button plusBtn;
    @FXML
    private Button divideBtn;
    @FXML
    private Button multipleBtn;
    @FXML
    private Button gapBtn;
    @FXML
    private Button backspaceBtn;
    @FXML
    private Button clearAllBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Label equationLbl;
    @FXML
    private TextField textField;

    private double a = 0, b = 0, c = 0;
    private String action = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn0.setOnAction(e -> addText(btn0.getText()));
        btn1.setOnAction(e -> addText(btn1.getText()));
        btn2.setOnAction(e -> addText(btn2.getText()));
        btn3.setOnAction(e -> addText(btn3.getText()));
        btn4.setOnAction(e -> addText(btn4.getText()));
        btn5.setOnAction(e -> addText(btn5.getText()));
        btn6.setOnAction(e -> addText(btn6.getText()));
        btn7.setOnAction(e -> addText(btn7.getText()));
        btn8.setOnAction(e -> addText(btn8.getText()));
        btn9.setOnAction(e -> addText(btn9.getText()));
        changeSignBtn.setOnAction(e -> changeSign());
        minusBtn.setOnAction(e -> setAction("-"));
        plusBtn.setOnAction(e -> setAction("+"));
        divideBtn.setOnAction(e -> setAction("/"));
        multipleBtn.setOnAction(e -> setAction("*"));
        clearBtn.setOnAction(e -> textField.setText("0"));
        clearAllBtn.setOnAction(e -> clearAll(true));
        equationBtn.setOnAction(e -> equation());
        gapBtn.setOnAction(e -> {
            if (!textField.getText().contains(".")){
                textField.setText(textField.getText() + ".");
            }
        });
        backspaceBtn.setOnAction(e -> {
            int length = textField.getText().length();
            String text = textField.getText();
            if (text.contains("-")) {
                if (length > 2) {
                    textField.setText(textField.getText(0, length - 1));
                } else if (length == 2) {
                    textField.setText("0");
                }
            } else {
                if (length > 1) {
                    textField.setText(textField.getText(0, length - 1));
                } else if (length == 1) {
                    textField.setText("0");
                }
            }
        });
    }

    private void changeSign() {
        if (!textField.getText().equals("0")){
            if (textField.getText().contains("-")){
                textField.setText(textField.getText().replace("-", ""));
            } else {
                textField.setText("-" + textField.getText());
            }
        }
    }

    private void addText(String number){
        if (textField.getText().equals("0")){
            textField.setText(number);
        } else {
            textField.setText(textField.getText() + number);
        }
    }

    private void setAction(String action){
        if (a == 0 && this.action.isEmpty()){
            a = Double.parseDouble(textField.getText());
        } else {
            a = calc(a, this.action, Double.parseDouble(textField.getText()));
        }
        this.action = action;
        equationLbl.setText(a + " " + action);
        textField.setText("");

    }

    private double calc(double a, String action, double b){
        switch (action){
            case "-":
                return a - b;
            case "+":
                return a + b;
            case "/":
                return a / b;
            case "*":
                return a * b;
            default:
                System.out.println("Unknown action");
                return 0;
        }
    }

    private void equation(){
        if (a != 0 && !action.isEmpty()){
            c = calc(a, action, (b = Double.parseDouble(textField.getText())));
            equationLbl.setText(a + " " + action + " " + b + " = " + c);
            clearAll(false);
        }
    }

    private void clearAll(boolean lbl){
        a = 0;
        b = 0;
        c = 0;
        textField.setText("0");
        if (lbl){
            equationLbl.setText("");
        }
        action = "";
    }
}
