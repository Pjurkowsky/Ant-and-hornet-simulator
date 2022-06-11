import javax.swing.*;
import java.util.ArrayList;

public interface Entity {

    JPanel draw();
    Entity update(ArrayList<Entity> map);

}
