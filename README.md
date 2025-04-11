# Chantuy App

## Project Overview

The Chantuy App is an Android application designed to provide a platform for users to share and discuss various topics. It includes features such as user profiles, topic-based discussions, notifications, and feedback mechanisms.

## Installation Instructions

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   ```
2. **Open the project in Android Studio**.
3. **Build the project** using the Gradle wrapper:
   ```bash
   ./gradlew build
   ```
4. **Run the application** on an Android device or emulator.

## Usage

- **User Profiles**: Create and manage user profiles.
- **Topic Discussions**: Engage in discussions based on various topics.
- **Notifications**: Receive updates and notifications.
- **Feedback**: Provide feedback on discussions and topics.

## Directory Structure

- **Root Directory**: Contains Gradle wrapper scripts and configuration files.
- **`app/` Directory**: Contains the main application module.
  - **`src/main/java/edu/bluejack20_2/chantuy/`**: Main source code directory.
  - **`res/`**: Application resources.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Important Note

Before running the application, ensure to add an API key to the `google-services.json` file located in the `app/` directory. This is necessary for Firebase services to function correctly. If you do not have an API key, you can obtain one from the Firebase Console.
