package atm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ATMproject extends Application implements AccountGui{

    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start (Stage window) {
        
        //scene1
        Label L1 = new Label("Enter your Card number");
        L1.setStyle("-fx-font: normal bold 20px 'serif' ");
        TextField display1 = new TextField();
        display1.setMaxWidth(100);
        Button B1 = new Button("1");
        Button B2 = new Button("2");
        Button B3 = new Button("3");
        Button B4 = new Button("4");
        Button B5 = new Button("5");
        Button B6 = new Button("6");
        Button B7 = new Button("7");
        Button B8 = new Button("8");
        Button B9 = new Button("9");
        Button B0 = new Button("0");
        Button enter1 = new Button("Enter");
        B1.setOnAction(e -> display1.setText(display1.getText()+"1"));
        B2.setOnAction(e -> display1.setText(display1.getText()+"2"));
        B3.setOnAction(e -> display1.setText(display1.getText()+"3"));
        B4.setOnAction(e -> display1.setText(display1.getText()+"4"));
        B5.setOnAction(e -> display1.setText(display1.getText()+"5"));
        B6.setOnAction(e -> display1.setText(display1.getText()+"6"));
        B7.setOnAction(e -> display1.setText(display1.getText()+"7"));
        B8.setOnAction(e -> display1.setText(display1.getText()+"8"));
        B9.setOnAction(e -> display1.setText(display1.getText()+"9"));
        B0.setOnAction(e -> display1.setText(display1.getText()+"0"));
        GridPane numPad = new GridPane();
        numPad.setPadding(new Insets(5,5,5,5));
        numPad.setVgap(5);
        numPad.setHgap(5);
        numPad.setAlignment(Pos.CENTER);
        numPad.add(B1, 0, 0);
        numPad.add(B2, 1, 0);
        numPad.add(B3, 2, 0);
        numPad.add(B4, 0, 1);
        numPad.add(B5, 1, 1);
        numPad.add(B6, 2, 1);
        numPad.add(B7, 0, 2);
        numPad.add(B8, 1, 2);
        numPad.add(B9, 2, 2);
        numPad.add(B0, 1, 3);
        VBox layout1 = new VBox(8);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(L1, display1, numPad, enter1);
        
        //scene2
        Label L2 = new Label();
        L2.setStyle("-fx-font: normal bold 20px 'serif' ");
        Button deposit = new Button("Deposit");
        deposit.setPrefSize(100, 20);
        Button withdraw = new Button("Withdraw");
        withdraw.setPrefSize(100, 20);
        Button balanceI = new Button ("Balance Inquiry");
        balanceI.setPrefSize(100, 20);
        balanceI.setOnAction(e -> L2.setText(getCurrentBalance()));
        Button previous = new Button("Previous");
        previous.setPrefSize(100, 20);
        previous.setOnAction(e ->{
            if("Null".equals(previous()))
                AlertBox.display("Error", "There is no previous transaction.");
            else {
                 L2.setText(previous());
                 pos++;
            }
        });
        Button next = new Button ("Next");
        next.setPrefSize(100, 20);
        next.setOnAction(e ->{
            if("Null".equals(next()))
                AlertBox.display("Error", "There is no post transaction.");
            else {
                L2.setText(next());
                pos--;
            }
        });
        VBox layout2 = new VBox(15);
        layout2.setPadding(new Insets(20,20,20,20));
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(L2, deposit, withdraw, balanceI, previous, next);
        
        //scene3 
        Label L3 = new Label("Enter the amount of money");
        L3.setStyle("-fx-font: normal bold 20px 'serif' ");
        TextField display2 = new TextField();
        display2.setMaxWidth(100);
        Button enter2 = new Button("Enter");
        Button back1 = new Button("Back");
        VBox layout3 = new VBox(10);
        layout3.setAlignment(Pos.CENTER);
        layout3.getChildren().addAll(L3, display2, enter2, back1);
        
        //scene4
        Label L4 = new Label("Enter the amount of money");
        L4.setStyle("-fx-font: normal bold 20px 'serif' ");
        TextField display3 = new TextField();
        display3.setMaxWidth(100);
        Button enter3 = new Button("Enter");
        Button back2 = new Button("Back");
        VBox layout4 = new VBox(10);
        layout4.setAlignment(Pos.CENTER);
        layout4.getChildren().addAll(L4, display3, enter3, back2);
        
        Scene scene1 = new Scene(layout1, 300, 400);
        Scene scene2 = new Scene(layout2, 300, 400);
        Scene scene3 = new Scene(layout3, 300, 400);
        Scene scene4 = new Scene(layout4, 300, 400);
        
        enter1.setOnAction(e ->{ 
            if("5624".equals(display1.getText())) 
                window.setScene(scene2);
            else {
                AlertBox.display("Error", "Wrong Credentials.");
                display1.clear();
            }
        });
        enter2.setOnAction(e ->{ 
            deposit(display2.getText());
            AlertBox.display("Update", "Transaction Complete.");
            display2.clear();
            pos = 1;
        });
        enter3.setOnAction(e ->{
            if(withdraw(display3.getText()) == true)
                AlertBox.display("Update", "Transaction Complete.");
            else
                AlertBox.display("Error", "Insufficient Funds.");
            display3.clear();
            pos = 1;
        });
        deposit.setOnAction(e -> window.setScene(scene3));
        withdraw.setOnAction(e -> window.setScene(scene4));
        
        back1.setOnAction(e -> window.setScene(scene2));
        back2.setOnAction(e -> window.setScene(scene2));
        
        window.setTitle("ATM");
        window.setScene(scene1);
        window.show();
    }
    
    private double balance = 0;
    Queue transaction = new Queue(5);
    private int pos = 1;

    @Override
    public String getCurrentBalance() {
        return Double.toString(balance);
    }

    @Override
    public void deposit(String Amount) {
       double depValue = Double.parseDouble(Amount);
       balance = balance + depValue;
       Queue.push("Deposit "+Amount);
    }

    @Override
    public boolean withdraw(String Amount) {
        double withValue = Double.parseDouble(Amount);
        if(withValue > balance)
            return false;
        else {
            balance = balance - withValue;
            Queue.push("Withdraw "+Amount);
            return true;
        }
    }

    @Override
    public String previous() {
        return Queue.getPrevious(pos);
    }

    @Override
    public String next() {
        return Queue.getNext(pos-1);
    }
}
