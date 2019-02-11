// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg, Gavin Sanchez
// I2C Receiver for Arduino

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class M_I2C {

	private static I2C Wire = new I2C(Port.kOnboard, 4);//uses the i2c port on the RoboRIO
														//uses address 4, must match arduino
	private static final int MAX_BYTES = 32;
	
	public void write(String input){//writes to the arduino 
			char[] CharArray = input.toCharArray();//creates a char array from the input string
			byte[] WriteData = new byte[CharArray.length];//creates a byte array from the char array
			for (int i = 0; i < CharArray.length; i++) {//writes each byte to the arduino
				WriteData[i] = (byte) CharArray[i];//adds the char elements to the byte array 
			}
			Wire.transaction(WriteData, WriteData.length, null, 0);//sends each byte to arduino
			
	}
	
	public Arduino getArduino() {//reads the data from arduino and saves it
		String info[] = read().split("\\|");//everytime a "|" is used it splits the data,
											//and adds it as a new element in the array
		Arduino sensorData = new Arduino();  //creates a new packet to hold the data 
		if(info[0].equals("none") || info[0].equals("")){//checks to make sure there is data 
			sensorData.x = -1;//the x val will never be -1 so we can text later in code to make sure 
					   //there is data
			sensorData.y = -1;
			sensorData.area = -1;
		}else if(info.length == 4){//if there is an x, y, and area value the length equals 3
			sensorData.x = Double.parseDouble(info[0]);//set x
			sensorData.y = Double.parseDouble(info[1]);//set y
			sensorData.area = Double.parseDouble(info[2]);//set area
			sensorData.distance = Double.parseDouble(info[3]);
		}
		return sensorData;
	}

	public double getDistance() {
		Arduino receive = getArduino();
		return receive.distance;
	}
	
	private String read(){//function to read the data from arduino
		byte[] data = new byte[MAX_BYTES];//create a byte array to hold the incoming data
		Wire.read(4, MAX_BYTES, data);//use address 4 on i2c and store it in data
		String output = new String(data);//create a string from the byte array
		int pt = output.indexOf((char)255);
		return (String) output.subSequence(0, pt < 0 ? 0 : pt);
	}

}