package sdonchor.tetris;
import java.io.*;
import javax.sound.sampled.*;
public class MusicPlayer{
	private Clip clip;
	/*
	 * Plays music theme in a loop.
	 */
	public MusicPlayer(File file) 
	{
	    try
	    {
	        clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
	        clip.addLineListener(new LineListener()
	        {
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP)
	                {
	                    clip.close();
	                    try {
							clip.open(AudioSystem.getAudioInputStream(file));
						} catch (LineUnavailableException e) {
							System.out.println("Line is not available.");
							e.printStackTrace();
						} catch (IOException e) {
							System.out.println("Couldn't load audio file.");
							e.printStackTrace();
						} catch (UnsupportedAudioFileException e) {
							System.out.println("Unsupported audio file.");
							e.printStackTrace();
						}
	                    clip.start();
	                }
	            }
	        });

	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
}