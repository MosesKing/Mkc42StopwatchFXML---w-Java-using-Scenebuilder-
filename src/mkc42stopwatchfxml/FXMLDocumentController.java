package mkc42stopwatchfxml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author moeses
 */
public class FXMLDocumentController implements Initializable {
    
      // Our Numbers, which is what we are gunna use to make the Stopwatch happen 

    private double tickTimeInSeconds = 1.0;  // change this to change resolution
  
    private double angleDeltaPerSeconds = 6.0;
  
    private double secondsElapsed = 0;
    // How are computer TimeLine/KeyFrame 
   
    private Timeline timeline;
 
    private KeyFrame keyFrame;
    // Other Variables for the Images 
    @FXML
    private StackPane analogClockContainer;
    // stackPane to stack the images on top of each other
    @FXML
    private ImageView dialImageView;
    @FXML
    private ImageView secondHandImageView;
    @FXML
    private Image dialImage;
    @FXML
    private Image secondHandImage;
    @FXML
    private String dialImageName = "clockface.png";
    @FXML
    private String secondHandImageName = "second.png";
    @FXML
    public String formatted;

    @FXML
    public Label digitalClock;
    
    @FXML
    Button startButton = new Button("Start");
    @FXML
    Button stopButton = new Button("Stop");
    @FXML
    Button resetButton = new Button("Reset");
    
    
  
    public void setupTimer() {
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    // This method will move the hand acround our clock to the Scene
    private void update() {
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        secondHandImageView.setRotate(rotation);
        int minute = (int) secondsElapsed / 60;
        int timelayout = (int) secondsElapsed % 60;
        formatted = String.format("%02d:%02d", minute, timelayout);
        digitalClock.setText(formatted);
    }

    // Starts the Timer / And Picks Up from Last Played
    @FXML
    public void start() {
        timeline.play();
    }
    // Stops the Timers
    @FXML
    public void stop() {
        timeline.stop();
    }
    // Resets the Whole Clock!!
    @FXML
    public void reset() {
        secondsElapsed = 0;
        secondHandImageView.setRotate(0);
        timeline.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTimer();
        // TODO
    }    
    
}
