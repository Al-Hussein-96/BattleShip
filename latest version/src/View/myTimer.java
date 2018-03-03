package View;

import Model.Game;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class myTimer extends Pane {

    private final Text TheTime;
    private int i;
    private final Thread ThreadTimer;
    private boolean ShutDown = false;

    public myTimer(String TheTime1) {
        this.TheTime = new Text(TheTime1);

        ThreadTimer = new Thread(new Runnable() {
            @Override
            public void run() {

                setTimer("1");
                i = 1;
                try {
                    while (!ShutDown) {
                        System.out.println("");
                        if (!Game.Pause) {
                            setTimer(String.valueOf(i));
                            i++;
                            GUIPlayer3.TheGame.setCurrentTimer(i);
                            Thread.sleep(1000);
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        getChildren().addAll(TheTime);
    }

    public void setTimer(String s) {
        TheTime.setText(s);
        if (Integer.parseInt(s) > 5) {
            TheTime.setFont(new Font(45));
            TheTime.setFill(Color.RED);
        } else {
            TheTime.setFont(new Font(40));
            TheTime.setFill(Color.WHITE);
        }
    }

    public void StartThreadTimer() {
        try {
            ThreadTimer.start();
        } catch (Exception e) {
        }
    }

    public void StopThreadTimer() {
        ShutDown = true;
    }

    public void ResetTimer() {
        this.i = 1;
        setTimer("1");
    }

    public void setFont1(int sz) {
        TheTime.setFont(new Font(40));
    }

    public String getTime() {
        return TheTime.getText();
    }

}
