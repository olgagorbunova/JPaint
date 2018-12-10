package se.miun.olgo1700.dt062g.jpaint;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
* Class includes methods that serialize Drawing objects to XML, as well as extract Drawing objects from an XML file.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-12-10
*/
public class FileHandler {
	
	/**
	 * Creates an XML file that contains information about a Drawing object, and saves file
	 * under a given name. Creates file name by adding drawing info to the name as follows:
	 * "drawing name" by "author".xml. 
	 * @param drawing Drawing object
	 */
	public static void saveToXML(Drawing drawing, String fileName) {
		
		//prepare file name
		String fileNameNew;
		if(fileName.length() > 4) {
			String nameEnd = fileName.substring(fileName.length() - 4);
			
			if(nameEnd.equals(".xml")) {
				fileNameNew = fileName;
			}
			else
				fileNameNew = fileName + ".xml";	
		}
		else {
			fileNameNew = fileName + ".xml";	
		}
		
		//generate XML
		try {
			JAXBContext jc = JAXBContext.newInstance(Drawing.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(drawing, new File(fileNameNew));
			
		} catch (JAXBException e) {
			System.err.println("Error while creating XML");
		}
	}
	
	/**
	 * Calls reate an XML file that contains information about a Drawing object, and saves file
	 * under a given name. Controls if a file name ends with ".xml". If not, adds ".xml" to the file name.
	 * @param drawing Drawing object
	 */
	public static void saveToXML(Drawing drawing) {
		String fileName = drawing.getName() + " by " + drawing.getAuthor();
		saveToXML(drawing, fileName);
	}
	
	public static Drawing loadFromXML(String fileName) {
		Drawing drawing = new Drawing();
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Drawing.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			drawing = (Drawing) unmarshaller.unmarshal(new File(fileName));
		} catch (JAXBException e) {
			System.err.println("Error while reading object from XML");
		}
		
		return drawing;
	}

}
