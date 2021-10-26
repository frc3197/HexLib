// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.HexLib.Util;

/** Add your docs here. */
public class InputFilter {
    double input;

    public InputFilter(double Input) {
        this.input = input;
    }

    public double getFiltered(double deadzone) {
        if (input > deadzone || input < -deadzone) {
            return input;
        } else {
            return 0;
        }
    }

}
