/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LionArt
 */

import java.util.Scanner;

public class ApplicationHangman {

    /**
     * @param level
     */
    public static void drawLevel(char level[][])
    {
        for(int i=0;i<14;i++)
        {
            for(int j=0;j<23;j++)
            {
                System.out.print(level[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void drawGameState(char level[][],String encrypted,int lives,String guesses)
    {
        System.out.println("Number of mistakes left: "+lives);
        System.out.println("Guesses: "+guesses);
        System.out.println("Please guess a letter!");
    }
    
    public static String getEncryptedWord(String word)
    {
        String encrypted="";
        for(int j=0;j<word.length();j++)
            {
                encrypted=encrypted+'_';
                if(j<word.length()-1)
                encrypted=encrypted+' ';
            }
        return encrypted;
    }
    
    public static boolean win(String word,String encrypted)
    {
        int pos=0;
        for(int i=0;i<word.length();i++)
        {
            if(word.charAt(i)!=encrypted.charAt(pos))
            return false;
            else
            pos=pos+2;
        }
        System.out.println();
        System.out.println("You won!");
        System.out.println("Secret: "+word);
        System.out.println();
        return true;
    }
    
    public static char[][] getModifiedLevel(int lives,char oldLevel[][])
    {
        char level[][]=oldLevel;
        switch(lives)
        {
            case 6:
            {
                level[2][16]='|';
                level[3][16]='|';
                level[4][16]='|';
                level[5][16]='&';
                break;
            }
            case 5:
            {
                level[4][15]='(';
                level[4][16]='_';
                level[4][17]=')';
                break;
            }
            case 4:
            {
                level[6][16]='|';
                level[7][16]='|';
                level[8][16]='|';
                break;
            }
            case 3:
            {
                level[6][15]='/';
                level[7][14]='/';
                break;
            }
            case 2:
            {
                level[6][17]='\\';
                level[7][18]='\\';
                break;
            }
            case 1:
            {
                level[9][15]='/';
                level[10][14]='/';
                break;
            }
            case 0:
            {
                level[9][17]='\\';
                level[10][18]='\\';
                break;
            }

        }
        return level;
    }
    
    public static void main(String[] args) 
    {      
        Scanner reader=new Scanner(System.in);
        int n=reader.nextInt();
        for(int i=0;i<n;i++)
        {
            char initializedLevel [][]={
            {'+','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+'},
            {'|',' ',' ',' ',' ','_','_','_','_','_','_','_','_','_','_','_','_',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|','/',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ',' ','_','_','|','_','_',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'+','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+'},
            };
            char level[][]=initializedLevel;
            String word=reader.next();
            int maxAnswers=reader.nextInt();
            int answers=0;
            String encrypted=getEncryptedWord(word);
            String guesses="-";
            int lives=7;
            System.out.println("Welcome to the Hangman Game!");
            drawLevel(level);
            System.out.println("Secret word:");
            System.out.println(encrypted);
            System.out.println();
            drawGameState(level,encrypted,lives,guesses);
            while(lives>0 && answers<maxAnswers)
            {
                int good=0;
                char letter=reader.next().charAt(0);
                System.out.println("Your choice: "+letter);
                if((letter>64 && letter<133) || (letter>96 && letter<123))
                {
                    letter=Character.toUpperCase(letter);
                    
                    if(guesses.indexOf(letter)==-1)
                    {
                        if(word.indexOf(letter)==-1)
                        {
                            lives--;
                            if(guesses.equals("-"))
                            {
                                guesses=""+letter;
                            }
                            else
                            guesses=guesses+letter;
                            good=-1;
                        }
                        else 
                        {
                            for(int k=0;k<word.length();k++)
                            {
                                if(letter==word.charAt(k))
                                {
                                    int pos;
                                    switch(k)
                                    {
                                        case 0:
                                        {
                                            pos=0;
                                            break;
                                        }
                                        case 1:
                                        {
                                            pos=2;
                                            break;
                                        }
                                        default:
                                        {
                                            pos=k*2;
                                            break;
                                        }
                                    }
                                    if(pos==0)
                                    encrypted=letter+encrypted.substring(1); 
                                    else
                                    encrypted=encrypted.substring(0,pos)+letter+encrypted.substring(pos+1);
                                }
                            }
                            if(guesses.equals("-"))
                            {
                                guesses=""+letter;
                            }
                            else
                            guesses=guesses+letter;
                            good=1;
                        }
                    }
                    else
                    {
                        System.out.println("You already guessed that letter!");
                        lives--;
                        level=getModifiedLevel(lives,level);
                        drawLevel(level);
                    }
                }
                else
                {
                    System.out.println("Only Latin alphabet letters!");
                    lives--;
                    level=getModifiedLevel(lives,level);
                    drawLevel(level);
                }
                answers++;
                if(lives<=0)
                {
                    System.out.println("Game Over!");
                    System.out.println("Secret: "+word);
                    break;
                }
                if(!win(word,encrypted))
                {
                    if(good==-1)
                    {
                        System.out.println("Nope!");
                        level=getModifiedLevel(lives,level);
                        drawLevel(level);
                    }
                    else if(good==1)
                    {
                        System.out.println("Nice!");
                        System.out.println("Secret word:");
                        System.out.println(encrypted);
                        System.out.println();
                    }
                    if(answers>=maxAnswers)
                    {
                        break;
                    }
                    else
                    {
                        level=getModifiedLevel(lives,level);
                        drawGameState(level,encrypted,lives,guesses);
                    }
                }
                else
                break;
            }
        }
        
    }
    
}
