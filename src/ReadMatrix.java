//Daniel Cohen 203850029 Ben Efrat 305773806

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadMatrix {

	int[][] myArray ;
	int Rows,Cols;
	public ReadMatrix(int rows,int cols)
	{
		Rows = rows;
		Cols = cols;
		myArray = new int[rows][cols];
	}

	public int[][] readFromFile() throws NumberFormatException, IOException
	{



		int row = 0;
		String line;
		BufferedReader br = Files.newBufferedReader(Paths.get("array.txt"));

		//read file		
		while ((line = br.readLine()) != null){
			//line = line.replace(",", "");

			String [] str = line.split(",");
			if(!(str[0].equals(""))){
				for (int col = 0; col <Rows ; col++){
					if(str[col].contains("-"))
					{
						str[col]=str[col].replace("-", "");
						int n = Integer.parseInt(str[col] );
						myArray[row][col] = -n;

					}
					else {
						int n = Integer.parseInt(str[col]);

						myArray[row][col] = n;
					}
				}
				row++;
			}}


		return myArray;

	}
}
