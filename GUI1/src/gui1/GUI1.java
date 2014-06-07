/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui1;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ricardo KAKA
 */
public class GUI1 implements ActionListener {

    Player player[] = new Player[5];
   
    ArrayList winpos = new ArrayList();
    ArrayList position = new ArrayList();
    
    Enemy monster[] = new Enemy[9];
    HashMap NonBlock = new HashMap();
    String name[] = {"tilu", "Akash", "Kaushik the sivaprasad", "Human", "Viki de drunk", "Roki"};
    static int currentplayer;
    HashMap Monster = new HashMap();
    //  HashMap startingpoint = new HashMap();
    HashMap endingpoint = new HashMap();
    HashMap Activated = new HashMap();
    HashMap hero = new HashMap();
    JFrame frame1;
    JFrame frame2;
    boolean checkmonster = false;
    JFrame frame;
    JButton[][] grid;
    JButton[][] grid1;
    int numofcolumn;
    JLabel label;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    public void BuildGUI(int width, int length) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
   
                
        frame = new JFrame();
        frame.setLayout(new GridLayout(width, length));
        numofcolumn = width;
        grid = new JButton[width][length];
        int counter = 0;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                grid[x][y] = new JButton();
                (grid[x][y]).setActionCommand("" + counter);
                (grid[x][y]).addActionListener(this);
                frame.add(grid[x][y]);
                counter++;
            }
        }

        for (int i = 1; i < 9; i++) {
            
            Enemy enemy = (Enemy) Monster.get(i);
            int place = enemy.getPosition();
         //   int place = (int) Monster.get(i);
            String ij = getij(place);
            StringTokenizer str = new StringTokenizer(ij, ",");
            int indexi = Integer.parseInt(str.nextToken());
            int indexj = Integer.parseInt(str.nextToken());


            if(i < 5 )
            grid[indexi][indexj].setIcon(new ImageIcon("images\\monster"+i+".gif"));
            else
            grid[indexi][indexj].setIcon(new ImageIcon("images\\monster"+1+".gif"));
            
        }
        for (int i = 1; i < 5; i++) {
            int place = (int) hero.get(i);
            String ij = getij(place);
            StringTokenizer str = new StringTokenizer(ij, ",");
            int indexi = Integer.parseInt(str.nextToken());
            int indexj = Integer.parseInt(str.nextToken());
            grid[indexi][indexj].setIcon(new ImageIcon("images\\Hero"+i+".gif"));
       
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void start() {

   
        
        
        winpos.add(95);
        winpos.add(92);
        winpos.add(91);
        
        for (int i = 1; i < 5; i++) {
            player[i] = new Player();
            player[i].setName(name[i]);
            player[i].setVitality(20);
            player[i].setHealth(100);
            player[i].setExp(40);
            position.add(i);
        }
        for (int i = 1; i < 9; i++) {
            
            
            monster[i] = new Enemy();
            monster[i].setName("Monster" + i);
            monster[i].setID(i);
            
            Random r = new Random();
            int pos = r.nextInt(99);
            while(position.contains(pos)){
                pos = r.nextInt(99);
            }
            
            position.add(pos);
            System.out.println(pos);
            monster[i].setPosition(pos);
            monster[i].setHealth(3);
            if(i < 5)
            monster[i].setImagePath("images\\Monster"+ i+".gif");
            else
            monster[i].setImagePath("images\\Monster"+ 1+".gif");
        }

        Monster.put(1, monster[1]);

        Monster.put(2, monster[2]);

        Monster.put(3, monster[3]);

        Monster.put(4, monster[4]);

        Monster.put(5, monster[5]);

        Monster.put(6, monster[6]);

        Monster.put(7, monster[7]);

        Monster.put(8, monster[8]);
        hero.put(1, 1);

        hero.put(2, 2);
        hero.put(3, 3);
        hero.put(4, 4);

    }

    public void createinventoryframe() {
        frame1 = new JFrame("status");
        frame2 = new JFrame("Activated");
        frame1.setLayout(new GridLayout(7, 1));
        frame2.setLayout(new GridLayout(1, 1));


        label = new JLabel("Name Player :" + player[1].getName());
        label1 = new JLabel("Health :" + player[1].getHealth());
        label2 = new JLabel("Strength :" + player[1].getStrength());
        label3 = new JLabel("Experience :" + player[1].getExp());
        label4 = new JLabel("Activated Monsters" + Activated.size());
        label5 = new JLabel();
        label5.setIcon(new ImageIcon("activation.png"));

        frame2.add(label5);

        frame1.add(label);
        frame1.add(label1);
        frame1.add(label2);
        frame1.add(label3);
        frame1.add(label4);
        // frame1.add();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame2.pack();
        frame1.setVisible(true);


    }

    public static void main(String args[]) {
        try {
            // I can do it randomly but here i am putting it manually.
            // Building the intial Scenario ////
            GUI1 gg = new GUI1();
            gg.start();
            try {
                gg.BuildGUI(10, 10);
                          
          
        
            } catch(Exception e){
            
            }
            /// Now the player take the turn accoringly
            // createinventoryframe();
            currentplayer = 1;
            gg.createinventoryframe();
            System.out.println("Player 1 take the turn");

             UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
                           UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean sameplace = false;
        boolean sameplacehero = false ;
        boolean moves = false ;
     // Loop for satisfying the players.
     // Here I am Writing the code for Decreasing the health of the player  /////////////////////////
        int heroplace = (int) hero.get(currentplayer);
        String crossplace = getij(heroplace);
        StringTokenizer strtoken = new StringTokenizer(crossplace, ",");
        int conflictx = Integer.parseInt(strtoken.nextToken());
        int conflicty = Integer.parseInt(strtoken.nextToken());
    
        //////////////////////////////////////////////////////////////////////////////////

        System.out.println("Event happend at" + e.getActionCommand());
        String ij = getij(Integer.parseInt(e.getActionCommand()));
        int currentposition = Integer.parseInt(e.getActionCommand());
        
        boolean finalwin = checkwin(currentposition);
        int movement = caldistance(conflictx,conflicty,currentposition);
        if(movement > 2) moves = true ;
        
        
        ///////////////////////////////////////////////////// Experimentation ///////////
        
        /////   Monster will hit you according to their ranges
        
            for (int x = 1; x < 9; x++) {
            if (Monster.get(x) != null) {
                
                //// Here Get the monster and hit it according to it.
                
                Enemy enemy = (Enemy) Monster.get(x);
                int getplace = enemy.getPosition();
                String Monsterplace = getij(getplace);
                StringTokenizer token = new StringTokenizer(Monsterplace, ",");
                int indexi = Integer.parseInt(token.nextToken());
                int indexj = Integer.parseInt(token.nextToken());

                StringTokenizer str = new StringTokenizer(ij, ",");
                int row = Integer.parseInt(str.nextToken());
                int column = Integer.parseInt(str.nextToken());

                int distance;
                
                if(moves == false)         ///// sorry for this confusing parameter //// it means you can move
                 distance= Math.abs(row - indexi) + Math.abs(column - indexj);
                else
                 distance= Math.abs(conflictx - indexi) + Math.abs(conflicty - indexj);   
                
                if (distance <= 2) {
                    int playerhealth = player[currentplayer].getHealth();
                    player[currentplayer].setHealth(playerhealth - 1);

                    //////////////  Reduce the health and everything
                    
                    //  System.out.println( "-------------------"+currentplayer+" "+player[currentplayer].getHealth());
                }
            }
        }
        
        ////////////////////////////////////////////////////////////////////////////////
        
        
        
        if(finalwin == false){
        
        StringTokenizer str = new StringTokenizer(ij, ",");
        int i = Integer.parseInt(str.nextToken());
        int j = Integer.parseInt(str.nextToken());
        //     NonBlock.put(Integer.parseInt(e.getActionCommand()), true);

        // Now you hit hard the Monsters. 
        int Monsterplacing = 0;
        for (int index = 1; index < 9; index++) {
            if (sameplace == false) {
                if (Monster.get(index) != null) {
                    
                    Enemy enemy = (Enemy) Monster.get(index);
                    Monsterplacing = enemy.getPosition();
                    
                    //    int place = (int)hero.get(currentplayer);
                    System.out.println("Monster place" + Monsterplacing);
                    System.out.println("hero place" + currentposition);
                    if (Monsterplacing == currentposition) {
                        sameplace = true;
                    }
                }
            }
        }
        
        if(hero.containsValue(currentposition)){
            sameplacehero = true ;
        }

        
        if(sameplacehero || moves){
            
            if(sameplacehero)
            System.out.println("You cannot step on the player");
            else
            System.out.println("You cannot Move More Than two steps");    
            
        }else{
        

        if (sameplace) {
            
            System.out.println("Same place");
            // here the code for hitting the monster. 
            int hero1 = (int) hero.get(currentplayer);
            int distance = caldistance(i,j,hero1);
            if (distance <= 2) {
                // code for hit the monster. if monster's health is less than it would be killed.

                System.out.println("Wow you hit the monster" + Monsterplacing);
                // checking

                int identifier = 1;
                boolean loopcondition = false;
                for (int rr = 1; rr < 9; rr++) {
                    if (loopcondition == false) {
                        if (Monster.get(rr) != null) {
                            
                            //
                            Enemy enemy =(Enemy) Monster.get(rr);
                            int value = enemy.getPosition();
                            
                            identifier = rr;
                            if (value == Monsterplacing) {
                                loopcondition = true;
                            }
                        }
                    }
                }

                ///  Hit the monster and reduce health and if monster will be killed then just trasure will appear.
                

                int health = monster[identifier].getHealth();
                health = health - 3;
                if (health <= 0) {
                    // Now Remove the character from the board. 
                    // And remove that guy from monster and activated list.
                    //        (grid[i][j]).setIcon(new ImageIcon("money.png"));
                    (grid[i][j]).setIcon(null);
                    Monster.remove(identifier);
                    Activated.remove(identifier);
                    player[currentplayer].setExp(player[currentplayer].getExp() + 5);


                ////////////////////  else only reduce the health of the monster.   
                    
                } else {
                    
                }

            } else {

                System.out.println("Hey Fucker you cannot hit that guy");

                JFrame board = new JFrame();
                board.setLayout(new GridLayout(1, 1));
                JLabel puticon = new JLabel();
                puticon.setIcon(new ImageIcon("hit.png"));

                board.add(puticon);
                board.pack();
                board.setVisible(true);

            }
        } else {
            
            System.out.println("int i" + i + "int j" + j);
            grid[i][j].setIcon(new ImageIcon("images\\Hero"+currentplayer+".gif"));

            int place = (int) hero.get(currentplayer);
            String stringplace = getij(place);
            StringTokenizer str1 = new StringTokenizer(stringplace, ",");
            int ii = Integer.parseInt(str1.nextToken());
            int jj = Integer.parseInt(str1.nextToken());
            //   System.out.println("int i"+i+"int j"+j);
            grid[ii][jj].setIcon(null);
            hero.put(currentplayer, Integer.parseInt(e.getActionCommand()));

            for (int x = 1; x < 9; x++) {
                if (Monster.get(x) != null) {
                    Enemy enemy =(Enemy) Monster.get(x);
                    int getplace = enemy.getPosition();
                   
                    String Monsterplace = getij(getplace);
                    StringTokenizer token = new StringTokenizer(Monsterplace, ",");
                    int indexi = Integer.parseInt(token.nextToken());
                    int indexj = Integer.parseInt(token.nextToken());
                  

                    int distance = Math.abs(i - indexi) + Math.abs(j - indexj);

                    if (distance <= 2) {

                        grid[indexi][indexj].setBackground(Color.GREEN);
                        Activated.put(x, getplace);

                        if (checkmonster == false) {
                            checkmonster = true;
                            //  frame1.add(label5);
                            frame2.setVisible(true);
                        }

                    }
                }
            }

            label4.setText("Activated Monsters" + Activated.size());
            System.out.println("List of activated Monsters" + Activated.toString());

         }
        }
        label.setText("Name Player :" + player[currentplayer].getName());

        label1.setText("Health :" + player[currentplayer].getHealth());
        label2.setText("Strength :" + player[currentplayer].getStrength());
        label3.setText("Experience :" + player[currentplayer].getExp());



        System.out.println("Players Current position is " + Integer.parseInt(e.getActionCommand()));
        currentplayer++;

        currentplayer = currentplayer % 5;
        if (currentplayer == 0) {
            currentplayer = 1;
        }
       }else{
            
            
            System.out.println("You won the match && and it is over.");
       
            
       }
    }

    public void startingpoint(int width, int length) {
        frame = new JFrame();
        frame.setLayout(new GridLayout(width, length));

        grid1 = new JButton[width][length];
        int counter = 0;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                grid1[x][y] = new JButton("" + counter);
                (grid1[x][y]).addActionListener(new GUI1.akash());
                frame.add(grid1[x][y]);
                counter++;
            }
        }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }

    public int getMapping(int i, int j) {
        int value = (i * numofcolumn) + j;
        return value;
    }

    public int caldistance(int i,int j,int place){
    
            
            String stringplace = getij(place);
            StringTokenizer str1 = new StringTokenizer(stringplace, ",");
            int ii = Integer.parseInt(str1.nextToken());
            int jj = Integer.parseInt(str1.nextToken());

            int distance = Math.abs(i - ii) + Math.abs(j - jj);
            return distance ;
    }
    
    public String getij(int value) {
        int i = value / numofcolumn;
        int j = value % numofcolumn;
        return i + "," + j;
    }
    
    public boolean checkwin(int finalposition){
        boolean result = false;
        if(winpos.contains(finalposition)){
            if(Monster.size() == 0){
                result = true;
            }
        }
        
        return result ;  
    }

    class akash implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            System.out.println("Event happend at" + e.getActionCommand());
            String ij = getij(Integer.parseInt(e.getActionCommand()));
            //   startingpoint.put(Integer.parseInt(e.getActionCommand()), true);
            StringTokenizer str = new StringTokenizer(ij, ",");
            int i = Integer.parseInt(str.nextToken());
            int j = Integer.parseInt(str.nextToken());



            grid1[i][j].setIcon(new ImageIcon("dot.png"));

        }
    }
}
