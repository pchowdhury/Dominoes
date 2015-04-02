package Code.MainGame;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
/*import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
 */

/**
 * @(#)Domino.java
 *
 *
 * @author
 * @version 1.00 2008/6/19
 */


public class Domino {
    public static final byte DICE_0=0;
    public static final byte DICE_1=1;
    public static final byte DICE_2=2;
    public static final byte DICE_3=3;
    public static final byte DICE_4=4;
    public static final byte DICE_5=5;
    public static final byte DICE_6=6;
    
    public static final byte LEFT=0;
    public static final byte TOP=1;
    public static final byte RIGHT=2;
    public static final byte BOTTOM=3;
    
    
    public static final byte VERTICAL_STAND=0;
    public static final byte HORIZENTAL_STAND=1;
    
    public static final byte DISABLED=0;
    public static final byte ENABLED=1;
    
    public static final int DICE_WIDTH=14;
    public static final int DICE_HEIGHT=15;
    
    public static final int DOMINO_WIDTH=14;
    public static final int DOMINO_HEIGHT=30;
    
    
    public boolean spinner=false;
    public boolean enabled1=true;
    public boolean enabled2=true;
    
    public boolean openLEFT=true;
    public boolean openTOP=true;
    public boolean openRIGHT=true;
    public boolean openBOTTOM=true;
    
    public byte dice1;
    public byte dice2;
    public byte gesture;
    public int X, Y;
    public static boolean paintBgFactor;
    
    static Bitmap[][] dominoImage=null;
    static Bitmap[][] diceImage=null;
    
    
    public Domino() {
        dice1=0;
        dice2=0;
        gesture=VERTICAL_STAND;
        X=Y=0;
        paintBgFactor=false;
        
        try{
            if(dominoImage==null) {
                dominoImage=new Bitmap[2][];
                dominoImage[VERTICAL_STAND]=new Bitmap[2];
                dominoImage[VERTICAL_STAND][DISABLED]=Bitmap.getBitmapResource("images/dominoVD.png");//"images/dominoVD.png"
                dominoImage[VERTICAL_STAND][ENABLED]=Bitmap.getBitmapResource("images/dominoVE.png");
                
                dominoImage[HORIZENTAL_STAND]=new Bitmap[2];
                dominoImage[HORIZENTAL_STAND][DISABLED]=Bitmap.getBitmapResource("images/dominoHD.png");//"images/dominoHD.png"
                dominoImage[HORIZENTAL_STAND][ENABLED]=Bitmap.getBitmapResource("images/dominoHE.png");
                
            }
            if(diceImage==null) {
                diceImage=new Bitmap[2][];
                diceImage[VERTICAL_STAND]=new Bitmap[6];
                diceImage[VERTICAL_STAND][0]=Bitmap.getBitmapResource("images/domino1V.png");
                diceImage[VERTICAL_STAND][1]=Bitmap.getBitmapResource("images/domino2V.png");
                diceImage[VERTICAL_STAND][2]=Bitmap.getBitmapResource("images/domino3V.png");
                diceImage[VERTICAL_STAND][3]=Bitmap.getBitmapResource("images/domino4V.png");
                diceImage[VERTICAL_STAND][4]=Bitmap.getBitmapResource("images/domino5V.png");
                diceImage[VERTICAL_STAND][5]=Bitmap.getBitmapResource("images/domino6V.png");
                
                diceImage[HORIZENTAL_STAND]=new Bitmap[6];
                diceImage[HORIZENTAL_STAND][0]=Bitmap.getBitmapResource("images/domino1H.png");
                diceImage[HORIZENTAL_STAND][1]=Bitmap.getBitmapResource("images/domino2H.png");
                diceImage[HORIZENTAL_STAND][2]=Bitmap.getBitmapResource("images/domino3H.png");
                diceImage[HORIZENTAL_STAND][3]=Bitmap.getBitmapResource("images/domino4H.png");
                diceImage[HORIZENTAL_STAND][4]=Bitmap.getBitmapResource("images/domino5H.png");
                diceImage[HORIZENTAL_STAND][5]=Bitmap.getBitmapResource("images/domino6H.png");
            }
        } catch(Exception e) {
        	if(Constants.DEBUG)
            	System.out.println("Error while loading Image Domino()");
        }
    }
    
    public Domino(byte dice1, byte dice2 ) {
        this.dice1=dice1;
        this.dice2=dice2;
        gesture=VERTICAL_STAND;
        transformToVertical();
        X=Y=0;
        paintBgFactor=false;
//      System.out.println(dice1+"  "+dice2);
        
        try{
            if(dominoImage==null) {
                dominoImage=new Bitmap[2][];
                dominoImage[VERTICAL_STAND]=new Bitmap[2];
                dominoImage[VERTICAL_STAND][DISABLED]=Bitmap.getBitmapResource("images/dominoVD.png");//"images/dominoVD.png"
                dominoImage[VERTICAL_STAND][ENABLED]=Bitmap.getBitmapResource("images/dominoVE.png");
                
                dominoImage[HORIZENTAL_STAND]=new Bitmap[2];
                dominoImage[HORIZENTAL_STAND][DISABLED]=Bitmap.getBitmapResource("images/dominoHD.png");//"images/dominoHD.png"
                dominoImage[HORIZENTAL_STAND][ENABLED]=Bitmap.getBitmapResource("images/dominoHE.png");
                
            }
            if(diceImage==null) {
                diceImage=new Bitmap[2][];
                diceImage[VERTICAL_STAND]=new Bitmap[6];
                diceImage[VERTICAL_STAND][0]=Bitmap.getBitmapResource("images/domino1V.png");
                diceImage[VERTICAL_STAND][1]=Bitmap.getBitmapResource("images/domino2V.png");
                diceImage[VERTICAL_STAND][2]=Bitmap.getBitmapResource("images/domino3V.png");
                diceImage[VERTICAL_STAND][3]=Bitmap.getBitmapResource("images/domino4V.png");
                diceImage[VERTICAL_STAND][4]=Bitmap.getBitmapResource("images/domino5V.png");
                diceImage[VERTICAL_STAND][5]=Bitmap.getBitmapResource("images/domino6V.png");
                
                diceImage[HORIZENTAL_STAND]=new Bitmap[6];
                diceImage[HORIZENTAL_STAND][0]=Bitmap.getBitmapResource("images/domino1H.png");
                diceImage[HORIZENTAL_STAND][1]=Bitmap.getBitmapResource("images/domino2H.png");
                diceImage[HORIZENTAL_STAND][2]=Bitmap.getBitmapResource("images/domino3H.png");
                diceImage[HORIZENTAL_STAND][3]=Bitmap.getBitmapResource("images/domino4H.png");
                diceImage[HORIZENTAL_STAND][4]=Bitmap.getBitmapResource("images/domino5H.png");
                diceImage[HORIZENTAL_STAND][5]=Bitmap.getBitmapResource("images/domino6H.png");
            }
        } catch(Exception e) {
        	if(Constants.DEBUG)
            	System.out.println("Error while loading Image Domino()");
        }
    }
    
    public String toString()
   	{
   		return ""+spinner+","+enabled1+","+enabled2+","+openLEFT+","+openTOP+","+openRIGHT+","+openBOTTOM+","+dice1+","+dice2+","+gesture+","+X+","+Y+","+paintBgFactor+"";
   	}
    
    public boolean isEnabled() {
        return (enabled1 || enabled2);
    }
    
    
    public void setEnabled(boolean set) {
        enabled1=set;
        enabled2=set;
    }
    
    public void setEnabledDice1(boolean set) {
        enabled1=set;
    }
    
    public void setEnabledDice2(boolean set) {
        enabled2=set;
    }
    
    
    
    public void swapDice() {
        byte temp=dice1;
        dice1=dice2;
        dice2=temp;
    }
    
    public boolean areTwins() {
        return (dice1==dice2);
    }
    
    public int getSum() {
        return ((int)(dice1+dice2));
    }
    
    public boolean isSumMultipleOf5() {
        return ((((int)(dice1+dice2))%5)==0);
    }
    
    public void transformToHorizental() {
        gesture=HORIZENTAL_STAND;
        
        if(areTwins()) {
            openLEFT=false;
            openTOP=true;
            openRIGHT=false;
            openBOTTOM=true;
        } else {
            openLEFT=true;
            openTOP=false;
            openRIGHT=true;
            openBOTTOM=false;
        }
        
    }
    
    
    public void transformToVertical() {
        gesture=VERTICAL_STAND;
        if(areTwins()) {
            openLEFT=true;
            openTOP=false;
            openRIGHT=true;
            openBOTTOM=false;
        } else {
            openLEFT=false;
            openTOP=true;
            openRIGHT=false;
            openBOTTOM=true;
        }
    }
    
    public boolean matchDice(Domino domino) {
        return
                (
                dice1==domino.dice1 ||
                dice1==domino.dice2 ||
                dice2==domino.dice1 ||
                dice2==domino.dice2
                );
    }
    
   	public boolean similarDomino(Domino domino)
	{
		return
		(
			dice1==domino.dice1 && dice2==domino.dice2||
			dice2==domino.dice1 && dice1==domino.dice2 
		);
	}
    
    public boolean matchDiceFigure(byte dice) {
        return
                (
                dice1==dice ||
                dice2==dice
                );
    }
    
    
    public boolean matchIfDominoFits(Domino domino) {
        
        if(!matchDice(domino))
            return false;
        
        
        switch(domino.gesture) {
            case VERTICAL_STAND:
                if(domino.spinner)
                    return true;
                else {
                    if(domino.areTwins())
                        return true;
                    else {
                        if(domino.openTOP && matchDiceFigure(domino.dice1))
                            return true;
                        if(domino.openBOTTOM && matchDiceFigure(domino.dice2))
                            return true;
                    }
                }
                return false;
            case HORIZENTAL_STAND:
                if(domino.areTwins())
                    return true;
                else {
                    if(domino.openLEFT && matchDiceFigure(domino.dice1))
                        return true;
                    if(domino.openRIGHT && matchDiceFigure(domino.dice2))
                        return true;
                }
                
                return false;
        }
        return false;
    }
    
    
    public boolean setToMatchDomino(Domino domino) {
        if(!matchDice(domino))
            return false;
        return false;
    }
    
    
    
    public void paint(Graphics g, boolean displayValue) {
        int factorX=0,factorY=0;
        if(paintBgFactor) {
            factorX=Background.bgX;
            factorY=Background.bgY;
        }
        
        try{
            switch(gesture) {
                case VERTICAL_STAND:
                    if(!paintBgFactor &&
                            ((X>=(Constants.PANEL1_X+1 + ((Domino.DICE_WIDTH+1)*Constants.MAX_DOMINO_IN_PANEL))) ||
                            (X<(Constants.PANEL1_X+1))))
                        return;
                    
                    if(!displayValue)
                    {
	                   g.drawBitmap(X+factorX, Y + factorY, DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][DISABLED], 0, 0);
                    }
                    else
                    {
                    	//dice 1
                    	if(enabled1)
	                        g.drawBitmap(X+factorX, Y + factorY, DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][ENABLED], 0, 0);
                    	else
	                        g.drawBitmap(X+factorX, Y + factorY, DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][DISABLED], 0, 0);
                    }

                    if(!displayValue)
                    {
               	        g.drawBitmap(X+factorX, Y + factorY+DICE_HEIGHT , DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][DISABLED], 0, 0);                    	
                    }
                    else
                    {
	                    //dice 2
    	                if(enabled2)
        	                g.drawBitmap(X+factorX, Y + factorY+DICE_HEIGHT , DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][ENABLED], 0, 0);
            	        else
                	        g.drawBitmap(X+factorX, Y + factorY+DICE_HEIGHT , DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][DISABLED], 0, 0);
                    }         
                    
                    if(displayValue)
                    {
                        if(dice1!=0)
                            g.drawBitmap( X + factorX, Y + factorY , DICE_WIDTH, DICE_HEIGHT, diceImage[VERTICAL_STAND][dice1-1], 0, 0);
                        if(dice2!=0)
                            g.drawBitmap( X + factorX, Y + DICE_HEIGHT + factorY, DICE_WIDTH, DICE_HEIGHT, diceImage[VERTICAL_STAND][dice2-1], 0, 0);
                    }
                    break;
                    
                case HORIZENTAL_STAND:
                    
                    if(enabled1)
                        g.drawBitmap(X+factorX, Y + factorY, dominoImage[HORIZENTAL_STAND][ENABLED].getWidth() , dominoImage[HORIZENTAL_STAND][ENABLED].getHeight() , dominoImage[HORIZENTAL_STAND][ENABLED], 0, 0);
                    else
                        g.drawBitmap(X+factorX, Y + factorY, dominoImage[HORIZENTAL_STAND][DISABLED].getWidth() , dominoImage[HORIZENTAL_STAND][DISABLED].getHeight() , dominoImage[HORIZENTAL_STAND][DISABLED], 0, 0);
                    
                    
                    if(enabled2)
                        g.drawBitmap(X + factorX+DICE_HEIGHT, Y + factorY , dominoImage[HORIZENTAL_STAND][ENABLED].getWidth() , dominoImage[HORIZENTAL_STAND][ENABLED].getHeight() , dominoImage[HORIZENTAL_STAND][ENABLED], 0, 0);
                    else
                        g.drawBitmap(X + factorX+DICE_HEIGHT, Y + factorY , dominoImage[HORIZENTAL_STAND][DISABLED].getWidth() , dominoImage[HORIZENTAL_STAND][DISABLED].getHeight() , dominoImage[HORIZENTAL_STAND][DISABLED], 0, 0);
                    
                    if(displayValue)
                    {
                        if(dice1!=0)
                            g.drawBitmap( X + factorX, Y + factorY , DICE_WIDTH, DICE_HEIGHT, diceImage[HORIZENTAL_STAND][dice1-1], 0, 0);
                        if(dice2!=0)
                            g.drawBitmap( X + DICE_HEIGHT + factorX, Y + factorY, DICE_WIDTH, DICE_HEIGHT, diceImage[HORIZENTAL_STAND][dice2-1], 0, 0);
                    }
                    break;
            }
        } catch(Exception e) {
        	if(Constants.DEBUG)
            	System.out.println("Error while painting Domino="+(X + factorX)+"," +(Y + DICE_HEIGHT + factorY)+",dice1="+dice1+",dice2="+dice2);
        }
        
    }
    public byte getDice1() {
        return dice1;
    }
    public byte getDice2() {
        return dice2;
    }
    
    public Domino getClone() {
        Domino newDomino= new Domino(dice1, dice2);
        newDomino.spinner=spinner;
        newDomino.enabled1=enabled1;
        newDomino.enabled2=enabled2;
        
        newDomino.openLEFT=openLEFT;
        newDomino.openTOP=openTOP;
        newDomino.openRIGHT=openRIGHT;
        newDomino.openBOTTOM=openBOTTOM;
        
        newDomino.gesture=gesture;
        newDomino.X=X;
        newDomino.Y=Y;
        newDomino.paintBgFactor=paintBgFactor;
        return newDomino;
    }
    
    
    public void paintSingleDomino(Graphics g, int x, int y) {
        try{
            if(enabled1)
                g.drawBitmap( x, y, DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][ENABLED], 0, 0);
            else
                g.drawBitmap( x, y, DICE_WIDTH, DICE_HEIGHT, dominoImage[VERTICAL_STAND][DISABLED], 0, 0);
            if(dice1!=0)
                g.drawBitmap( x, y, DICE_WIDTH, DICE_HEIGHT, diceImage[VERTICAL_STAND][dice1-1], 0, 0);
        }catch(Exception e) {
        	if(Constants.DEBUG)
            	System.out.println("Error in paintSingleDomino="+e);
        }
    }
    
	public byte[] toBytes()
	{
		byte[] data=(new String(toString())).getBytes();
		return data;
	}

	public static Domino fromBytes(byte[] buf)
	{
		try{
		
		String data=new String(buf);
		Domino domino=new Domino((byte)0, (byte)0);
		int index=0;
		String str;

		
		//spinner		
		index=data.indexOf(",");
		str=data.substring(0,index);
		
		data=data.substring(index+1);		

		if(str.equals("true"))
			domino.spinner=true;
		else
			domino.spinner=false;
		//enabled1		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		if(str.equals("true"))
			domino.enabled1=true;
		else
			domino.enabled1=false;
		//enabled2		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		if(str.equals("true"))
			domino.enabled2=true;
		else
			domino.enabled2=false;			
		//openLEFT		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		if(str.equals("true"))
			domino.openLEFT=true;
		else
			domino.openLEFT=false;
		//openTOP		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		if(str.equals("true"))
			domino.openTOP=true;
		else
			domino.openTOP=false;	
		//openRIGHT		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		if(str.equals("true"))
			domino.openRIGHT=true;
		else
			domino.openRIGHT=false;
		//openBOTTOM		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		if(str.equals("true"))
			domino.openBOTTOM=true;
		else
			domino.openBOTTOM=false;
		//dice1		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		domino.dice1=Byte.parseByte(str);  

		//dice2		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		domino.dice2=Byte.parseByte(str);  
		//gesture		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		domino.gesture=Byte.parseByte(str);  

		//X		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		domino.X=Integer.parseInt(str);  
	
		//Y		
		index=data.indexOf(",");
		str=data.substring(0,index);
		data=data.substring(index+1);		
		domino.Y=Integer.parseInt(str);  

		
		//paintBgFactor		
		if(data.equals("true"))
			domino.paintBgFactor=true;
		else
			domino.paintBgFactor=false;
		 return domino;
		}catch(Exception e)
		{
			if(Constants.DEBUG)
				System.out.println(" Domino fromBytes="+e);
			return null;
		}
		
	}
}
