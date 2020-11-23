package com.mukscode.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mukscode.hibernate.entity.Course;
import com.mukscode.hibernate.entity.Instructor;
import com.mukscode.hibernate.entity.InstructorDetail;
import com.mukscode.hibernate.entity.Review;


public class GetCourseAndReviews {

public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try 
		{

			//start transaction
			session.beginTransaction();
			
			//create a course
			Course theCourse = new Course("How to Kill people without getting caught");
			
			//add some reviews
			theCourse.addReview(new Review("Greate Course...helped me a lot in my criminal activities"));
			theCourse.addReview(new Review("Well well well, what can i say it's a goto course for any beginners who wants to kill for fun"));
			theCourse.addReview(new Review("Didn't like the course at all.. old methods are used here."));
			
			//save the course and reviews
			System.out.println("Saving the course and reviews");
			System.out.println(theCourse);
			System.out.println(theCourse.getReviews());
			session.save(theCourse);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		}
		finally 
		{
			factory.close();
		}
		
	}

}
