package com.javagame.repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.glassfish.jaxb.core.annotation.XmlLocation;

import com.javagame.entities.Card;
import com.javagame.entities.Category;
import com.javagame.entities.ListWrapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class DataRepository {
	public ListWrapper getAllData() {
		try {
		//	File file = new File("data.xml");
			InputStream inputStream = XmlLocation.class.getClassLoader().getResourceAsStream("data.xml");
		    JAXBContext jaxbContext = JAXBContext.newInstance(ListWrapper.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    return (ListWrapper)jaxbUnmarshaller.unmarshal(inputStream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
		return null;
	}
	public List<Category> getAllCategories(){
		List<Category> lisCategories = getAllData().getCategories();
		return lisCategories;
	}
	public List<Card>  getAllCards(){
		return getAllData().getCards();
	}
//	public void makeXml() {
//		   try {
//			JAXBContext contextObj = JAXBContext.newInstance(ListWrapper.class);
//		    Marshaller marshallerObj = contextObj.createMarshaller();  
//		    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		    ListWrapper listWrapper = new ListWrapper();
//		    List<Category> = Arrays.asList(null);
//		    marshallerObj.marshal(listWrapper, new FileOutputStream("sourceData.xml"));
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//	}
}
