import java.io.*;
import java.util.StringTokenizer;

class Main
{
    String ReadLn()  // utility function to read from stdin
    {
        byte buff[] = new byte[255];
        int i = 0, a = -1;

        try
        {
            while (i < 255)
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

    public static void main(String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dynamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin() {
        String[] indicators;
        String digitalSymbols[] = {
                "YYYYYYN",  //0
                "NYYNNNN",  //1
                "YYNYYNY",  //2
                "YYYYNNY",  //3
                "NYYNNYY",  //4
                "YNYYNYY",  //5
                "YNYYYYY",  //6
                "YYYNNNN",  //7
                "YYYYYYY",  //8
                "YYYYNYY"   //9
        };

        int inputCount;
        String input;
        StringTokenizer idata;

        input = ReadLn ();
        idata = new StringTokenizer (input);
        inputCount = Integer.parseInt (idata.nextToken());

        while(inputCount  != 0)
        {
            indicators = new String[inputCount];
            boolean dataIsCountDown = false;

            for (int i = 0; i < inputCount; i++)
                indicators[i] = ReadLn();

            for (int j = 9; j >= 0; j--) {
                if(isCountDown(indicators, digitalSymbols, j, 0))
                {
                    dataIsCountDown = true;
                    break;
                }
            }

            if(dataIsCountDown)
                System.out.println("MATCH");
            else
                System.out.println("MISMATCH");

            input = ReadLn ();
            idata = new StringTokenizer (input);
            inputCount = Integer.parseInt (idata.nextToken());
        }
    }

    boolean isCountDown(String[] indicators, String[] digitalSymbols, int testNumber, int index)
    {
        for (int i = 0; i < 7; i++) {
            if (indicators[index].charAt(i) == 'Y' && digitalSymbols[testNumber].charAt(i) != 'Y') return false;
        }

        if(index + 1 < indicators.length) {
            if (testNumber - 1 < 0) return false;
            if (!isCountDown(indicators, digitalSymbols, testNumber - 1, index + 1)) return false;
        }

        return true;
    }
}