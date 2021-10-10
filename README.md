# Freeletics Code Challange Documentation
**Freeletics Square Inc challenge App** is a friendly Android App which list Square Inc repos details like name and rating stars on GitHub

It gives the user ability to bookmark/unbookmark a repo which will be shown as an icon in the repos list main screen.
This change is stored locally on the user device 

### App architecture

The app is written in `Kotlin` based on `MVVM` architecture. Repository design pattern has been used as an abstraction of the data layer

The app communicates with the backend through an API as a source of data using Retrofit Library. 
All data is saved locally in the device using a locale database (sqlite).
Databinding is used to bind the data layer with the UI.
Hilt is used for DI to provide objects referencing.


### Data flow 
 
 `RepoListViewModel` calls `DataRepo` which is responsible for getting the data from the backend server through a json API. The data will be saved into the local database, after that the data will be passed to the View Model using the `DataRepo` class.
 
 The API response is saved in the database as a Coroutines Flow object to make sure all subscribers will be notified of any change that happened to the data. which will save us from having extra observers like livedata
 
 Please refer to the diagram below for a visual view
 
  ![](https://provider-stg.s3.ap-southeast-1.amazonaws.com/data-flow-digram.jpeg)

  [KTlint](https://github.com/jlleitschuh/ktlint-gradle) and [Kotlin Code Style](https://kotlinlang.org/ ) is used to keep a good code quality and to follow Kotlin best practices
  
## Unit Test
The unit test is based on `AndroidJUnitRunner` and `Coroutines`.
The test has been written for `AppDatabase` only with full coverage.


### App Setup 
1. Android Gradel Build `gradle:7.0.2`
2. install Kotlin `kotlin-gradle-plugin:1.5.31`
3. `java 8`
4. `Android SDK 31`


## Used Library
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)  for DI
- [Retrofit](https://square.github.io/retrofit/) for API connection 
- [Moshi](https://github.com/square/moshi) for parsing 
- [Coroutines for async](https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMIrOjF5vil8wIV0ZlmAh05kw-zEAAYASAAEgIu_PD_BwE&gclsrc=aw.ds)
- [Room](ttps://developer.android.com/jetpack/androidx/releases/room?gclid=EAIaIQobChMI_-b7oPK58wIVP5lmAh1tjAGXEAAYASAAEgJozvD_BwE&gclsrc=aw.ds) for local storage 
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Truth](https://truth.dev/) for unit test 
- [ktlint](https://github.com/jlleitschuh/ktlint-gradle) plugin for clean code