package graphics;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundMusic {
	
	static Clip clip;
	static String status = "play";
	static AudioInputStream audioInputStream;
	
	public static void music() {
		if(status.contentEquals("play")) {
			stopSong();
			status = "stop";
		} else {
			playSong();
			status = "play";
		}
	}

	public static void playSong() {
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File("MiiBackgroundSong.wav"));
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}catch(IOException e) {
				//logger.log();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public static void stopSong() {
		clip.stop();
		clip.close();
		status = "stop";
		
	}
	
}
