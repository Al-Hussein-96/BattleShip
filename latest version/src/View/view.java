package View;
/// to bind (Classes View) with (Controller) and (Model)

import Model.model;
import Controller.*;
import Model.BattleshipPlayer;

public class view {

    public static model model_$;
    public static controller controller_$;

    public view(model model_$, controller controller_$) {
        view.model_$ = model_$;
        view.controller_$ = controller_$;
    }

    public void Win(BattleshipPlayer win) {
        new Win(win);
    }

    public void RunMainMenu() {
        new MainMenu();
    }


}
