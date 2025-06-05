# 💰 Expense Tracker

A modern Android expense tracking application built with Jetpack Compose and Room database. Track your income and expenses with an intuitive interface and comprehensive financial reporting.

## 📱 Screenshots
<table>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/bf1e3550-4b86-4f29-b47f-68445e540a2d" 
           alt="Reports" width="250" />
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/fd191d79-a650-4071-8165-f427790e1f0f" 
           alt="Transaction History" width="250" />
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/915dd7e9-44c2-42c7-a09a-446206bfdba8" 
           alt="Home Screen" width="250" />
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/1b0bc631-bea0-43ee-8e88-afbe20f6ba92" 
           alt="Add Transaction" width="250" />
    </td>
  </tr>
  <tr>
    <td align="center"><b>Home Screen</b></td>
    <td align="center"><b>Add Transaction</b></td>
    <td align="center"><b>History with filters</b></td>
    <td align="center"><b>Financial Report</b></td>
  </tr>
</table>


## ✨ Features

- **💸 Transaction Management**: Add, view income/expense transactions
- **📊 Financial Dashboard**: Real-time balance calculation with income/expense overview
- **🔍 Advanced Filtering**: Filter transactions by type (Income/Expense) and sort by amount or date
- **📈 Financial Reports**: Detailed reporting with transaction categorization
- **🎨 Modern UI**: Beautiful Material Design 3 interface with smooth animations
- **💾 Local Storage**: Secure offline data storage using Room database
- **📅 Date Picker**: Easy date selection for transactions
- **👤 User Profile**: Personalized user experience

## 🏗️ Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern with:

- **Room Database** for local data persistence
- **LiveData** for reactive UI updates
- **Repository Pattern** for data abstraction
- **Jetpack Compose** for modern UI development
- **Navigation Component** with animated transitions

### 📁 Project Structure

```
├── data/
│   ├── TransactionType.kt          # Transaction type enums
│   ├── enums/SortOrder.kt         # Sorting options
│   └── ChartModel.kt              # Data model for charts
├── database/
│   ├── Transaction.kt             # Room entity
│   ├── TransactionDao.kt          # Data Access Object
│   ├── TransactionDatabase.kt     # Room database
│   ├── TransactionRepository.kt   # Repository pattern
│   └── TransactionViewModelFactory.kt
├── model/
│   └── TransactionViewModel.kt    # ViewModel for business logic
├── navigation/
│   ├── AppNavHost.kt             # Navigation setup
│   ├── AppNavigation.kt          # Navigation routes
│   └── BottomNavItem.kt          # Bottom navigation items
├── screens/
│   ├── HomeScreen.kt             # Dashboard/Home screen
│   ├── Add.kt                    # Add transaction screen
│   ├── TransactionScreen.kt      # Transaction history
│   ├── FinancialReport.kt        # Reports and analytics
│   └── Profile.kt                # User profile
├── ui_components/
│   ├── BottomNavigationBar.kt    # Bottom navigation
│   └── DatePickerCmp.kt          # Date picker component
└── MainActivity.kt               # Main activity
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or newer
- Android SDK 24 (API level 24) or higher
- Kotlin 1.8+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/expense-tracker.git
   cd expense-tracker
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository folder

3. **Build the project**
   - Let Android Studio sync the project
   - Build → Make Project (Ctrl+F9)

4. **Run the app**
   - Connect an Android device or start an emulator
   - Run → Run 'app' (Shift+F10)

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Database**: Room
- **Navigation**: Navigation Component with Compose
- **Reactive Programming**: LiveData
- **Material Design**: Material Design 3
- **Animation**: Accompanist Navigation Animation

## 📊 Key Dependencies

```gradle
dependencies {
    // Compose BOM
    implementation platform('androidx.compose:compose-bom:2023.10.01')
    
    // Room Database
    implementation "androidx.room:room-runtime:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    
    // Navigation
    implementation "androidx.navigation:navigation-compose:$nav_version"
    implementation "com.google.accompanist:accompanist-navigation-animation:0.32.0"
    
    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version"
    implementation "androidx.compose.runtime:runtime-livedata:$compose_version"
    
    // Material Design 3
    implementation "androidx.compose.material3:material3:$material3_version"
}
```

## 📱 App Screens

### 🏠 Home Screen
- Account balance overview
- Recent transactions list
- Income/Expense summary cards
- Quick navigation to other screens

### ➕ Add Transaction
- Amount input with currency symbol
- Transaction type selection (Income/Expense)
- Category and description fields
- Date picker integration
- Form validation with user feedback

### 📋 Transaction History
- Complete transaction list
- Advanced filtering and sorting options
- Modal bottom sheet for filter controls
- Transaction type indicators

### 📈 Financial Reports
- Tabbed interface for Income/Expense reports
- Transaction categorization
- Visual data representation
- Detailed transaction breakdowns

### 👤 Profile
- User information display
- Settings and preferences
- Data export options
- Account management



## 🎨 UI Features

- **Material Design 3** theming
- **Responsive layouts** for different screen sizes
- **Smooth animations** between screens
- **Interactive components** with proper feedback
- **Consistent color scheme** throughout the app
- **Accessibility support** with semantic descriptions



## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



⭐ **Star this repository if you found it helpful!**
