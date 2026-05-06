# 🚀 Study Burnout Detector (Pro Edition)
> **A Behavioral Analytics & Life Management Tool for Students.**

![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![UI](https://img.shields.io/badge/Framework-Swing-blue.svg)
![OOP](https://img.shields.io/badge/Design-OOP%20Architecture-green.svg)
![Persistence](https://img.shields.io/badge/Storage-Flat%20File-lightgrey.svg)

---

## 🌟 Project Overview
The **Study Burnout Detector** is an interactive desktop application designed to help students track their daily productivity and mental fatigue. Unlike a simple logger, this system analyzes various life factors—including academic study, screen time, physical exercise, and spiritual activities—to provide a holistic "Burnout Score" and personalized behavioral advice.

---

## ✨ Key Features
- 🔐 **Secure Authentication:** User registration and login system with password protection.
- 📊 **Activity Tracking:** Log 5 distinct categories: Study, Screen Time, Hobbies, Walking/Exercise, and Wasted/Travel Time.
- 🕌 **Spiritual Well-being:** Integrated 5-times Daily Prayer (Salat) tracker.
- 🏆 **Dynamic Pointing System:** Real-time calculation of positive and negative points based on behavior.
- 📅 **Interactive History Explorer:** A JList-based calendar interface to view detailed daily "stories" and past performances.
- 📈 **Long-term Analytics:** View average performance stats over time to observe improvement trends.
- 💡 **Smart Advice Engine:** Automated feedback based on the balance of fatigue and recovery.

---

## 🧠 Core OOP Principles Implemented
This project is built from the ground up to demonstrate mastery of **Object-Oriented Programming**:

| OOP Pillar | Implementation Details |
| :--- | :--- |
| **Abstraction** | Abstract classes `Activity` and `Analyzer` serve as templates for specific behaviors. |
| **Inheritance** | `User extends Person`, and specific activities like `StudyActivity` inherit from the base `Activity` class. |
| **Polymorphism** | **Method Overriding:** Different `calculatePoints()` logic for each activity. **Upcasting:** Using `Analyzer engine = new SmartAnalyzer()`. |
| **Encapsulation** | Sensitive data (passwords, hours, dates) are kept `private` and accessed only via secure Getters and Setters. |
| **Interfaces** | The `Suggestable` interface enforces a contract for generating behavioral feedback. |
| **Exception Handling** | Custom exceptions like `AuthException` and `InvalidInputException` prevent application crashes. |

---

## 📂 Project Structure
```text
StudyBurnoutDetector/
├── src/
│   └── burnoutdetector/
│       ├── LoginGUI.java            # Main Entry Point & Authentication
│       ├── BurnoutAppGUI.java       # Main Dashboard & Interactive UI
│       ├── Person.java & User.java  # Class Hierarchy & Inheritance
│       ├── Activity.java            # Abstract Base for all activities
│       ├── StudyActivity.java ...   # Specialized Polymorphic Classes
│       ├── StudySession.java        # Data Modeling & Scoring Logic
│       ├── FileHandler.java         # Static Utility for persistent storage
│       ├── Analyzer.java            # Abstract Decision Layer
│       ├── SmartAnalyzer.java       # Real-time Logic Implementation
│       └── Suggestable.java         # Interface Contract
├── users.txt                        # Persistent User Database
└── burnout_records.txt              # Encoded Activity Database

```
---

## 📈 The Pointing Algorithm
The system calculates the Net Performance Score using the following weights:
- 📖 Academic Study: +10 pts/hr
- 🚶 Walking/Exercise: +12 pts/hr
- 🎨 Hobbies/ECA: +8 pts/hr
- 🕌 Prayers (Salat): +5 pts/prayer
- 😴 Recovery (Sleep): +5 pts/hr
- 📱 Screen Time: -15 pts/hr
- 🚗 Travel/Wasted Time: -5 pts/hr

---

## 🛠️ Installation & Usage
- Ensure JDK 8 or higher is installed.
- Clone this repository or download the source files.
- Open the project in your favorite IDE (VS Code, IntelliJ, or NetBeans).
- Run LoginGUI.java to start the application.
- Register a new user, log in, and start tracking your journey!
---

<b> Developed with ❤️ in Java for Academic Excellence. <b>