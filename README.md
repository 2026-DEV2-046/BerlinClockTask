
Berlin Clock
============
Project Overview
----------------

This project is a **Berlin Clock** built with **Kotlin, Jetpack Compose, and Hilt DI**. It demonstrates **clean architecture principles**, **test-driven development (TDD)**, and **state-driven UI**.

Screenshots
-----------
Sample screens from the kata:

![ui-1](docs/images/screen1.png)
![ui-2](docs/images/screen2.png)

Clock Rules
-----------

*   The **top lamp** blinks to show seconds (Yellow for even seconds, Gray for odd seconds).
*   The next two rows represent **hours**:
    *   Upper row: 4 red lamps, each representing 5-hour blocks.
    *   Lower row: 4 red lamps, each representing 1-hour blocks.
*   The final two rows represent **minutes**:
    *   Upper row: 11 lamps, each representing 5-minute blocks. Every third lamp is red, the rest are yellow.
    *   Lower row: 4 yellow lamps, each representing 1-minute blocks.


How to Clone & Run
------------------

### Clone the repository

    git clone https://github.com/2026-DEV2-046/BerlinClockTask.git
    cd BerlinClockTask
    ./gradlew build
    ./gradlew test
    ./gradlew connectedAndroidTest


### Requirements

- **JDK 11** (recommended)
- **Android Studio Narwhal**
- **Android SDK** installed
- **Gradle 8.13** (Wrapper included)

Tech Stack
----------

*   **Language**: Kotlin
*   **UI**: Jetpack Compose
*   **DI**: Hilt
*   **Architecture**: Clean Architecture + MVVM
*   **State Management**: StateFlow `BerlinClockUiState`
*   **Testing**: JUnit + AndroidX Compose UI Test

TDD Test Coverage
-----------------

*   Unit Tests: Row generation logic (seconds, hours, minutes).
*   ViewModel state emissions.
*   UI Tests: Compose rendering of lamps, time updates.