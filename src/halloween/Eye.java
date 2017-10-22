package halloween;

import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.TachoMotorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Eye implements Runnable {
	
	private Port port;
	private EV3UltrasonicSensor eye;
	private RegulatedMotor motor;
	Key esc = LocalEV3.get().getKey("Escape");

	public void run()
	{
		while(true)
			{
				eyeoff();
				eyeon();
			}
	}
	
	Eye( int portnr, RegulatedMotor motor  ) // Konstruktor f�r die Instanziierung des Objektes
	{
		
		this.port = LocalEV3.get().getPort("S"+portnr);
		this.eye = new EV3UltrasonicSensor(this.port);
		this.motor = motor;
		
	}
	// Augen auf 	
	void eyeon(){
		
		 eye.enable(); // Aktiviere den Ultraschallsensor
		 motoreyemove(); // Rufe eine Motorbewegung auf
		 int x= timeEyeOn(); // Zeit ermitteln, die die Augen auf sind 
		 try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 eyeBlink(); // Wimpernschlag
		 
		 
	}
	
	// Augen zu
	void eyeoff(){
		
		eye.disable();
		int x = timeEyeOff();
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	// Wimpernschlag
	void eyeBlink(){
			eye.disable();
			Delay.msDelay(timeEyeblink());  // Zeit f�r den Wimpernschlag
			eye.enable();
	}
	
	// Die Augen nach links und rechts bewegen
	void motoreyemove(){
		
		motor.setSpeed(speedmotor()); // Geschwindigkeit des Motors setzen. Geschwindigkeit wird zuf�llig gew�hlt
		int x = rotatemotor();	// einen zuf�lligen Winkel bestimmen
		motor.rotate(x);	// Motor um diesen Winkel drehen
		motor.stop();	// Motor stoppen
		Delay.msDelay(timeEyeOff()); 
		
		// Motor wird um den gleichen Wert - in zwei Schritten - wieder zur�ckgedreht
		motor.setSpeed(speedmotor());
		motor.rotate(-x/2);
		motor.stop();
		Delay.msDelay(timeEyeOff());
		
		motor.setSpeed(speedmotor());
		motor.rotate(-x/2);
	}
	
	// Ermittlung von Zufallswerten
	
	// Zeitdauer f�r Augen an
	int timeEyeOn(){
		return (int)(Math.random()*15000);
	}
	// Zeitdauer f�r Augen aus
	int timeEyeOff(){
		return (int)(Math.random()*5000);
	}
	// Zeitdauer f�r Wimpernschlag
	int timeEyeblink(){
		return (int)(Math.random()*50);
	}
	// Geschwindigkeit f�r Motor
	int speedmotor(){
		return (int)(Math.random()*300);
	}
	// Winkel f�r den Motor
	int rotatemotor(){
		return (int)(Math.random()*180);
	}
	
	
}
