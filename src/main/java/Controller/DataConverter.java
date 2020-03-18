package Controller;

public class DataConverter {

    public static byte convertBinTOByteSigned(char[] binArr){
        int power=0;
        double val = 0;
        for(int i = binArr.length-1 ; i>=0; i--){
            if(i==0){
                val += Integer.parseInt(binArr[i]+"") * Math.pow(-2, power++);
            }else
                val += Integer.parseInt(binArr[i]+"") * Math.pow(2, power++);
        }
        return (byte)val;
    }
}
