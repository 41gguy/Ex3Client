
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class Ex3Client {

    public static void main(String[] args) {

    	System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "38103");

        try {
        	Socket socket = new Socket("localhost", 38103);
            InputStream is = socket.getInputStream();
            DataInputStream ds = new DataInputStream(is);
            
            int initialbyte = ds.readUnsignedByte();
            int[] bytearray = new int[initialbyte];
            
            
            for (int i = 0; i < initialbyte; i++) {
            	bytearray[i] = ds.readUnsignedByte();
            }
            
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            dos.writeInt(checksum(bytearray));
            
           try {
        	   int response = ds.read();
        	   System.out.println("Response Good");
           }
           catch (Exception e) {
        	   System.out.println(e);
        	   System.out.println("Response Bad");
           }
            
           System.out.println("Disconnecting from the server.");
           
        }
        catch (Exception e) {
        	System.out.print(e);
        }
    }
    
	public static int checksum(int[] b) {
    	
    	String[] hexbytearr = new String[b.length/2];
    	int checksum = 0;
    	
    	int t = 0;
    	for (int i = 0; i < b.length/2; i++) {
    		StringBuilder sb = new StringBuilder();
    		sb.append(String.format("%2s", Integer.toHexString(b[t])).replace(' ', '0'));
    		t++;
    		sb.append(String.format("%2s", Integer.toHexString(b[t])).replace(' ', '0'));
    		t++;
    		
    		hexbytearr[i] = sb.toString();
    		
    		checksum += Integer.parseInt(hexbytearr[i], 16);
    		if (checksum > 65535) {
    			
    			checksum = checksum%65535 + Character.digit(Integer.toHexString(checksum).charAt(0), 16);
    		}
    		
    	}
    	
    	return checksum; 
    }
    	
}
