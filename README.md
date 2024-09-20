# WeatherApp
A simple weather app that shows daily weather conditions, and weather forecasts; built with `Kotlin` using `MVVM` and `Clean Architecture` design pattern.

## Feature
> - This Weather App is an Android App build with MVVM Architecture using Kotlin language and Retrofit network calling library.
> - The app shows your current location, and a list of cities showing current temperature of the city.
> - When a city is clicked, it will direct user to detailed screen whereby user will be able to see the current weather state of the city and forecast of the next 4-5 days, depend on api data. User can add city to his/her favorite lists.
> - To add a city to favorite list, User will have to mark it as favorite by clicking on heart icon. Cache data will be lost if app is uninstalled or data is cleared.

### Screenshots
<img width="305" height="480" alt="DFD" src="https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/search_screen_shot.png">
<img width="305" height="480" alt="DFD" src="https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/weather_detail.png">
<img width="305" height="480" alt="DFD" src="https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/Cache.png">


### Recording
[demo.webm](https://github.com/mssengar/kotlinWeatherApp/blob/main/app/src/main/java/com/mss/weatherapp/asset/demo.webm)



### Technologies

    - MVVM with Clean Architecture for design pattern
    - Hilt for dependency injection
    - Jetpack Compose for UI component design
    - Retrofit & OkHttp for network requests
    - Room DB for local caching
### API
This project uses API data from [weatherapi.com](https://www.weatherapi.com/)

### Folder Structure
```bash
.
├── README.md
├── app
│   ├── build. gradle
│   ├── proguard-rules.pro
│   └── src
│       ├── androidTest
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── com
│       │   │       └── mss
│       │   │           └── weatherapp
│       │   │               ├── WeatherApplication.kt
│       │   │               ├── data
│       │   │               │   ├── repositories
│       │   │               │   └── source
│       │   │               │       ├── device
│       │   │               │       └── weather
│       │   │               ├── domain
│       │   │               │   ├── models
│       │   │               │   └── usecases
│       │   │               │       ├── device
│       │   │               │       └── weather
│       │   │               ├── core
│       │   │               │   └── di
│       │   │               │   └── util
│       │   │               └── presentation
│       │   │                   ├── component
│       │   │                   │   └── theme
│       │   │                   └── screen
│       │   │                       ├── home
│       │   │                       │   └── model
│       │   │                       ├── settings
│       │   │                       └── weather_detail
│       │   └── res
│       │       ├── drawable
│       │       ├── values
│       │       └── xml
│       └── test
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


### Setup Instruction
You can setup/run the project in two ways - either by downloading the project and compiling locally using Android Studio, or by downloading and installing the debug APK artifact straight from the build pipeline.

#### Run the project locally

> Make sure you have all the local dependencies setup i.e Android Studio & the Android SDK, [check here](https://developer.android.com/studio/install)

- First off, `git clone` this project
    - `git clone https://github.com/mssengar/kotlinWeatherApp.git`
- Open the project in `Android Studio`pointing to the root folder's `build.gradle` file
- After successfully building the project, run `./gradlew installDebug` in the root project directory to install in any of the available device/emulator

### Design Inspiration
- [Offdesignarea's Weather App design in Dribbble](https://dribbble.com/shots/17003404-Weather-App)
