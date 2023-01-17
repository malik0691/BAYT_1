**To run this project, you just need to run the 'CucumberRunner' file in it after compiling**


**Part 4:**

There are still some scenarios that can be covered. Like
1. Registration using social media and business accounts.
2. Verification of job filter to check if it populates result accordingly.
3. Sharing the specific job using different social media
4. Verification of Add Preferred job section and its expected outcome after login.

**Part 5:**

Issues that figured out while executing the test cases for that particular mentioned page.
1. ‘Job title’ and ‘Job Skills’ still accepts the numeric value and special characters if we pass 10 digit numbers and add  the space between the phone numbers e.g (92) 301 13121
2. Experience field accepts the alphabets unlike ‘Age’ field
3. ‘Job Desc’ label is not clearly defined.
4. ‘Job title’ and ‘Job Skills’ still accepts the email if .com is not added like softwaretest@gmail
