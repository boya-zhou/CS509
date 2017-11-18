package java_demo_and_sandbox;
import java.io.StringReader;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

public class request_play {
	public static void main(String args[]) throws JAXBException {
		String xmlInput = "<xml><name X=\"haha\">March</name><age>25</age></xml>";
		Person p = parsePerson(xmlInput);
		System.out.println(p.name);
		System.out.println(p.age);
		System.out.println(p.X);
	}

	public static Person parsePerson(String xml) throws JAXBException {
		Person person = JAXB.unmarshal(new StringReader(xml), Person.class);
		return(person);
	}
	
	private static class Person {
		public String name;
		public int age;
		public String X;
	}
	
}
