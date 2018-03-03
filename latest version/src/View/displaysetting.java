package View;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static latest.version.LatestVersion.Windows;
import static latest.version.LatestVersion.publicMethod;

public class displaysetting {

    Scene scene;
    ToggleSwitch toggle = new ToggleSwitch();
    ComboBox limitTime = new ComboBox<>();
    ComboBox Boomvolicity = new ComboBox<>();

    Text text = new Text();

    public displaysetting() {
        Pane root = new Pane();
        ImageView imageview = publicMethod.addBackground("war.png");

        Titles Display = new Titles("Display Setting", Color.CORAL);
        Display.setTranslateX(350);
        Display.setTranslateY(80);

        Text t = new Text("Allow display Grid of your Opponent");
        t.setFill(Color.WHITESMOKE);
        t.setFont(new Font(50));

        text.setFont(Font.font(18));
        text.setFill(Color.WHITE);
        text.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then("ON").otherwise("OFF"));

        Text limit = new Text("Limit Time");
        limit.setFill(Color.WHITESMOKE);
        limit.setFont(new Font(30));

        Text volicity = new Text("volicity Time");
        volicity.setFill(Color.WHITESMOKE);
        volicity.setFont(new Font(30));

        Boomvolicity.getItems().add(1000);
        Boomvolicity.getItems().add(1500);
        Boomvolicity.getItems().add(2000);
        Boomvolicity.getItems().add(2500);
        Boomvolicity.getItems().add(3000);
        Boomvolicity.getItems().add(3500);
        Boomvolicity.getItems().add(4000);
        Boomvolicity.getItems().add(4500);
        Boomvolicity.getItems().add(5000);

        Boomvolicity.getSelectionModel().select(1);

        for (int i = 2; i <= 15; i++) {
            limitTime.getItems().add(i);
        }

        limitTime.getSelectionModel().select(8);

        VBox v = new VBox(t, toggle, text, limit, limitTime, volicity, Boomvolicity);
        v.setAlignment(Pos.CENTER);
        v.setTranslateX(200);
        v.setTranslateY(250);

        Button Back = new Button("Back");
        Back.setTranslateY(100);
        Back.setStyle("-fx-font: 35 arial; -fx-base: #ee2211;");

        Back.setTranslateY(650);

        Back.setOnAction(e -> new GameOptions());

        root.getChildren().addAll(imageview, Display, v, Back);
        scene = new Scene(root, 1200, 720);

        //    Windows.setScene(scene);
    }

    private static class ToggleSwitch extends Parent {

        private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

        private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
        private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

        private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

        public BooleanProperty switchedOnProperty() {
            return switchedOn;
        }

        public ToggleSwitch() {
            Rectangle background = new Rectangle(100, 50);
            background.setArcWidth(50);
            background.setArcHeight(50);
            background.setFill(Color.WHITE);
            background.setStroke(Color.LIGHTGRAY);

            Circle trigger = new Circle(25);
            trigger.setCenterX(25);
            trigger.setCenterY(25);
            trigger.setFill(Color.WHITE);
            trigger.setStroke(Color.LIGHTGRAY);

            DropShadow shadow = new DropShadow();
            shadow.setRadius(2);
            trigger.setEffect(shadow);

            translateAnimation.setNode(trigger);
            fillAnimation.setShape(background);

            getChildren().addAll(background, trigger);

            switchedOn.addListener((obs, oldState, newState)
                    -> {
                boolean isOn = newState.booleanValue();
                translateAnimation.setToX(isOn ? 100 - 50 : 0);
                fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
                fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);

                animation.play();
            });

            setOnMouseClicked(event
                    -> {
                switchedOn.set(!switchedOn.get());
            });
        }
    }

    public void setScene() {
        Windows.setScene(scene);
    }

}
