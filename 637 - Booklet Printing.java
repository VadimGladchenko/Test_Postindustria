import java.io.*;
import java.util.*;

class Main
{
    String ReadLn ()  // utility function to read from stdin
    {
        byte buff[] = new byte[8];
        int i = 0, a = -1;

        try
        {
            while (i < 8)
            {
                a = System.in.read();
                if ((a < 0) || (a == '\n')) break;
                buff[i++] += a;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((a < 0) && (i == 0)) return (null);  // eof
        return (new String (buff, 0, i));
    }

    public static void main (String args[])
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
        int n;
        String input;
        while(!(input = ReadLn()).equals(""))
        {
            if((n = Integer.parseInt(input)) == 0) return;
                printing(n);
        }

    }

    void printing(int pages)
    {
        int emptyPages = 0;

        if(pages % 4 == 1) emptyPages = 3;  // 3 pass
        if(pages % 4 == 2) emptyPages = 2;  // 2 pass
        if(pages % 4 == 3) emptyPages = 1;  // 1 pass
        if(pages == 1) emptyPages = 1;      // 1 pass

        System.out.println("Printing order for " + pages + " pages:");

        for(int frontCount = 1, backCount = pages, sheet = 1; frontCount <= backCount; )
        {
            if(frontCount % 2 == 1)
                if( emptyPages == 0) {
                    System.out.println("Sheet " + sheet + ", front: " + backCount + ", " + frontCount);
                    backCount--;
                }
                else {
                    System.out.println("Sheet " + sheet + ", front: " + "Blank" + ", " + frontCount);
                    emptyPages--;
                }
            else {
                if( emptyPages == 0) {
                    System.out.println("Sheet " + sheet + ", back : " + frontCount + ", " + backCount);
                    backCount--;
                }
                else {
                    System.out.println("Sheet " + sheet + ", back : " + frontCount + ", " + "Blank");
                    emptyPages--;
                }
            }

            if(frontCount % 2 == 0) sheet++;
            frontCount++;
        }
    }
}