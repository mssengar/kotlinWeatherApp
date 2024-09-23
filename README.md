# WeatherApp
Welcome to the Weather App! This Android application provides real-time weather information using `Kotlin`, following the `MVVM (Model-View-ViewModel)` architecture and `Clean Architecture` principles. It utilizes `Room` for local data storage and Android `Navigation for seamless user experiences.


## Feature
> - **Real-Time Weather Data**: Fetch current weather information based on user location or user can also search for the city.
> - **Search City**: Easily search for weather data by city name.
> - **Favorite Cities**: Mark cities as favorites for quick access and easily toggle favorite status.
> - **Detailed Weather Information**: Check comprehensive weather details, including mist, humidity, rain, and other conditions.
> - **Offline Support**: Store weather data locally using Room, allowing access without internet connectivity.


### Screenshots
<div class="row" >
  <img src="https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/search_screen_shot.png" width="320">
  <img src="https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/weather_detail.png" width="320">
  <img src="https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/Cache.png" alt="Mountains" width="320">
</div>



### Recording
[demo.webm](https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/demo.webm)



### Technologies Used

> - **Kotlin**: Primary programming language for Android development.
> - **Compose**: Jetpack Compose is used for designing the UI part.
> - **MVVM**: Architectural pattern that helps in separating UI and business logic.
> - **Clean Architecture**: Ensures a modular structure, making the app easy to test and maintain.
> - **Room**: Persistence library for local database operations.
> - **LiveData**: Observable data holder class for UI updates.
> - **Retrofit**: For network requests and API calls.
> - **Navigation Component**: Simplifies navigation between app screens.
> - **Hilt**: Hilt is use for dependency injection, for making the code cleaner and easier to manage.


    
### API
This project uses API data from [weatherapi.com](https://www.weatherapi.com/)


### Folder Structure
```bash
.
├── README.md
weather-app/
│
├── app/
│   ├── core/                # core layer (di, utils)
│     ├── di/                # Dependency injection (if applicable)
│     └── utils/             # Utility classes
│   ├── data/                # Data layer (Room, Retrofit)
│   ├── domain/              # Domain layer (Use cases, Models)
│   ├── presentation/         # Presentation layer (ViewModels, UI)
├── build.gradle
├── gradle
│   └── wrapper
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
└── settings.gradle
```


### Data Flow
<img width="929" alt="DFD" src="https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/clean_archi.png">


## Prerequisites
Make sure you have the following installed:
- [Android Studio](https://developer.android.com/studio)
- [Java Development Kit (JDK) 17]
- Android SDK (set up via Android Studio)
- [Gradle](https://gradle.org/install/) (if you want to use the command line)


### Setup Instruction
You can setup/run the project in two ways - either by downloading the project and compiling locally using Android Studio, or by downloading and installing the debug APK straight from the drive link (https://drive.google.com/file/d/1_q7qU9Y3RLP2b0k_oS82_nQ3-etf1EaA/view?usp=drive_link).


#### Run the project locally

> Make sure you have all the local dependencies setup i.e Android Studio & the Android SDK, [check here](https://developer.android.com/studio/install)

- First off, `git clone` this project
    - `git clone https://github.com/mssengar/kotlinWeatherApp.git`
- Open the project in `Android Studio`pointing to the root folder's `build.gradle` file
- After successfully building the project, run `./gradlew installDebug` in the root project directory to install in any of the available device/emulator



### Testing

> - The app includes unit tests for ViewModels. 
> - To run tests: Open the terminal in Android Studio & run below command
> - **Run Test cases**: `./gradlew testDebugUnitTest`
> - **Generate Coverage**: `./gradlew createDebugCoverageReport`
    

### Design Inspiration
- [Offdesignarea's Weather App design in Dribbble](https://dribbble.com/shots/17003404-Weather-App)

## License
   ```bash
Copyright (C) 2024

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
