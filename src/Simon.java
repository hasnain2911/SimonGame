import java.util.Random;
import java.util.ArrayList;
import swiftbot.*;
public class Simon {
	static Random rand = new Random();
	static ArrayList<Integer> colourChoice = new ArrayList<>();
	static ArrayList<Integer> colourSelected = new ArrayList<>();
	SwiftBotAPI swiftBot;
	
	//Constructor
	public Simon(SwiftBotAPI swiftBot) {
		this.swiftBot = swiftBot;
	}
	
	//Button Sequence Method
	public static void enterSequence(int seqLength) throws InterruptedException {
        int count = 0;
        colourSelected.clear();
        
        int[] red = new int[] {255,0,0};
        int[] blue = new int[] {0,0,255};
        int[] green = new int[] {0,255,0};
        int[] yellow = new int[] {255,255,0};
        
		System.out.println("You have 4 seconds to pick each colour of the sequence. A = Red, B = Blue, X = Green, Y = Yellow");

        try{
            while(count <= seqLength) {
	            
            	//long endtime = System.currentTimeMillis() + 4_000;
	            
            	swiftBot.enableButton(Button.A, () -> {
            		swiftBot.setUnderlight(Underlight.BACK_LEFT,red);
            		Thread.sleep(100);
	                colourSelected.add(0);
	                });
            	swiftBot.disableAllButtons();
                //colourSelected.add(0);
                count++;
                continue;
                
                swiftBot.enableButton(Button.B, () -> {
                	swiftBot.setUnderlight(Underlight.BACK_RIGHT,blue);
            		Thread.sleep(100);
	                colourSelected.add(1);
	                });
            	swiftBot.disableAllButtons();
                //colourSelected.add(0);
                count++;
                continue;
                
                swiftBot.enableButton(Button.X, () -> {
                	swiftBot.setUnderlight(Underlight.FRONT_LEFT,green);
            		Thread.sleep(100);
	                colourSelected.add(2);
	                });
            	swiftBot.disableAllButtons();
                //colourSelected.add(0);
                count++;
                continue;
                
                swiftBot.enableButton(Button.Y, () -> {
                	swiftBot.setUnderlight(Underlight.FRONT_RIGHT,yellow);
            		Thread.sleep(100);
	                colourSelected.add(3);
	                });
            	swiftBot.disableAllButtons();
                //colourSelected.add(0);
                count++;
                continue;
	
	            
            }
//            swiftBot.disableAllButtons(); // Turns off all buttons now that it's been 10 seconds.
//            System.out.println("All buttons are now off.");
            //Checking GitHub

        }catch(Exception e){
            System.out.println("ERROR occurred when setting up buttons.");
            e.printStackTrace();
            System.exit(5);
        }

    }
	
	//Light Sequence Method
	public static void generateSequence(int seqLength) throws InterruptedException {
		if(seqLength > colourChoice.size()) {
			colourChoice.add(rand.nextInt(4));
		}
        // Declaring four variables containing the RGB values for red, green, blue and yellow.
        int[] red = new int[] {255,0,0};
        int[] blue = new int[] {0,0,255};
        int[] green = new int[] {0,255,0};
        int[] yellow = new int[] {255,255,0};

        try{
            // Declaring an array of under lights.
            Underlight[] underlights = new Underlight[] {Underlight.BACK_LEFT, Underlight.BACK_RIGHT, Underlight.FRONT_LEFT, Underlight.FRONT_RIGHT};

            //Displaying Sequence
            for(int i = 0; i < seqLength; i++) {
            	int colourPick = colourChoice.get(i);
            	switch(colourPick) {
                case 0: swiftBot.setUnderlight(underlight[0],red);
                		Thread.sleep(100);
                		break;
                case 1: swiftBot.setUnderlight(underlight[1],blue);
        				Thread.sleep(100);
        				break;
                case 2: swiftBot.setUnderlight(underlight[2],green);
        				Thread.sleep(100);
        				break;
                case 3: swiftBot.setUnderlight(underlight[3],yellow);
        				Thread.sleep(100);
        				break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR: Unable to set under light");
            System.exit(5);
        }

        System.out.println("SUCCESS: All under lights should be green");
        Thread.sleep(2000);
        swiftBot.disableUnderlights();
    }


}
