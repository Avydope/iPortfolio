# *My Investment Portfolio*

## *Project Description*  

 **Proposals** :
- What will the application do?
\
My application's main purpose is to *record and track all your 
investment decisions.* 
 
- Who will use it?
\
My application is for anyone and everyone, who makes conscious 
**Investment decisions** and would like to keep a track of it.

- Why is this project of interest to you?
\
*Stock Markets, trading, finance* and *economics* have 
always been an interest of mine.


## User Stories 

- As a user, I want to be able to add and store multiple Investments to
    my InvestmentPortfolio 
    
- As a user, I want to be able to remove any added Investment
   
- As a user, I want to be able to track the no. of Investments
  
- As a user, I want to be able to view all my Investments in 
   the Investment Portfolio
   
- As a user, I want to be able to save my added Investments to a file

- As a user, I want to be able to load a previously saved file and resume where
  I left off
  
## Phase 4 Task 2 

- Test and design a class in your model package that is robust.  You must have at least one method that throws a 
checked exception.  You must have one test for the case where the exception is expected and another where the 
exception is not expected.

My removeInvestment method in InvestmentPortfolio class throws a wrongInvestment 
exception for an invalid investment position, which is handled by my initializeRemoveButton
method in InvestmentPortfolioGUI . <br>
My initializeRemoveButton method which is the method that handles the exception, 
shows an error message on the screen in case exception is caught. <br>
( My test cases are in InvestmentPortfolioTest ) 

## Phase 4 Task 3 

My application has a minimalistic structure with only the basic 
required classes. 
My InvestmentPortfolioGUI or InvestmentPortfolioApp classes (depending on which task)
extends InvestmentPortfolio, JsonReader and JsonWriter. <br>
My InvestmentPortfolio further extends my Investments class.

After going through my application, **I believe my classes do not need refactoring.** <br>
All my classes have a single responsibility and high cohesion.

My InvestmentPortfolio class represents an array list of Investments
 and has methods related to the array list of Investments. <br>
My Investments class represents a Investment and contains all methods 
related to a particular Investment.

I think this systematic design code where I believe refactoring is not 
required, is one of the perks of keeping my application simple and minimalistic
from the very start, yet keeping it fully functional.
