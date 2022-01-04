<h1 align="center"><b>Banking Application</b></h1>

## Table of Contents
+ Project Abstract
+ How to Run
+ Acknowledgments 

## Project Abstract
This project is a Java desktop application designed to help users organize their finances more efficiently. There is both a GUI and a text-based UI. The banking application provides three main features: Accounts, Budget and Invest.
+ **Accounts** 
  + Allows users to view the current balance of their chequing and savings accounts. Users will be able to add or delete their accounts from this page.
+ **Budget**
  + Users can track how they spend their money. They will be able to choose categories such as rent, utilities, food, etc. The user can allocate a portion of their income to each category.
 + **Invest**
   + Allows users to search different stocks by symbol, and they will be given a brief overview of the stock. Users can purchase stocks and add them to their portfolios. Furthermore, users can manage their portfolios through the app.

## How to Run
1. Clone the project
```bash
git clone https://github.com/salimk1/banking-application.git
```
2. Import as a Maven Project in your IDE
    - For Eclipse:
       - `File > Import > Maven > Existing Maven Projects > banking-application`
    - For IntelliJ IDEA:
       - `New > Project from Existing Sources > Select the pom.xml file`	 
3. Run `BankApplication` in `banking-application/src/main/java/ca/ucalgary/gui` to start GUI
4. Run `CLI` in `banking-application/src/main/java/ca/ucalgary/tui` to start text-based UI

## Acknowledgments 
We collaborated on this project with a team of four fellow university students. Portions of this project were adapted from external material provided to us by our professor at the University of Calgary.
