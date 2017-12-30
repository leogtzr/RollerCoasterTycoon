package guests;

import java.util.logging.Level;
import java.util.logging.Logger;
import world.World;


public class GuestMover implements Runnable {

   private World world;

    public GuestMover(World world)
    {
        this.world = world;
    }



    public void run()
    {
        while (true)
        {

            world.movePeople();

            try
            {
                Thread.sleep(80);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(GuestMover.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
