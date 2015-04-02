package Code.MainGame;
import java.util.Vector;
import java.util.Random;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;

/*
import javax.microedition.lcdui.Graphics;
import java.util.Random;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
 */
/**
 * @(#)Board.java
 *
 *
 * @author
 * @version 1.00 2008/6/19
 */


public class Board {
    static final int FIRST_BOARD=0;
    static final int SPINNER=0;
    static final int REGULAR=1;
    
    
    static int board=0;
    static boolean hasSpinner=false;
    
    static Vector[] onBoard=null;
    static Vector onStock=null;
//    static Bitmap[] imagePanel;
    static Bitmap imageFrame=null;

    static GameController gameController;
    
    public Board() {
    }
    
    public static void initBoard(GameController gameController1) {
        gameController=gameController1;
        
        hasSpinner=false;
        if(onBoard==null) {
            onBoard=new Vector[2];
            onBoard[SPINNER]=new Vector();
            onBoard[REGULAR]=new Vector();
        }
        try {
//            if(imagePanel==null)
//                imagePanel=new Bitmap[2];
//            imagePanel[0]=Bitmap.getBitmapResource("images/panel1.png");
//            imagePanel[1]=Bitmap.getBitmapResource("images/panel2.png");
            if(imageFrame==null)
            	imageFrame=Bitmap.getBitmapResource("images/frame.png");
            
        } catch(Exception e) {
            if(Constants.DEBUG)
            	System.out.println("Error loading panel image");
        }
        
        if(onStock==null)
            onStock=new Vector();
    populateStock(onStock);

    }
    
    
    public static void populateStock(Vector tstock)
    {
        Domino domino;
        byte d1=0,d2=0;
        tstock.removeAllElements();
        
        for(int i=0; i<28;i++)
        {
            if(d1==7)
                break;
            if(d2==7)
            {
                d1++;
                d2=d1;
            }
            domino =new Domino(d1, d2++);
            tstock.addElement(domino);
        }    	
    }
    public static void deInitBoard() {
        onBoard=null;
//        imagePanel=null;
        onStock=null;
        onStock=null;
        board=FIRST_BOARD;
    }
    
    public static Vector[] getClone() {
        Domino domino;
        Vector[] tempBoard=new Vector[2];
        
        tempBoard[SPINNER]=new Vector();
        for(int i=0; i<onBoard[SPINNER].size();i++) {
            domino=(Domino)onBoard[SPINNER].elementAt(i);
            tempBoard[SPINNER].addElement(domino.getClone());
        }
        
        tempBoard[REGULAR]=new Vector();
        for(int i=0; i<onBoard[REGULAR].size();i++) {
            domino=(Domino)onBoard[REGULAR].elementAt(i);
            tempBoard[REGULAR].addElement(domino.getClone());
        }
        
        return tempBoard;
    }
 
    public static Vector cloneOnstockDominos()
   {
   		Vector cloneDomino=new Vector();
   		Domino domino; 
    	for(int i=0; i<onStock.size();i++)
    	{
    		domino=(Domino)onStock.elementAt(i);
    		cloneDomino.addElement(domino.getClone());
    	}
    	
   		return cloneDomino; 
   } 
 
    
    public static void paint(Graphics g) {
        try{
            Background.paint(g);
            
// if(Constants.DEBUG)
//	System.out.println("Error in board paint=1 ");
            Domino.paintBgFactor=true;
            Domino domino;
            for(int i=0; i<onBoard[SPINNER].size();i++) {
                domino = (Domino)onBoard[SPINNER].elementAt(i);
                domino.paint(g, true);
            }
            for(int i=0; i<onBoard[REGULAR].size();i++) {
                domino = (Domino)onBoard[REGULAR].elementAt(i);
                domino.paint(g, true);
            }
            
            g.drawBitmap( 0, 0, imageFrame.getWidth(), imageFrame.getHeight(), imageFrame, 0 ,0);            
            
//if(Constants.DEBUG)
//		System.out.println("Error in board paint=2 ");
/*            g.drawBitmap( Constants.PANEL1_X, Constants.PANEL1_Y, imagePanel[0].getWidth(), imagePanel[0].getHeight(), imagePanel[0], 0 ,0);
            g.drawBitmap( Constants.PANEL2_X, Constants.PANEL2_Y, imagePanel[1].getWidth(), imagePanel[1].getHeight(), imagePanel[1], 0 ,0);
*/           
//if(Constants.DEBUG)
//	System.out.println("Error in board paint=3 ");
            
        }catch(Exception e) {
            if(Constants.DEBUG)
            	System.out.println("Error in board paint="+e);
        }
        
    }
    
    public static void shuffleAndAquire(Vector player) {
        int count=0,index;
        boolean twins=false;
        Random random=new Random();
        Domino domino;
        try {
            
            do
            {
                index=(random.nextInt()%28);
                if(index>=onStock.size()||index<0)
                    continue;
                domino = (Domino)(onStock.elementAt(index));
                
                if(domino.areTwins())
                    twins=true;
                
                if(count==6 && !twins) {
                    
                    if(domino.dice1!=domino.dice2)
                        continue;
                } else {
                    player.addElement(domino);
                    onStock.removeElementAt(index);
                    count++;
                }
                
            }while(count!=Constants.MAX_SHARE);
        }catch(Exception e) {
            if(Constants.DEBUG)
            	System.out.println("Error in board shuffleAndAquire");
        }
        
    }
    
    
     public static boolean pickDomino(Player player)
     {
    	int count=0,index;
    	Random random=new Random();
    	Domino domino;
    	Vector drawnDominos=new Vector();
    	
//if(Constants.DEBUG)
//	System.out.println("onStock(size)=" +onStock.size());    			    	    		
    	
  		try
    	{
    		try{
		   	   		while(!player.setEnablility() && onStock.size()>0)
		    		{
			    		index=(random.nextInt()%onStock.size());  
		    			if(index<0)index=-index;
			    		domino = (Domino)(onStock.elementAt(index));
						Domino dominoP=(Domino)player.playerDominos.elementAt(player.playerDominos.size()-1);
			    		domino.X=dominoP.X+Domino.DICE_WIDTH+1;
			    		domino.Y=dominoP.Y;
			    		
			    		//for sending data
			    		if(gameController.playerMode==Constants.MULTIPLAYER)
			    			drawnDominos.addElement(domino.getClone());
			    			
			    		player.playerDominos.addElement(domino);
		    			onStock.removeElementAt(index);
						count++;
		    		};
    		}catch(Exception e)
    		{
    			if(Constants.DEBUG)
    				System.out.println("error in while pickDomino"+e);
    		}
			player.selectedDominoIndex=-1;
   			player.setCurser();
   			gameController.game.animation.setToDraw(count);

    		if(player.type==Game.PLAYER1)
    			gameController.sendItems=MultiplayerResponse.putData(drawnDominos);		
			drawnDominos=null;
			
if(Constants.DEBUG)
	System.out.println(count + " domino(s) added"+ " onStock(size)=" +onStock.size());    			    	    		
if(Constants.DEBUG)
	System.out.println(" 2.player(size)="+ player.playerDominos.size());    			    	    							

			if(onStock.size()==0 && !player.setEnablility())
				return false;

//				player.setPlaceInControlPanel();
			return true;
    	}catch(Exception e)
    	{
			if(Constants.DEBUG)
				System.out.println("Error in board pickDomino");    			    	
			return false;
	    }
     }
    
    
    public static int resetBoard() {
        try {
            
            board++;
            hasSpinner=false;
            onBoard[SPINNER].removeAllElements();
            onBoard[REGULAR].removeAllElements();
            onStock.removeAllElements();
            
            Domino domino;
            byte d1=0,d2=0;
            for(int i=0; i<28;i++) {
                if(d1==7)
                    break;
                if(d2==7) {
                    d1++;
                    d2=d1;
                }
                domino =new Domino(d1, d2++);
                onStock.addElement(domino);
            }
            if(Constants.DEBUG)
            	System.out.println("Board="+board);
            return board;
        }catch(Exception e) {
            if(Constants.DEBUG)
            	System.out.println("Error while reset board");
            return -1;
        }
        
    }
    
    public static int boardSum(Vector[] onboard) {
        Domino dominoB=null;
        int expScore=0;
        for(int i=0;i<onboard[Board.SPINNER].size();i++) {
            dominoB=(Domino)onboard[Board.SPINNER].elementAt(i);
            if(dominoB.isEnabled()) {
                switch(dominoB.gesture) {
                    case Domino.VERTICAL_STAND:
                        if(dominoB.areTwins()) {
                            if(dominoB.openLEFT || dominoB.openRIGHT)
                                expScore+=dominoB.getSum();
                        } else {
                            if(dominoB.openTOP)
                                expScore+=(int)dominoB.getDice1();
                            if(dominoB.openBOTTOM)
                                expScore+=(int)dominoB.getDice2();
                        }
                        break;
                        
                    case Domino.HORIZENTAL_STAND:
                        if(dominoB.areTwins()) {
                            if(dominoB.openTOP || dominoB.openBOTTOM)
                                expScore+=dominoB.getSum();
                        } else {
                            if(dominoB.openLEFT)
                                expScore+=(int)dominoB.getDice1();
                            if(dominoB.openRIGHT)
                                expScore+=(int)dominoB.getDice2();
                        }
                        break;
                }
            }
        }
        
        for(int i=0;i<onboard[Board.REGULAR].size();i++) {
            dominoB=(Domino)onboard[Board.REGULAR].elementAt(i);
            if(dominoB.spinner)     continue;
            
            if(dominoB.isEnabled()) {
                switch(dominoB.gesture) {
                    case Domino.VERTICAL_STAND:
                        if(dominoB.areTwins()) {
                            if(dominoB.openLEFT || dominoB.openRIGHT)
                                expScore+=dominoB.getSum();
                        } else {
                            if(dominoB.openTOP)
                                expScore+=(int)dominoB.getDice1();
                            if(dominoB.openBOTTOM)
                                expScore+=(int)dominoB.getDice2();
                        }
                        break;
                        
                    case Domino.HORIZENTAL_STAND:
                        if(dominoB.areTwins()) {
                            if(dominoB.openTOP || dominoB.openBOTTOM)
                                expScore+=dominoB.getSum();
                        } else {
                            if(dominoB.openLEFT)
                                expScore+=(int)dominoB.getDice1();
                            if(dominoB.openRIGHT)
                                expScore+=(int)dominoB.getDice2();
                        }
                        break;
                }
            }
        }
        
        return expScore;
        
    }
    
    
}
