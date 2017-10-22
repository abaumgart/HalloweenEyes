package halloween;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.internal.ev3.EV3IOPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Programm {

	
	public static void main(String[] args) {
		EV3IOPort.closeAll(); // Schlie�t alle Ports 

		// Motorobjekte anlegen
		RegulatedMotor m1 = new EV3LargeRegulatedMotor(MotorPort.A); 
		RegulatedMotor m2 = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor m3 = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor m4 = new EV3LargeRegulatedMotor(MotorPort.D);
		
		// Thread anlegen. Konstruktor der Klasse Eye den Ultraschallsensorport sowie den Motor als Objket �bergeben
		//
		Runnable threadjob = new Eye(1,m1); 
		Thread eye1 = new Thread(threadjob);
		
		Runnable threadjob2 = new Eye(2,m2);
		Thread eye2 = new Thread(threadjob2);
		
		Runnable threadjob3 = new Eye(3,m3);
		Thread eye3= new Thread(threadjob3);
		
		Runnable threadjob4 = new Eye(4,m4);
		Thread eye4 = new Thread(threadjob4);
		
		// Thread starten
			eye1.start();
			eye2.start();
			eye3.start();
			eye4.start();
	}

}
