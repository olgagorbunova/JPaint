# JPaint

## Assignmet 1

In this assignment, I have created Class __Assignment1__.
This class includes static fields and methods that facilitate calculation of circle or rectangle area and circumference, as well as _main_ method to run the program.

To read user input, I created an instance of a __Scanner class__ object with _System.in_.

## Assignmet 2

In this assignment, class hierarchy was created for __Rectangle__ and __Circle__ geometric shapes. The two classes inherit from an abstract class __Shape__, which in turn implements an interface _Drawable_.

__Rectangle__ and __Circle__ classes implement abstract methods from class __Shape__ and _Drawable_ methods, as well as provide own methods that calculate some of it's objects' geometric properties.

Classes are tested with the test functions provided in the assignment.

The code is documented in a way that allows for generation of Javadoc output.

## Assignment 3

A custom exception class, inheriting from _java.lang.Exception_, is created. The class is called __ShapeIncomplete__, and an object of the class is thrown if it is not possible to calculate a property of a geometric shape, if a shape is incomplete (second point defining a shape is mossing). __ShapeIncomplete__ class takes a calculation type String as a constructor argument, and overrides _toString_ method with relevant information.

__Shape__ classes abstract methods, as well as __Rectangle__ and __Circle__ classes methods throw the exception.
