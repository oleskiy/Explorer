package test;

import java.io.File;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {
		
		showDialog();
		
	}
	
	public static void showDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		int result = fileChooser.showOpenDialog(dialog);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    try {
		     System.out.println("-du "+ selectedFile.getPath());
		    	calculateFileSize(selectedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    System.out.println("\n");
		    showDialog();
		}	
	}
	
	
	/**
	 * 
	 * 1 block  - 4096 bytes
	 * 1 block  - 8 sectors
	 * 1 sector - 512 bytes
	 * **/
	public static long calculateFileSize(File file) throws IOException {
		  int fileSizeBlock = 0;
		  if(file.isDirectory()) {
			  
			  File[] children = file.listFiles();
			     for(File child : children) {
			       fileSizeBlock += calculateFileSize(child);
			     }
		    
		  }else {
			  
			  float fileSizeKilobyte = (float)file.length()/4096;
			  
			  if(fileSizeKilobyte%10!=0) {
				  fileSizeBlock =+ ((int)fileSizeKilobyte+1)*8;
			  }else {
				  fileSizeBlock =+ ((int)fileSizeKilobyte)*8;
			  }
		  }
		  
		  
		/***
		 * for du -a to see all files and directories
		 * comment if()
		 * */
		if(!file.isHidden()&&!file.isFile()) {
		  System.out.println(fileSizeBlock+"\t"+ file.getPath());
		}
		 return fileSizeBlock;
		}

}
