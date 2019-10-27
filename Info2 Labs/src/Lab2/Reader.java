package Lab2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Reader {
		
		public Reader() {
			super();
		}
		
		public ArrayList<String> getListFromString(String text) {
			ArrayList<String> line = new ArrayList<>();
			line.add(text);
			return line;
		}
		
		public Stream<String> getStreamFromFile(String filePath){
			try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)){
				return stream;
		    }
		    catch (IOException e){
		        e.printStackTrace();
		    }
		    return null;
		}
	
}
