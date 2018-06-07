
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
       "qqq.google.xd",
       "http://www/google/com",
       "www.google.com.",
       ".www.google.com",
       "http://www.google.com/testpage/testpath",
       "ftp://www.google.com"
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

   @Test(timeout = 4000)
   public void testPartition() {
	 //You can use this function to implement your First Partition testing
   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
   /*partitions:
   	 protocol - address
   */
   
   String[] no_protocol = {
	       "www.google.com",
	       "www.google.com/testpage",
	       "8.8.8.8",
	       "8.8.8.8.8",
	       "qqq.google.xd",
	 };
   
   String[] http_protocol = {
	       "http://www.google.com",
	       "http://www.google.com/testpage",
	       "http://8.8.8.8",
	       "http://8.8.8.8.8",
	       "http://qqq.google.xd",
	 };
   
   String[] ftp_protocol = {
	       "ftp://www.google.com",
	       "ftp://www.google.com/testpage",
	       "ftp://www.google.com/testpage",
	       "ftp://8.8.8.8",
	       "ftp://8.8.8.8.8",
	       "ftp://qqq.google.xd",
	 };
   
   String[] file_protocol = {
	       "file://www.google.com",
	       "file://www.google.com/testpage",
	       "file://8.8.8.8",
	       "file://8.8.8.8.8",
	       "file:///malformed\\path",
	       "file:///nfs/stak/users/pugliesn/testfile",
	 };

     System.out.println("\nPartition testing (with no protocol) " + no_protocol.length + " URLs: ");

     for(int i = 0; i < no_protocol.length; i++) {
       //System.out.println("Test " + i + ":");
       System.out.print("Test " + i + ": " + no_protocol[i] + " - ");

         if(urlVal.isValid(no_protocol[i])) {
          System.out.println("Valid URL");
         } else {
          System.out.println("Invalid URL");
         }
       }
     
     System.out.println("\nPartition testing (with http protocol) " + http_protocol.length + " URLs: ");

     for(int i = 0; i < http_protocol.length; i++) {
       //System.out.println("Test " + i + ":");
       System.out.print("Test " + i + ": " + http_protocol[i] + " - ");

         if(urlVal.isValid(http_protocol[i])) {
          System.out.println("Valid URL");
         } else {
          System.out.println("Invalid URL");
         }
       }
     
     System.out.println("\nPartition testing (with ftp protocol) " + ftp_protocol.length + " URLs: ");

     for(int i = 0; i < ftp_protocol.length; i++) {
       //System.out.println("Test " + i + ":");
       System.out.print("Test " + i + ": " + ftp_protocol[i] + " - ");

         if(urlVal.isValid(ftp_protocol[i])) {
          System.out.println("Valid URL");
         } else {
          System.out.println("Invalid URL");
         }
       }
     
     System.out.println("\nPartition testing (with file protocol) " + file_protocol.length + " URLs: ");

     for(int i = 0; i < file_protocol.length; i++) {
       //System.out.println("Test " + i + ":");
       System.out.print("Test " + i + ": " + file_protocol[i] + " - ");

         if(urlVal.isValid(file_protocol[i])) {
          System.out.println("Valid URL");
         } else {
          System.out.println("Invalid URL");
         }
       }
     
     System.out.println();
   }

   @Test(timeout = 4000)
   public void testWhiteBox() {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   //You can use this function for programming based testing
	   
	   ResultPair[] test_protocols = {
			   new ResultPair("http://", true),
               new ResultPair("ftp://", true),
               new ResultPair("fto://", false),
               new ResultPair("htto://", false),
               new ResultPair("", true)
       };
	   
	   ResultPair[] test_authority = {
			   new ResultPair("www.google.com", true),
               new ResultPair("255.255.255.255", true),
               new ResultPair("800.800.800.800", false),
               new ResultPair("www.google.car", false),
               new ResultPair("", false)
       };
	   
	   ResultPair[] test_port = {
			   new ResultPair(":22", true),
               new ResultPair(":65536", false),
               new ResultPair(":80z", false),
               new ResultPair(":-1", false),
               new ResultPair("", true)
       };
	   
	   ResultPair[] test_path = {
			   new ResultPair("", true),
               new ResultPair("/testpath", true),
               new ResultPair("/test/path", true),
               new ResultPair("/../", false),
               new ResultPair("/..", false),
       };
	   
	   Object[] schemes = {test_protocols, test_authority, test_port, test_path};
	   
	   int[] token_array = {0, 0, 0, 0};
	   
	   String current_url = "";
	   boolean current_validity;
	   
	   //loops through each of the 4 sections of the schemes and creates each permutation
	   int token_position = 0;
	   
	   System.out.println("White box testing: ");
	   
	   while(token_position < schemes.length) {
		   while(token_array[token_position] < 5) {
			   current_url = "";
			   current_url = current_url + test_protocols[token_array[0]].item + test_authority[token_array[1]].item + 
					   test_port[token_array[2]].item + test_path[token_array[3]].item;
			   current_validity = (test_protocols[token_array[0]].valid && test_authority[token_array[1]].valid && 
					   test_port[token_array[2]].valid && test_path[token_array[3]].valid);
			   
			   System.out.println(current_url + " - Expected: " + Boolean.toString(current_validity));
			   System.out.println("Actual: " + Boolean.toString(urlVal.isValid(current_url)));
			   
			   token_array[token_position]++;
			   
		   }
		   token_array[token_position] = 0;
		   token_position++;
	   }

   }



}
