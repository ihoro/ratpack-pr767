import com.example.Conf
import com.example.Foo
import com.google.inject.Stage
import com.netflix.governator.guice.LifecycleInjector
import ratpack.guice.Guice

import static ratpack.groovy.Groovy.ratpack

def governatorInjector = LifecycleInjector.builder()
    .inStage(Stage.PRODUCTION)
    .usingBasePackages('com.example')
    .build()
    .createInjector()

ratpack {
  serverConfig {
    props 'conf.properties'
    require '', Conf
  }
  /*
  bindings {
    ...
  }
  */
  handlers {
    register(Guice.registry(governatorInjector))
    get {
      println context.get(Conf).prop1                     // gives null
      println context.get(Foo).conf.prop1                 // gives null
      println context.get(Conf).is(context.get(Foo).conf) // the same instance

      render 'hi'
    }
  }
}
