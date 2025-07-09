PocketTracker
An Android expense tracking app designed specifically for college students to build financial literacy and stay within budget. PocketTracker provides a simple, offline alternative to complex budgeting tools with a privacy-first approach.

Demo
![Screen_recording_20250708_194151-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/d23322ca-8cc6-4e3b-826a-18a954ca8613)

Problem & Solution
The Problem: Over 40% of college students don't know exactly how much they're spending each month, leading to budget overruns and financial stress.

The Solution: PocketTracker offers a fast, user-friendly expense tracking solution that works completely offline. No bank account linking required, no subscription fees, and all data stays private on your device. Just simply open the app and write down your spending.

Features
Core Features
Quick Expense Entry - Add expenses in under 10 seconds
Smart Categories - Predefined categories (Food, Transport, Entertainment, Shopping, Other)
Expense Management - Full CRUD functionality (Create, Read, Update, Delete)
Visual Analytics - Simple charts showing spending breakdown by category
Offline Storage - All data saved locally using Room database
Privacy - No bank account linking or personal data collection

Key Features
Lightning-fast expense logging
Clear spending insights and analytics
Complete privacy and offline functionality
Modern, student-friendly interface
Reliable local data storage

Technical Implementation
Architecture: Single-Activity with Fragments
UI: RecyclerView with custom adapters
Data: Room Database for local storage
State Management: ViewModels with LiveData
Navigation: Navigation Component
Design: Bottom Navigation with Material Design

Target Users
College Students (18-24): Limited income, high risk of overspending
Young Professionals (22-30): Recent graduates building financial habits

User Personas
Jason - The Budget-Conscious Student
20-year-old Marketing student at Virginia Tech
$800/month income, wants to stay within $600 spending budget
Needs quick expense logging without complexity

Marco - The New Graduate
23-year-old recent graduate starting first job
$45,000/year entry-level position
Overwhelmed by complex financial apps, values privacy

Getting Started
Prerequisites
Android Studio Pixel 9 
Android SDK API 21+ (Android 5.0)
Kotlin support

Installation
Clone the repository: git clone https://github.com/JosephVn272/PocketTracker.git
Open the project in Android Studio
Sync Gradle files
Run the app on an emulator or physical device

Screenshots
Add Expense: ![Screenshot_20250708_193436](https://github.com/user-attachments/assets/c34b60b6-6bd2-41ef-808b-951165068abf)
Expense List:![Screenshot_20250708_193802](https://github.com/user-attachments/assets/7262a3fd-8b1a-41e7-b9f6-c5afaa76b0ab)
Edit Expense:![Screenshot_20250708_193822](https://github.com/user-attachments/assets/f820c49c-2cc2-472b-ac1b-6ca1e780bcdf)
Analytic:![Screenshot_20250708_193810](https://github.com/user-attachments/assets/1f2f3f8e-bfe0-4e90-8dd4-bc359b9fce9b)

Current Status
Implemented Features
Expense entry and management
Category-based organization
Local data storage with Room
Basic analytics and charts
Bottom navigation
CRUD operations for expenses

Future Enhancements
Filtering options (by date range, amount)
CSV data export via email sharing
Budget setting and alerts
Enhanced analytics with trends
Dark mode support

Summary
PocketTracker aim to help students build financial habits
to stay on top of their budgets and start saving at a young age. It will make it easier
by providing graphics and analytics to help users pinpoint their habits.


