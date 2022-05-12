package com.example.excerse1;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private MediaView mediaView;
    @FXML
    private MediaPlayer player;
    @FXML
    private Slider durationTime;

    @FXML
    private Slider volumeSlider;

    @FXML
    public void initialize() {
        String video = getClass().getResource("Dali Nguwe.mp4").toExternalForm();
        Media media = new Media(video);
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);



                player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                        durationTime.setValue(t1.toSeconds());

                    }
                });
                durationTime.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        player.seek(Duration.seconds(durationTime.getValue()));
                    }
                });
        durationTime.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(durationTime.getValue()));
            }
        });
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total =  media.getDuration();
                durationTime.setMax(total.toSeconds());

            }
        });


        volumeSlider.setValue(player.getVolume() *100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                player.setVolume(volumeSlider.getValue()/100);
            }
        });
    }


    @FXML
    void playVideo(MouseEvent event) {
        player.play();
        player.setRate(1);


    }

    @FXML
    void pauseVideo(MouseEvent event) {
        player.pause();
    }

    @FXML
    void stopVideo(MouseEvent event) {
        player.stop();

    }
}