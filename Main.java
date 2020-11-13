import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements Runnable, ActionListener{

  // Class Variables 

  //top status text
  JTextField status;

  //create an array for the pits
  JButton[] pits;

  //create the big pits for points
  JTextField p1Points;
  JTextField p2Points;

  //use a variable to keep track of whose turn it is
  boolean p2Turn;

  //use boolean to make it so the player can't play when the game is over
  boolean gameOver;


  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Title");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);

    //create a font to make the text bigger
    Font statusFont = new Font("arial", Font.PLAIN, 36);
    Font pitFont = new Font("arial", Font.PLAIN, 58);
 
    //create a mainPanel and set the layout
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    //create and add the status text to the panel 
    status = new JTextField();
    status.setFont(statusFont);
    mainPanel.add(status, BorderLayout.PAGE_START);
    //disable text field so people can't edit it
    status.setEnabled(false);

    //create a panel to put the grid on for the buttons
    JPanel grid = new JPanel();
    grid.setLayout(new GridLayout(2,6));

    //create an array with 12 spots for the buttons
    pits = new JButton[12];

    //create buttons using a for loop and add to panel
    for(int i = 0; i < pits.length; i++){
      if(i < 6){
      int spot = 5 - i;
      //create a button with 4 points in it
      pits[spot] = new JButton("" + 4);
      //add action listener 
      pits[spot].addActionListener(this);
      //set action command to the number
      String pitNum = String.valueOf(spot);
      pits[spot].setActionCommand(pitNum);

      //add font to the buttons
      pits[spot].setFont(pitFont);

      //add grid to panel
      grid.add(pits[spot]);

      }else{
      //create a button with 4 points in it
      pits[i] = new JButton("" + 4);
      //add action listener 
      pits[i].addActionListener(this);
      //set action command to the number
      String pitNum = String.valueOf(i);
      pits[i].setActionCommand(pitNum);

      //add font to the buttons
      pits[i].setFont(pitFont);

      //add grid to panel
      grid.add(pits[i]);
      }
    }

    //make sure p1 is first
    p2Turn = false;

    //set game over to false
    gameOver = false;

    //add the pit grid to panel
    mainPanel.add(grid, BorderLayout.CENTER);

    //create and add points to the panel
    p2Points = new JTextField("" + 0);
    mainPanel.add(p2Points, BorderLayout.LINE_START);
    p1Points = new JTextField("" + 0);
    mainPanel.add(p1Points, BorderLayout.LINE_END);

    //set font to make the text fields bigger
    p2Points.setFont(pitFont);
    p1Points.setFont(pitFont);

    //disable the text fields so user can not change them
    p2Points.setEnabled(false);
    p1Points.setEnabled(false);

    //add main panel to the frame
    frame.add(mainPanel);

    //use method to set turn text
    playerTurn();
  }

  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();

    //buttons for player 1
    if(command.equals("6")){
      //get the text from the pit
      String spots = pits[6].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 1
      String pointsText = p1Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      //if there are the exact amount of marbles in selected pit to finish in your point section you get another turn
      if(marbles == 6){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);


        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        //loop to put a marble in each pit after the selected pit based on the marbles in the selected pit
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
        //
      }else if(marbles < 6){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn(); 

      }else if(marbles > 6){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }  

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn(); 

      }


      //reset clicked button to 0
      pits[6].setText("" + 0);
    }

    if(command.equals("7")){
      //get the text from the pit
      String spots = pits[7].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 1
      String pointsText = p1Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 5){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 5){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();  

      }else if(marbles > 5){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }   
        
        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();

      }
      //reset clicked button to 0
      pits[7].setText("" + 0);
    }

     if(command.equals("8")){
      //get the text from the pit
      String spots = pits[8].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 1
      String pointsText = p1Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 4){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 4){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 4){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        } 
        
        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();

      }
      //reset clicked button to 0
      pits[8].setText("" + 0);
    }

      if(command.equals("9")){
      //get the text from the pit
      String spots = pits[9].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 1
      String pointsText = p1Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 3){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 3){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn(); 

      }else if(marbles > 3){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn(); 

      }
      //reset clicked button to 0
      pits[9].setText("" + 0);
    }

      if(command.equals("10")){
      //get the text from the pit
      String spots = pits[10].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 1
      String pointsText = p1Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 2){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 2){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }
        
        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();

      }else if(marbles > 2){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }  
        
        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();    
      }
      //reset clicked button to 0
      pits[10].setText("" + 0);
    }

      if(command.equals("11")){
      //get the text from the pit
      String spots = pits[11].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 1
      String pointsText = p1Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 1){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 1){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 1){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p1Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   
      }
      //reset clicked button to 0
      pits[11].setText("" + 0);
    }

      //buttons for player 2
      if(command.equals("0")){
      //get the text from the pit
      String spots = pits[0].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 2
      String pointsText = p2Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 6){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 6){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 6){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   
      }
      //reset clicked button to 0
      pits[0].setText("" + 0);
    }

      if(command.equals("1")){
      //get the text from the pit
      String spots = pits[1].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 2
      String pointsText = p2Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 5){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 5){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 5){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   
      }
      //reset clicked button to 0
      pits[1].setText("" + 0);
    }

      if(command.equals("2")){
      //get the text from the pit
      String spots = pits[2].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 2
      String pointsText = p2Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 4){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 4){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 4){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   
      }
      //reset clicked button to 0
      pits[2].setText("" + 0);
    }

      if(command.equals("3")){
      //get the text from the pit
      String spots = pits[3].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 2
      String pointsText = p2Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 3){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 3){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 3){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   
      }
      //reset clicked button to 0
      pits[3].setText("" + 0);
    }

      if(command.equals("4")){
      //get the text from the pit
      String spots = pits[4].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 2
      String pointsText = p2Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 2){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 2){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 2){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   
      }
      //reset clicked button to 0
      pits[4].setText("" + 0);
    }

      if(command.equals("5")){
      //get the text from the pit
      String spots = pits[5].getText();
      //change the text to an integer
      int marbles = Integer.parseInt(spots);

      //gets the points from player 2
      String pointsText = p2Points.getText();
      //changes points into integer
      int points = Integer.parseInt(pointsText);

      if(marbles == 1){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }
      }else if(marbles < 1){
        
        int index = Integer.parseInt(command);
        index = (index + 0) % 12;
        
        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;

        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   

      }else if(marbles > 1){
        //takes marble from pit
        marbles--;
        //puts it in points
        points++;
        p2Points.setText("" + points);

        int index = Integer.parseInt(command);
        index = (index + 0) % 12;

        for(int i = marbles; i >= 0; i--){
          int marbles2 = Integer.parseInt(pits[index].getText());
          marbles2++;
          pits[index].setText("" + marbles2);
          
          index = (index + 1) % 12;
        }

        //switch players
        p2Turn = !p2Turn;
        //output player text
        playerTurn();   
      }
      //reset clicked button to 0
      pits[5].setText("" + 0);
    }



  }

  //method called to set the text of whose turn it is
  public void playerTurn(){
    if(p2Turn){
      status.setText("Player 2's Turn");
      for(int i = 0; i < pits.length; i++){
        int player = i/6;
        if(player == 0){
          pits[i].setEnabled(true);
        }else{
          pits[i].setEnabled(false);
        }
        }
      }
    else{
      status.setText("Player 1's Turn");
      for(int i = 0; i < pits.length; i++){
        int player = i/6;
        if(player == 0){
          pits[i].setEnabled(false);
        }else{
          pits[i].setEnabled(true);
        }
        }
    }
  }

  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
