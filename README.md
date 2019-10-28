# Android Music App

# Introduction
An application that precisely does the following tasks:
- Let's you search for Artists using autocomplete support
- View albums of the selected Artist
- View the selected album in detail (Tracks)
(Makes use of Deezer APIs for content)

# Design and Dependencies
- I opted for Model View Presenter (MVP)
  - Could have gone for MVVM, but since it a minor app, And doesn't use persistence (e.g ROOM)

- Mostly Kotlin (1 or 2 classes in Java)
- OkHttp, Retrofit, RxJava
- Dagger for injection
- For loading images, using Glide
- View dependencies, such as Recycler view (obvious ones)
