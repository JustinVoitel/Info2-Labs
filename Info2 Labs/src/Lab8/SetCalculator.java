package Lab8;

/**
* The main class of a simple calculator. Create one of these and you'll
* get the calculator on screen.
* 
* @author David J. Barnes and Michael Kolling
* @version 2008.03.30
* @edited by Kim & Le Ha 3.12.2019  
*/
public class SetCalculator 
{
   protected SetCalcEngine engine;
 
   protected SetUserInterface sui;
   
   /**
    * Create a new calculator and show it.
    */
   public SetCalculator()
   {
       engine = new SetCalcEngine();
      
       sui = new SetUserInterface(engine);
   }

   /**
    * In case the window was closed, show it again.
    */
   public void show()
   {
       sui.setVisible(true);
   }
}
