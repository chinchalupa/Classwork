package problem.question1;

import java.util.Arrays;

public class CampusApp {
	public static void main(String[] args) {
		// Construct courses
		final ICourse csse120 = new Course("CSSE120", "Introduction to Software Development");
		final ICourse csse220 = new Course("CSSE220", "Object-Oriented Software Development");
		final ICourse csse371 = new Course("CSSE371", "Software Requirements Engineering");
		final ICourse csse374 = new Course("CSSE374", "Software Design");
		
		// Construct students
		final IStudent jamesBond = new Student(007, 
				"James Bond",
				2,
				Arrays.asList(csse120, csse220),
				Arrays.asList(csse371, csse374));

		final IStudent ethanHunt = new Student(001,
				"Ethan Hunt",
				3,
				Arrays.asList(csse120, csse220, csse371),
				Arrays.asList(csse374));
		
		// Construct faculty members
		final IFaculty jp = new Faculty(123,
				"JP Mellor",
				null,
				"Department Head of the CSSE Department"
				);
		
		final IFaculty chandanRupakheti = new Faculty(456, 
				"Chandan Rupakheti",
				jp,
				"Software Design");

		final IFaculty markHays = new Faculty(789, 
				"Mark Hays",
				jp,
				"Software Design");
		
		// Construct staff
		final IStaff darrylMouck = new Staff(101,
				"Darryl Mouck",
				jp,
				"System Adminstration");
	
		// Construct department
		final IDepartment csse = new Department(
				"CSSE",
				Arrays.asList(jamesBond, ethanHunt, jp, chandanRupakheti, markHays, darrylMouck));
		
		// Construct school
		final ISchool roseHulman = new School("Rose-Hulman Institute of Technology",
				Arrays.asList(csse));
		
		// Print the model
		System.out.println(roseHulman);
	}
}
