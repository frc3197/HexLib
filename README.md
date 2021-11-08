# HexLib


New: This library is actually useless now that SDS released their own with near identical helper classes. Jokes on us, their implementation actually works. Until further notice, this project is shelved








WIP Library for FRC
The aim of this is to reduce the work on CODING Swerve and to get straight to tuning
For the SDS MK3 Modules with support for:
Motor Controllers: TalonFX , CANSparkMax
Gyro: ADXRS450, NavX

Using the SwerveDriveBuilder Class and the other util classes the goal is to make Swerve in less than 20 lines of code.
Current Line Count: 16
Untested

Installation:
Legit just copy the HexLib folder in frc/robot into your projects code.
From there u can just access the classes on their own.
I'd love to do this in a maven repository and make it an actual library, but thats hard and i dont know how to do it : ^  )

Using it:
To create a swerveDrive you can create the object with the buildSwerve function.

SwerveDrive swerveDrive = SwerveDriveBuilder.buildSwerve(swerveDriveBuilder);

From there you just create the prerequisite objects and pass it into the function as a parameter.

Note: Please be careful with what you pass into the creation methods.

For Future Members: This will likely be outdated in someways at some point. Whether the syntax changes slightly, or you need to add new motor types. Feel free to contribute and keep it updated.

