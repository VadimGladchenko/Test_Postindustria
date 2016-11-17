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
        int digitalSymbols[] = { 0x7E, 0x30, 0x6D, 0x79, 0x33, 0x5B, 0x5F, 0x70, 0x7F, 0x7B };
        int indicators[];

        int inputCount;
        String input;
        StringTokenizer idata;

        input = ReadLn();
        idata = new StringTokenizer(input);
        inputCount = Integer.parseInt(idata.nextToken());

        while(inputCount != 0)
        {
            boolean dataIsCountDown = false;
            indicators = new int[inputCount];

            for (int i = 0; i < inputCount; i++) {
                input = ReadLn();
                for (int j = 0; j < 7; j++)
                {
                    if(input.charAt(j) == 'Y') indicators[i] |= (1) << (6-j);
                    else indicators[i] |= (0) << (6-j);
                }
            }

            for (int j = 9; j >= 0; j--) {
                if(isCountDown(indicators, digitalSymbols, j, 0, 0))
                {
                    dataIsCountDown = true;
                    break;
                }
            }

            if(dataIsCountDown)
                System.out.println("MATCH");
            else
                System.out.println("MISMATCH");

            input = ReadLn();
            idata = new StringTokenizer(input);
            inputCount = Integer.parseInt(idata.nextToken());
        }
    }

    boolean isCountDown(int[] indicators, int[] digitalSymbols, int testNumber, int index, int badMask)
    {
        if((indicators[index] & (127 - digitalSymbols[testNumber])) != 0) return false;     //implication s->s* = (s* & !s)
        if((indicators[index] & badMask) != 0) return false;

        badMask |= indicators[index] ^ digitalSymbols[testNumber];

        if(index + 1 < indicators.length) {
            if (testNumber - 1 < 0) return false;
            if (!isCountDown(indicators, digitalSymbols, testNumber - 1, index + 1, badMask)) return false;
        }

        return true;
    }
}