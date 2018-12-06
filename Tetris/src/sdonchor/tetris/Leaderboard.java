package sdonchor.tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class Leaderboard {
	
	public class Score implements Comparable<Score>{
		private String name;
		private int score;
		public Score(String name, int score) {
			this.name=name;
			this.score=score;
		}
		/**
		 * Returns player's name.
		 */
		public String getName() {
			return name;
		}
		/**
		 * Returns player's score.
		 */
		public int getScore() {
			return score;
		}
		/**
		 * compareTo override - compares only numeric score.
		 */
		@Override
		public int compareTo(Score x) {
			return Integer.compare(this.getScore(), x.getScore());
		}
	}
	private List<Score> leaderboard = new ArrayList<Score>();
	private String filename="leaderboard.txt";
	/**
	 * Leaderboard constructor, tries to load values from leaderboard file, if it doesn't exists creates a new one.
	 */
	public Leaderboard() {
		if(!LoadFromFile())
		{
			for(int i=0;i<5;i++)
				leaderboard.add(new Score("---",0));
			saveToFile();
		}
		
	}
	public boolean isDialogOpen=false;
	/**
	 * Adds new score, sorts the table and saves current leaderboard to file.
	 */
	public void addScore(String pname, int pscore){
		leaderboard.add(new Score(pname,pscore));
		sortLeaderboard();
		saveToFile();
	}
	/**
	 * Shows name input dialog and calls addScore()
	 */
	public void displayNameEntry(Window w,int points){
		String name="";
		while(name==""||name.length()>5) {
		name = JOptionPane.showInputDialog(w,
                "HIGHSCORE! What is your name? (max. 5 chars)", null);
		if(name==null||name.isEmpty())break;
		}

		if(name==null||name==""||name.isEmpty())return;
		else
		{
			addScore(name,points);
		}
		
	}
	/**
	 * Returns the Score with given index.
	 */
	public Score getIndex(int index){
		return leaderboard.get(index);
	}
	/**
	 * If given score is good enough to be added to leaderboards - returns true, else returns false.
	 */
	public boolean goodEnough(int nscore) {
		for(int i=0;i<5;i++)
		{
			if(nscore>getIndex(i).score) return true;
		}
		return false;
	}
	/**
	 * Sorts the table by score numeric value, descending.
	 */
	public void sortLeaderboard(){
		Collections.sort(leaderboard,Collections.reverseOrder());
	}
	/**
	 * Clears the leaderboard and removes the leaderboard file.
	 */
	public void restartLeaderboard() {
		File file=new File(filename);
		file.delete();
		leaderboard.clear();
		for(int i=0;i<5;i++)
			leaderboard.add(new Score("---",0));
	}
	/**
	 * Loads leaderboard from file.
	 */
	private boolean LoadFromFile(){
		File file = new File(filename);
		if(file.isFile())
		{
			FileReader fileReader;
			BufferedReader bufferedReader;
			try {
				fileReader = new FileReader(filename);
				bufferedReader = new BufferedReader(fileReader);
				String textLine;
				for(int i=0;i<5;i++)
				{
					textLine=bufferedReader.readLine();
					if(textLine!=null)
					{
						String[] result=textLine.split(">",2);
						leaderboard.add(new Score(result[0], Integer.parseInt(result[1])));

					}
					
				}
				bufferedReader.close();
				fileReader.close();
			}catch (IOException e)
			{
				System.out.println("Couldn't read leaderboard file.");
				return false;
			}
			return true;
		}
		else return false;
	}
	/**
	 * Saves current leaderboard state to text file.
	 */
	private void saveToFile()
	{
			FileWriter fileWriter;
			BufferedWriter bufferedWriter;
			try {
				fileWriter = new FileWriter(filename,false);
				bufferedWriter = new BufferedWriter(fileWriter);
				String textLine="";
				for(int i=0;i<5;i++)
				{
					textLine=leaderboard.get(i).getName()+">"+Integer.toString(leaderboard.get(i).getScore());
					bufferedWriter.write(textLine);
					if(i!=4)
					bufferedWriter.newLine();
				}
				bufferedWriter.close();
				fileWriter.close();
				
			} catch (IOException e) {
				System.out.println("Couldn't create leaderboard file.");
			}
	}
	

}
