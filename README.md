# Snowball
Snowball Sample application
- Application functionality
- Application architecture design
- How to build
- Conclusion

## Application functionality
- Download list of items from rest api: [API](https://gist.githubusercontent.com/lenguyenthanh/5bb3bbd3404bb118bfc1/raw/91809efb7abbc43870465cb594cdfd0bce67bb26/videos).
- Displays items as a list.

## Application architecture design
- General Architecture
- Dependency Injection
- Reactive framework
- Model-View-Presenter(MVP)
- Other practices

### Architecture
I follow [Clean Architecture](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html) in this project. I separate project into 3 layers (3 modules):

##### App module
This is presentation layer, its responsibility is to present data model into view which user interact with. 

##### Domain module
This is business layer, we can look at this module and understand what application does. This module is Java module and it dependent with Android Framework.

##### Data module
This is data layer, its responsibility is loading data and caching data. All data comes from here. Other layers do not to care about where data is come from, they just ask Data layer to get data.

##### Layer rules:
Each layer expose its interfaces to another layers. They may dependent on other's abstraction not implementation that mean no layer knows other layers details.

### Dependency Injection
This sample uses dependency inject(DI) ([Dagger 2](http://google.github.io/dagger/)) to avoid boilerplate code and make project clean and follow [Single Responsibility principle](https://en.wikipedia.org/wiki/Single_responsibility_principle). Furthermore, DI give us power to create multiple build types for project. For example, this project has two build type: Release and Debug. For Debug build we have a lot of debug tools(such as: [Logging](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor), [Stetho](http://facebook.github.io/stetho/), [LeakCanary](https://github.com/square/leakcanary)) which should not contain in Release build. Without DI it's really hard to make it.

### Reactive Framework
This project uses reactive programming([RxJava](https://github.com/ReactiveX/RxJava)) for async job as well as app flow.

### Model-View-Presenter(MVP)
This project uses my small library [Nimble](https://github.com/lenguyenthanh/nimble) as an MVP library. You can read more about MVP here: [MVP best practices](https://github.com/lenguyenthanh/nimble/wiki/MVP).

### Other practices
I tried hard to follow [SOLID](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)) in this project. It makes the application easier to maintain, test, and change. When using external dependencies (libraries), I prefer to write a wrapper interface for them, and hide all their implementations from my application. For example: [Image caching library wrapper](https://github.com/lenguyenthanh/Snowball/blob/master/app%2Fsrc%2Fmain%2Fjava%2Fcom%2Flenguyenthanh%2Fsnowball%2Fui%2Fnetwork%2FNetworkBitmapClient.java) or [Fake analytics tool library](https://github.com/lenguyenthanh/Snowball/blob/master/app%2Fsrc%2Fmain%2Fjava%2Fcom%2Flenguyenthanh%2Fsnowball%2Fui%2Fnetwork%2FTracker.java). 
