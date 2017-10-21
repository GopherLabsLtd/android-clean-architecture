# Android + Kotlin + MVP + Clean Architecture Demo


### Description
*Kotlin-MVP* is an Android application built to highlight __MVP (Model View Presenter)__ and __Clean Architecture__ concepts

### Run Requirements

* Android Studio 3.0 (Beta) - or [Install Kotlin Plugin](https://segunfamisa.com/posts/setting-up-android-studio-for-kotlin-development)

### High Level Layers

#### MVP Concepts
##### Presentation Logic
* `View` - delegates user interaction events to the `Presenter` and displays data passed by the `Presenter`
    * All `Activity`, `Fragment`, `View` subclasses belong to the `View` layer
    * Usually the view is passive / dumb - it shouldn't contain any complex logic and that's why most of the times we don't need write Unit Tests for it
* `Presenter` - contains the presentation logic and tells the `View` what to present
    * Usually we have one `Presenter` per scene (Activity/Fragment)
    * It doesn't reference the concrete type of the `View`, but rather it references the `View` protocol that is implemented usually by a `Activity` subclass
    * It should be a plain `Kotlin` class and not reference any `Android` framework classes - this makes it easier to reuse it maybe in a other application
    * It should be covered by Unit Tests
* `Configurator` - injects the dependency object graph into the scene (view controller)
    * You could very easily use a [DI (dependency injection) library](https://google.github.io/dagger/).
    * Usually it contains very simple logic and we don't need to write Unit Tests for it
* `Router` - contains navigation / flow logic from one scene (view controller) to another
    * In some communities / blog posts it might be referred to as a `FlowController`
    * Writing tests for it is quite difficult because it contains many references to `Android` framework classes so usually we try to keep it really simple and we don't write Unit Tests for it



#### Clean Architecture Concepts
##### Application Logic

* `UseCase / Interactor` - contains the application / business logic for a specific use case in your application
* It is referenced by the `Presenter`. The `Presenter` can reference multiple `UseCases` since it's common to have multiple use cases on the same screen
* It manipulates `Entities` and communicates with `Gateways` to retrieve / persist the entities
* The `Gateway` protocols should be defined in the `Application Logic` layers and implemented by the `Gateways & Framework Logic`
* The separation described above ensures that the `Application Logic` depends on abstractions and not on actual frameworks / implementations
* It should be covered by Unit Tests
* `Entity` - plain `Kotlin` classes / data classes
* Models objects used by your application such as `Order`, `Product`, `Shopping Cart`, etc

### Demo Application Details

 TODO
### Debatable Design Decisions

Giving that a large majority of mobile apps are a thin client on top of a set of APIs and that most of them contain little business logic (since most of the business logic is found in the APIs) some of the `Clean Architecture` concepts can be debatable in the mobile world. Below you can find some:

* Creating a representation for each layer (API, CoreData) might seem like over-engineering. If your application relies heavily on an API that is under your control then it might make sense to model both the entity and the API representation using the same class. You shouldn't however allow the persistence representation leak in the other layers (see [`Parse`](https://techcrunch.com/2016/01/28/facebook-shutters-its-parse-developer-platform/) example that got discontinued)
* If you find that in most cases your `Use Cases / Interactors` simply delegate the actions to the `Gateway` then maybe you don't need the `Use Cases / Interactors` in the first place and you can use the `Gateway` directly in the `Presenter`
* If you want to enforce the layer separation even more you can consider moving all the layers in their own projects / modules
* Some might consider that creating `display(xyz: String)` methods on a `CellView` protocol is over-engineering and that passing a plane `CellViewModel` object to the `CellView` and have the view configure itself with the view model is more straightforward. If you want top keep the view as passive / dumb as possible then you should probably create the methods, but then again simply reading some strings from a view model and setting some labels is not really complex logic

The list above is definitely not complete, and if you identify other debatable decisions please create an issue and we can discuss about it and include it in the list above.

For the items listed above (and also for other items of your own) it is important that you use __your own judgement__ and make an __informed decision__.

Keep in mind that you don't have to make all the design decisions up front and that you can refactor them in as you go.

Discuss about all the design decision with your team members and make sure you are all in agreement.

### Useful Resources

#### MVP & Other presentation patterns

* [iOS Architecture Patterns](https://medium.com/ios-os-x-development/ios-architecture-patterns-ecba4c38de52#.67lieoiim)
* [Architecture Wars - A New Hope](https://swifting.io/blog/2016/09/07/architecture-wars-a-new-hope/)
* [VIPER to be or not to be?](https://swifting.io/blog/2016/03/07/8-viper-to-be-or-not-to-be/?utm_source=swifting.io&utm_medium=web&utm_campaign=blog%20post)
* [Effective Android Architecture](https://realm.io/news/360andev-richa-khandelwal-effective-android-architecture-patterns-java/) - our note here is that you should be careful about coupling you application to Rx* or any other framework for that matter. Please read [Make the Magic go away, by Uncle Bob](https://8thlight.com/blog/uncle-bob/2015/08/06/let-the-magic-die.html) and think twice before letting a framework take over your application.
* [Improve your iOS Architecture with FlowControllers](http://merowing.info/2016/01/improve-your-ios-architecture-with-flowcontrollers/)
* [GUI Architectures, by Martin Fowler](https://martinfowler.com/eaaDev/uiArchs.html)

#### Clean Architecture
* [The Clean Architecture, by Uncle Bob](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)
* [Architecture: The Lost Years, by Uncle Bob](https://www.youtube.com/watch?v=HhNIttd87xs)
* [Clean Architecture, By Uncle Bob](https://8thlight.com/blog/uncle-bob/2011/11/22/Clean-Architecture.html)
* [Uncle Bob's clean architecture - An entity/model class for each layer?](http://softwareengineering.stackexchange.com/questions/303478/uncle-bobs-clean-architecture-an-entity-model-class-for-each-layer)

#### Unit Tests
* [xUnit Test Patterns: Refactoring Test Code](https://www.amazon.com/xUnit-Test-Patterns-Refactoring-Code/dp/0131495054)
* [The Art of Unit Testing: with examples in C#](https://www.amazon.com/Art-Unit-Testing-examples/dp/1617290890/)

### Contributing

Please feel free to open an issue for any questions or suggestions you have!
