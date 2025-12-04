# Bank Account Management System

A comprehensive console-based banking application built with Java that demonstrates Object-Oriented Programming principles, data structures, and algorithms for efficient account and transaction management. This project features a modular architecture with robust exception handling, comprehensive input validation, and a separated UI layer.

## 🚀 Enhanced Features

### Core Functionality
- **Account Management**: Create, view, search, and manage bank accounts
- **Customer Management**: Support for Regular and Premium customers with detailed views
- **Transaction Processing**: Deposit, withdrawal, and inter-account transfer operations
- **Transaction History**: View complete transaction records with summaries
- **Menu Navigation**: Hierarchical menu system with user-friendly interface
- **Account Administration**: Update account status, apply fees/interest, generate statements

### Account Types
- **Savings Account**:
    - Earns 3.5% annual interest
    - Minimum balance requirement: $500
    - Interest calculation functionality
    - Minimum balance enforcement on withdrawals

- **Checking Account**:
    - $1,000 overdraft limit
    - $10 monthly fee (waived for Premium customers)
    - Overdraft protection with limit enforcement
    - Monthly fee application with premium customer waivers

### Customer Types
- **Regular Customer**: Standard banking services
- **Premium Customer**:
    - No monthly fees
    - Priority service
    - Minimum balance requirement: $10,000 for checking accounts

## 🛡️ New: Robust Exception Handling System
- **Custom Exception Hierarchy**: 10+ specific exception types
- **Business Logic Exceptions**:
    - `InsufficientFundsException` - When withdrawal exceeds balance
    - `OverdraftLimitExceededException` - When overdraft limit is exceeded
    - `MinimumBalanceViolationException` - For savings account minimum balance
- **Input Validation**: `ValidationException` for user input errors
- **Graceful Error Recovery**: User-friendly messages with recovery options
- **Exception Chaining**: Proper exception propagation for debugging

## 🎨 New: Modular UI Architecture
- **AccountUI**: Handles all account viewing and searching operations
- **CustomerUI**: Manages customer listing and detailed views
- **AccountManagerUI**: Administrative functions (status updates, fee application, interest calculation)
- **Main.java**: Clean orchestration layer with minimal business logic

## ✅ New: Comprehensive Input Validation
- **Format Validation**: Names, contacts, amounts with proper formatting
- **Business Validation**: Age ranges, minimum deposits, balance requirements
- **Real-time Feedback**: Immediate error correction with helpful messages
- **Validation Rules**: Reusable validation rules via functional interfaces
- **CustomUtils**: Consistent output formatting across the application

## 🔄 New: Account Transfer Functionality
- **Inter-Account Transfers**: Move funds between any two accounts
- **Transaction Recording**: Both sides recorded (TRANSFER_OUT/TRANSFER_IN)
- **Validation**: Same account prevention, sufficient funds check
- **Confirmation**: Detailed transfer confirmation with balance previews

## 🧪 New: Unit Testing Suite
- **JUnit 5 Tests**: Comprehensive coverage of Account class methods
- **Exception Testing**: Verify proper exception throwing for edge cases
- **Mockito Integration**: For testing complex dependencies
- **Test Scenarios**:
    - Deposit validation and balance updates
    - Withdrawal with minimum balance enforcement
    - Overdraft limit testing for checking accounts
    - Transfer functionality between accounts

## 🛠️ Technology Stack

- **Language**: Java 8+
- **Build Tool**: Maven 3.6+
- **Testing**: JUnit 5, Mockito
- **Paradigm**: Object-Oriented Programming (OOP)
- **Design Patterns**: MVC separation, Factory pattern (customer/account creation), Strategy pattern (transaction processing)
- **Data Structures**: Arrays for account and transaction storage
- **Algorithms**: Linear search, sorting for transaction history

## 📋 Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven 3.6 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command line
- Git (for version control)

## ⚙️ Installation & Setup

### Method 1: Using Maven (Recommended)

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/BankAccountManagement.git
   cd BankAccountManagement
   ```

2. **Build the Project**
   ```bash
   # Clean and compile
   mvn clean compile
   
   # Run the application
   mvn exec:java -Dexec.mainClass="Main"
   
   # Run tests
   mvn test
   
   # Create executable JAR
   mvn clean package
   ```

3. **Run from JAR**
   ```bash
   java -jar target/BankAccountManagement-1.0.jar
   ```

### Method 2: Using an IDE

1. **Import as Maven Project**
    - Open your IDE (IntelliJ/Eclipse)
    - Select "Import Project" → Choose the project folder
    - Select "Maven Project" option
    - Let IDE download dependencies automatically

2. **Run Configuration**
    - Main Class: `Main`
    - Working Directory: Project root
    - Use classpath of module: BankAccountManagement

### Method 3: Manual Compilation

1. **Compile All Classes**
   ```bash
   javac -d bin -cp ".:lib/*" src/main/java/**/*.java src/main/java/*.java
   ```

2. **Run the Application**
   ```bash
   java -cp "bin:lib/*" Main
   ```

## 📁 Project Structure

```
BankAccountManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── account/           # Account-related classes
│   │   │   │   ├── Account.java (abstract)
│   │   │   │   ├── SavingsAccount.java
│   │   │   │   ├── CheckingAccount.java
│   │   │   │   └── AccountManager.java
│   │   │   ├── customer/          # Customer-related classes
│   │   │   │   ├── Customer.java (abstract)
│   │   │   │   ├── RegularCustomer.java
│   │   │   │   └── PremiumCustomer.java
│   │   │   ├── transaction/       # Transaction-related classes
│   │   │   │   ├── Transactable.java (interface)
|   |   |   |   |──   TransactionManager.java
│   │   │   │   └── Transaction.java
│   │   │   ├── ui/                # UI separation
│   │   │   │   ├── AccountUI.java
│   │   │   │   ├── CustomerUI.java
│   │   │   │   └── AccountManagerUI.java
│   │   │   ├── utils/             # Utility classes
│   │   │   │   ├── CustomUtils.java
│   │   │   │   └── InputValidator.java
│   │   │   ├── exceptions/        # NEW: Custom exceptions
│   │   │   │   ├── BankException.java
│   │   │   │   ├── ValidationException.java
│   │   │   │   └── InsufficientFundsException.java
│   │   │   └── Main.java          # Entry point
│   │   └── docs/
|   |   |   └── git-workflow.md
│   └── test/
│       └── java/                  # Test classes
│           └── AccountTest.java   # Unit tests
├── README.md
├── pom.xml                        # Maven configuration
├── .gitignore
└── README.md              
```

## 🎯 How to Use

### 1. Main Menu Navigation
```
BANK ACCOUNT MANAGEMENT - MAIN MENU
1. Create Account
2. View Accounts
3. View Customers
4. Process Transaction
5. View Transaction History
6. Generate Account Statements
7. Run Tests
8. Exit
```

### 2. Account Management Sub-Menus

#### **Account Menu (Option 2)**
```
1. View All Accounts
2. View Account Details
3. Search Account
4. Manage Accounts
5. Back to Main Menu
```

#### **Manage Accounts (Option 4)**
```
1. Update Account Status
2. Update Account Information
3. Back to Account Menu
```

### 3. Customer Management (Option 3)
```
1. View All Customers
2. View Customer Details
3. Search Customers
4. Back to Main Menu
```

### 4. Transaction Processing (Option 4)
- **Deposit**: Add funds to any account
- **Withdrawal**: Remove funds with validation
- **Transfer**: Move funds between accounts with confirmation

### 5. Key Features in Action

#### **Account Creation with Validation:**
```
Enter customer name: John Smith ✓
Enter customer age: 35 ✓
Enter customer contact: +1-555-0101 ✓
Enter customer address: 123 Main St ✓

Customer type:
1. Regular Customer
2. Premium Customer
Select: 1 ✓

Account type:
1. Savings Account (Interest: 3.5%, Min Balance: $500)
2. Checking Account (Overdraft: $1,000, Monthly Fee: $10)
Select: 1 ✓

Enter initial deposit: $1000 ✓
✓ Account created successfully! (ACC001)
```

#### **Transaction with Business Rules:**
```
Account: ACC001 (Savings) | Balance: $1000.00
Withdraw: $600
✗ Error: Minimum balance violation! Remaining would be: $400.00
Minimum required: $500.00
```

## 🏗️ OOP Principles Applied

### **Encapsulation**
- Private fields with public getters/setters
- Data hiding with controlled access
- Validation in setters

### **Inheritance**
- Account hierarchy: `Account` ← `SavingsAccount`, `CheckingAccount`
- Customer hierarchy: `Customer` ← `RegularCustomer`, `PremiumCustomer`

### **Polymorphism**
- Method overriding in child classes
- Abstract methods in base classes
- Interface implementation (`Transactable`)

### **Abstraction**
- Abstract classes defining contracts
- Interface for transaction capability
- Separation of interface and implementation

### **Composition**
- AccountManager contains Account array
- TransactionManager contains Transaction array
- UI classes composed with managers

## 🧪 Testing the Application

### **Unit Tests Included:**
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AccountTest

# Generate test report
mvn surefire-report:report
```

### **Test Coverage:**
- **Account.deposit()**: Positive, negative, zero amounts
- **Account.withdraw()**: Sufficient/insufficient funds, minimum balance
- **Account.transfer()**: Valid transfers, same account prevention
- **Exception Handling**: Proper exception throwing and messages

### **Manual Test Scenarios:**
1. **Create Accounts**: Both types with various customer types
2. **Transaction Processing**: Deposits, withdrawals with edge cases
3. **Transfer Operations**: Between different account types
4. **Exception Handling**: Invalid inputs, business rule violations
5. **UI Navigation**: All menu paths and options

## 🐛 Troubleshooting

### **Common Issues & Solutions:**

1. **"Invalid input! Please enter a number."**
    - Ensure you're entering numeric values where required
    - Check for hidden characters or spaces

2. **"Account not found!"**
    - Verify account number format: ACC001, ACC002, etc.
    - Check if account exists in the system

3. **Transaction Failures:**
    - Savings accounts: Maintain $500 minimum balance
    - Checking accounts: Stay within $1,000 overdraft limit
    - Premium customers: No fees for checking accounts

4. **Build/Compilation Errors:**
   ```bash
   # Clean and rebuild
   mvn clean compile
   
   # Check Java version
   java -version
   
   # Verify Maven installation
   mvn -v
   ```

### **Sample Data:**
The application includes 5 pre-loaded accounts:
- 3 Savings accounts with various balances
- 2 Checking accounts with Regular/Premium customers
- Sample transactions for testing history view

## 📈 Extension Possibilities

### **Planned Enhancements:**
1. **File/Database Persistence**: Save accounts and transactions to file/DB
2. **Interest Compounding**: Daily/monthly interest calculation
3. **Bank Employee System**: Authentication and role-based access
4. **Reporting Module**: Financial reports, statements export
5. **Graphical UI**: JavaFX or Swing interface
6. **Web Interface**: REST API with Spring Boot
7. **Multi-currency Support**: Exchange rate integration

### **Advanced Features:**
- Loan management system
- Investment accounts
- Bill payment system
- Mobile banking integration
- Real-time transaction processing

## 🏆 Learning Outcomes

This project demonstrates mastery of:
- **Java OOP Principles**: All four pillars implemented
- **Exception Handling**: Custom exceptions with proper hierarchy
- **Design Patterns**: MVC, Factory, Strategy patterns
- **Code Organization**: Package structure, separation of concerns
- **Testing**: Unit tests with JUnit and Mockito
- **Version Control**: Git workflow with feature branches
- **Documentation**: Comprehensive README and code comments

## 👥 Contributors

Developed as an educational project demonstrating comprehensive Java programming skills, software design principles, and professional development practices.

## 📄 License

This project is for educational purposes as part of a programming curriculum. All code is available for learning and reference.

---

**Happy Banking! 🏦**

