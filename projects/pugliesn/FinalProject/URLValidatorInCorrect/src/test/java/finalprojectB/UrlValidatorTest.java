
package finalprojectB;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.*;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


  //public UrlValidatorTest(String testName) {
  //    super(testName);
  //}

   @Test(timeout = 4000)
   public void testManualTest() {
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
     //You can use this function to implement your manual testing
     String[] URLs = {
       "www.google.com",
       "http://www.google.com",
       "www.google.com/testpage",
       "www.google.com/testpage/testpath",
       "www.google.xd",
       "http://www.google.com/testpage/testpath",
     };

     System.out.println("Manual testing " + URLs.length + " URLs: ");

     for(int i = 0; i < URLs.length; i++) {
       //System.out.println("Test " + i + ":");
       System.out.print("Test " + i + ": " + URLs[i] + " - ");

         if(urlVal.isValid(URLs[i])) {
          System.out.println("Valid URL");
         } else {
          System.out.println("Invalid URL");
         }
       }
     }
/*
   @Test(timeout = 4000)
   public void testPartition() {
	 //You can use this function to implement your First Partition testing
   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
   //You can use this function to implement your manual testing

   System.out.println("Input partition tests: ");

     if(urlVal.isValid("http://www.google.com")) {
       System.out.println("Valid URL");
     } else {
       System.out.println("Invalid URL");
     }

     if(urlVal.isValid("file:///C:/Users/Nick/Downloads/FinalProject.pdf")) {
       System.out.println("Valid URL");
     } else {
       System.out.println("Invalid URL");
     }


   }
*/
   @Test(timeout = 4000)
   public void testWhiteBox() {
	   //You can use this function for programming based testing

   }



}
