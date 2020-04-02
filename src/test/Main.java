package test;



import java.awt.Dialog;
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
	
	public static long calculateFileSize(File file) throws IOException {
		  int fileSizeBlock = 0;
		  if(file.isDirectory()) {
			  
			  File[] children = file.listFiles();
			     for(File child : children) {
			       fileSizeBlock += calculateFileSize(child);
			     }
		    
		  }else {
			  
			  float s = (float)file.length()/4096;
			  
			  if(s%10!=0) {
				  fileSizeBlock =+ ((int)s+1)*8;
			  }else {
				  fileSizeBlock =+ ((int)s)*8;
			  }
		  }
		
		if(!file.isHidden()&&!file.isFile()) {
		  System.out.println(fileSizeBlock+"\t"+ file.getPath());
		}
		 return fileSizeBlock;
		}

}
