package isometric;

public class Main
{

    public static void main(String[] args)
    {
        Gui gui = new Gui();

        Thread thread = new Thread(gui);
        gui.setVisible(true);

        thread.start();
    }
}
