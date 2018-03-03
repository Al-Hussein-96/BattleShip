package Model;

import java.io.Serializable;

public class SaveInfo implements Serializable {

    private InfoGame Information;

    public SaveInfo(InfoGame Information) {
        this.Information = Information;
    }

    
    
    public void setInformation(InfoGame Information) {
        this.Information = Information;
    }

    public InfoGame getInformation() {
        return Information;
    }

}
