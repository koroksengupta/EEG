package com.test.helloeeg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



import android.os.Environment;
import android.text.format.Time;
import android.util.Log;

public class WriteToCSVFile {
	private static final String TAG = "WriteToCsvFile";

	private static final String CSV_FILE = "BCIInfo.csv";

	private static final String CSV_SEPARATOR = ",";

	private static final String END_OF_LINE = "\n";

	private String mPath = null;
	
	public void WriteToCsvFile() {
		this.mPath = getBCIFolder().toString() + "/" + CSV_FILE;
	} // End Of Constructor
	
	private File getBCIFolder() {
		File folder = new File(Environment.getExternalStorageDirectory()
				+ "/BCIInfo");
		Log.d(TAG, "Folder Path: " + folder.toString());
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}//End of getting the BCI FOlder
	
	
	public void generateCsvFile(String key, String info) {
		final String keyInfo = key;
		final String val = info;
		new Thread() {
			public void run() {
				String att1 = "0.0";
				String med1 = "0.0";
				

				Time time = new Time();
				

				time.setToNow();
				
				try {
					///////////////////////////////////////////
		    		File file =new File(mPath);
		    		//if file doesnt exists, then create it
		    		if(!file.exists()){
		    			file.createNewFile();
		    		}// End of Try block
		    		
		    	FileWriter csvWriter = new FileWriter(mPath,true);
		    	
		    	if (HelloEEGActivity.ATT.equals(keyInfo)) {
					att1 = val;
				}
				if (HelloEEGActivity.MED.equals(keyInfo)) {
					med1 = val;
				}

				
				csvWriter.append(Integer.toString(time.hour) + ":"
						+ Integer.toString(time.minute));
				csvWriter.append(CSV_SEPARATOR);
				csvWriter.append(att1);
				csvWriter.append(CSV_SEPARATOR);
				csvWriter.append(med1);
				csvWriter.append(END_OF_LINE);
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}.start();
}
	
}


