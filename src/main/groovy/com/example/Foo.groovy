package com.example

import com.netflix.governator.annotations.AutoBindSingleton

import javax.inject.Inject

@AutoBindSingleton
class Foo {
  @Inject Conf conf
}
