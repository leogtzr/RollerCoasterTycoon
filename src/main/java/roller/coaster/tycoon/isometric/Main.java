package roller.coaster.tycoon.isometric;

import roller.coaster.tycoon.world.World;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        World world = createWorld();
        Gui gui = new Gui(world);

        Thread thread = new Thread(gui);
        thread.start();
    }
    private static World createWorld() {
        try {
            return new World();
        } catch (IOException e) {
            throw new RuntimeException("Unable to start RollerCoasterTycoon", e);
        }
    }

}
