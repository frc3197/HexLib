// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.HexLib.Util;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/** Add your docs here. */
public class FilteredController {
    private XboxController controller;

    public FilteredController(XboxController controller){
        this.controller = controller;
    }
    
    public double getX(Hand hand, double deadzone){
        return new InputFilter(controller.getX(hand)).getFiltered(deadzone);
    }

    public double getY(Hand hand, double deadzone){
        return new InputFilter(controller.getY(hand)).getFiltered(deadzone);
    }

    public double getTrigger(Hand hand, double deadzone){
        return new InputFilter(controller.getTriggerAxis(hand)).getFiltered(deadzone);
    }
}
