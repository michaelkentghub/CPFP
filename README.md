# CPFP

Michael Kent

Completed Full ACC Java Developer Course  July 2015 – February 2016

This is the Capstone project for that course.

Resume is on linkedin:
https://www.linkedin.com/in/michaelrkent5

512-923-9356       mrkent57@gmail.com
Preparing to take Java SE7 certification exam 1Z0-803.

This “CPFP” project is an appointment scheduling app for a business.  In this case, it is for a fictitious lawn service, but it could be used for any business.

The code consists of servlets, jsps, htmls, and plain old Java files.

Development was done on Eclipse.

I have tested it on IE, Chrome, and Firefox, and it runs on all three.

The code opens a SQL database and  creates a table that keeps info on clients.

Session variables are used as a state machine to communicate between sections of the code.
Detailed functionality:

-  Create.html calls a servlet that deletes any current “customer” db and any current table named “clients”,   then creates them and seeds the table with a fictional client.   This allows the business owner to start fresh.
                                    
-  SQL database is used for this, and all user inputs are   handled with prepared statements.

- The business owner then opens “LoginOrRegister” which lets them “register” customers, meaning enter new client info into the table “clients”.  The info for each client is stored in a row and includes username (email), password, name, address, and up to three service appointment dates.

- Each customer can then log in to their account and change any info they want except their username (email).  This includes cancelling appointments and making new ones.  There is also a large text box for the customer to provide feedback.  The business owner can open “DeleteARow.html” to delete a customer (table row) from the database.
During registration, the app checks the email entered to make sure it is a valid email address.  If not, it asks the owner setting up the account to enter a valid address.
During registration, and when a customer makes a change to their password, the jsp has two boxes for the user to enter the password twice, and if they don’t match, it asks the user to enter the same password in both boxes.

The text box to enter the dates uses the default for each browser.

A few lines of bootstrap CSS are used to format the home page “LoginOrRegister.html”.


Possible future enhancements:

This app is a work in progress and the following features could be added.

-  As it stands, multiple people on different terminals can log in to the same account and make changes at the same time.   The app could easily be changed to allow only one user at a time  to be logged in to an account by adding a variable to each row in the table.   The logout servlet would reset the variable, and the login servlet would check to make sure it is reset before logging in, then would set it.
   
-  Code could be added to email all changes made, back to the customer that made them, and to email all changes made, and any feedback entered, to the business owner.

- The business owner can "freeze" any account by temporarily changing the password, locking the user out. Code could be added to display a message to the user explaining the problem when they try to log in.

-  A radio button could be added for each date scheduled, allowing the user to  select between different services for each of the dates, such as mow lawn only, mow + trim shrubs, compost, etc.   This would only require the addition of three variables to each db table row and three more session variables.


