# Git Workflow Guide - Bank Account Management System (Updated)

## 📋 Updated Project Structure
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


## 1. **Initial Setup** (First Time Only)
```bash
# Clone the repository
git clone <your-repository-url>
cd BankAccountManagement

# Set up your identity
git config user.name "Your Name"
git config user.email "your.email@example.com"

# Create .gitignore file with additional entries
echo ".idea/
target/
*.iml
*.class
.DS_Store
*.log
*.jar
out/
bin/
*.bak
*.tmp" > .gitignore
```

## 2. **Feature-Based Development Workflow**

### **Step 1: Start Fresh for New Feature**
```bash
# Pull latest changes from main branch
git checkout main
git pull origin main

# Create a new feature branch
git checkout -b feature/your-feature-name
```

### **Step 2: Make Structured Changes**
```bash
# Example: Adding new exception package
mkdir -p src/exceptions
# Add all exception files

# Example: Adding utility package
mkdir -p src/utils
# Add CustomUtils and InputValidator

# Check status frequently
git status

# Stage changes by logical groups
git add src/exceptions/
git commit -m "feat: add custom exception hierarchy

- Create BankException as base exception
- Add AccountException, TransactionException, ValidationException
- Add specific exceptions: InsufficientFundsException, OverdraftLimitExceededException
- Implement exception chaining for better debugging"

git add src/utils/
git commit -m "feat: add utility classes for better code organization

- Create CustomUtils for consistent output formatting
- Implement InputValidator with comprehensive validation rules
- Add exception-based validation methods
- Maintain backward compatibility with existing methods"
```

### **Step 3: Commit with Conventional Format**
```bash
# Use conventional commit format for better tracking
git commit -m "feat: implement transfer functionality

- Add transfer method to Account class
- Implement transfer in CheckingAccount and SavingsAccount
- Add transaction recording for both sides of transfer
- Include validation for same account transfers
- Update Main.java with transfer processing UI"

git commit -m "refactor: separate UI logic from business logic

- Create AccountUI class for account viewing/searching
- Create CustomerUI class for customer management
- Create AccountManagerUI for account administration
- Update Main.java to use new UI classes
- Improve code maintainability and separation of concerns"

git commit -m "test: add comprehensive unit tests for Account

- Test deposit functionality with edge cases
- Test withdrawal with minimum balance enforcement
- Test overdraft limits in CheckingAccount
- Add exception testing for invalid operations
- Ensure 100% method coverage for Account class"
```

### **Step 4: Push and Create Pull Request**
```bash
# Push your feature branch
git push -u origin feature/your-feature-name

# Create Pull Request on GitHub with detailed description:
"""
## 🚀 Feature: Exception Handling Implementation

### Changes Made:
1. **New Package Structure:**
   - Created `exceptions` package with 10+ custom exceptions
   - Hierarchical exception structure (BankException → AccountException → etc.)
   - Specific business exceptions (InsufficientFundsException, etc.)

2. **Updated Business Logic:**
   - Modified Account classes to throw exceptions instead of returning boolean
   - Updated withdraw() methods in SavingsAccount and CheckingAccount
   - Enhanced error messages with specific details

3. **Input Validation:**
   - Created InputValidator with format and business validation
   - Added ValidationException for input errors
   - Maintained backward compatibility

4. **UI Layer Updates:**
   - Added try-catch blocks in Main.java
   - User-friendly error messages using CustomUtils
   - Proper exception handling in transaction processing

### Testing:
- [x] Unit tests for exception scenarios
- [x] Manual testing of error cases
- [x] Input validation testing
- [ ] Integration testing (in progress)

### Breaking Changes:
- Account.withdraw() now throws exceptions instead of returning false
- InputValidator.getValidInput() now throws ValidationException
- Requires try-catch blocks in UI layer

### Screenshots:
[Add screenshots of error handling in action]
"""
```

# 3. **Updated Branch Naming Convention**

```
feature/                 - New features
├── feature/exception-handling
├── feature/ui-separation
├── feature/input-validation
├── feature/transfer-functionality
└── feature/account-management-ui

refactor/               - Code restructuring
├── refactor/ui-separation
└── refactor/exception-handling

test/                   - Testing related
├── test/unit-tests-account
└── test/integration-tests

bugfix/                 - Bug fixes
hotfix/                 - Critical fixes
docs/                   - Documentation
release/                - Release preparation
```

### 4. **Milestone-Based Development**

```bash
# Work on milestones sequentially
git checkout -b milestone/1-core-classes
# Implement: Account, Customer hierarchies

git checkout -b milestone/2-ui-implementation  
# Implement: Main menu, AccountUI, CustomerUI

git checkout -b milestone/3-transaction-system
# Implement: Transaction, transfer, validation

git checkout -b milestone/4-exception-handling
# Implement: Custom exceptions, error handling

git checkout -b milestone/5-testing
# Implement: Unit tests, integration tests

git checkout -b milestone/6-documentation
# Implement: README, Javadoc, user guides
```


### Key Dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.8.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```


## 📊 Project Status

| Component | Status | Branch | Notes |
|-----------|--------|--------|-------|
| Core Classes | ✅ Complete | `feature/core-classes` | Account, Customer hierarchies |
| Basic UI | ✅ Complete | `feature/basic-ui` | Main menu, account creation |
| Transaction System | ✅ Complete | `feature/transactions` | Deposit, withdrawal, transfer |
| Exception Handling | ✅ Complete | `feature/exception-handling` | Custom exceptions, validation |
| UI Separation | ✅ Complete | `feature/ui-separation` | AccountUI, CustomerUI, AccountManagerUI |
| Unit Testing | 🚧 In Progress | `feature/unit-tests` | JUnit tests for Account class |
| Input Validation | ✅ Complete | `feature/input-validation` | InputValidator, CustomUtils |
| Documentation | 📋 Planned | `feature/documentation` | Updated README, Javadoc |

# 🚀 Next Steps (Branch Planning)

```bash
# Current work
git checkout -b feature/testing

# Upcoming features
git checkout -b feature/file-persistence
git checkout -b feature/interest-calculation
git checkout -b feature/report-generation
git checkout -b feature/user-authentication
```

