package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Cannon extends SubsystemBase {
    private TalonSRX solenoid;
    private TalonSRX trigger;
    private TalonSRX elevator;

    public Cannon() {
        solenoid = new TalonSRX(20);
        trigger = new TalonSRX(16);
        elevator = new TalonSRX(15);
    }

    public InstantCommand moveUp() {
        return new InstantCommand(() -> elevator.set(TalonSRXControlMode.PercentOutput, 1));
    }

    public InstantCommand moveDown() {
        return new InstantCommand(() -> elevator.set(TalonSRXControlMode.PercentOutput, -1));
    }

    public InstantCommand stopElevator() {
        return new InstantCommand(() -> elevator.set(TalonSRXControlMode.PercentOutput, 0));
    }

    public SequentialCommandGroup loadLongShot() {
        return new InstantCommand(() -> solenoid.set(TalonSRXControlMode.PercentOutput, 1)).andThen(new WaitCommand(1.5)).andThen(() -> solenoid.set(TalonSRXControlMode.PercentOutput, 0));
    }

    public SequentialCommandGroup loadShortShot() {
        return new InstantCommand(() -> solenoid.set(TalonSRXControlMode.PercentOutput, 1)).andThen(new WaitCommand(1)).andThen(() -> solenoid.set(TalonSRXControlMode.PercentOutput, 0));
    }

    public InstantCommand shoot(Trigger leftTrigger) {
        return new InstantCommand(() -> {
            if (leftTrigger.getAsBoolean()) {
                trigger.set(TalonSRXControlMode.PercentOutput, 1);
            }
        });
    }
    
    public InstantCommand stopShooting() {
        return new InstantCommand(() -> trigger.set(TalonSRXControlMode.PercentOutput, 0));
    }
}
