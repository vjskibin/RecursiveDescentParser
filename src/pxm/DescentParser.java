package pxm;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*
  МОЙ СЛАДКИЙ БЕНС НАУР

  A -> E
  E -> T + E | T - E | T
  T -> F * T | F / T | F
  F -> P ^ F | P
  P ->  L | U | UL | (A)
  U -> + | -
  L -> D | DL
  D -> 0 | 1 | ... | 8 | 9
*/


import java.util.Scanner;

public class DescentParser {
    private static String line; //Daria Viktorovna izvinite pozhaluysta
    private static int i; //Position


    // A -> I = E | E
    static boolean A()
    {
        //Pls do not ask
        return E();
    }

    // E -> T + E | T - E | T
    static boolean E()
    {
        //We wish you a merry christmas
        if (T())
        {
            if (line.charAt(i) == '+' || line.charAt(i) == '-')
            {
                if(i < line.length()) ++i;
                return E();
            }
            return true;
        }

        return false;
    }

    // T -> F * T | F / T | F
    static boolean T()
    {

        if (F())
        {
            if(line.charAt(i) == '*' || line.charAt(i) == '/')
            {
                if(i < line.length()) ++i;
                return F();
            }
            return true;
        }
        return false;
    }

    // F -> P ^ F | P
    static boolean F()
    {
        if(P())
        {
            if(line.charAt(i) == '^')
            {
                if(i < line.length()) ++i;
                return F();
            }
            return true;
        }
        return false;
    }


    // P -> L | U | UL | (A)
    static boolean P()
    {
        if(L()) return true;
        else if (line.charAt(i) == '+' || line.charAt(i) == '-') L();
        else if (line.charAt(i) == '(')
        {
            if(i < line.length()) ++i;
            if(A())
            {
                if(line.charAt(i) == ')') {
                    if(i < line.length()) ++i;
                    return true;
                }
                else
                    return false;
            }
        }
       // else return false;

        return false;
    }



    // L -> D | DL
    static boolean L()
    {

        if (line.charAt(i) >= '0' && line.charAt(i) <= '9')
        {
            if(i < line.length()) ++i;
            /*if (!L() && line.charAt(i) != '+'&& line.charAt(i) != '-'&& line.charAt(i) != '*' && line.charAt(i) != '/' && line.charAt(i) != ' ')
            return false;
            else return true;*/ //Просто хотел попробовать, ничего не вышло
            L();

            return true;
        }
        //else return false;

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean preCheck = true;
        boolean tmpCheck = false;
        List<Character> usedChars =  Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9','(',')','+','-','/','*',' ');
        line = sc.nextLine();

        //Я очень ровный паря, так зачем писать это в спуске?
        for(int j = 0; j < line.length(); j++)
        {
            tmpCheck = false;
            for(Character ch : usedChars)
                if(ch == line.charAt(j))
                    tmpCheck = true;
            if (!tmpCheck)
            {
                preCheck = false;
                break;
            }
        }


        i = 0;

        line = line + " ";
        if(A() && preCheck) System.out.println("Ништяк");
        else System.out.println("Отстой");


    }
}
