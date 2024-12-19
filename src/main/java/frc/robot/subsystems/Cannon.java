package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Cannon extends SubsystemBase {
    private TalonFX solenoid;
    private TalonFX trigger;
    private TalonFX elevator;

    public Cannon() {
        solenoid = new TalonFX(20);
        trigger = new TalonFX(21);
        elevator = new TalonFX(22);
    }

    public InstantCommand moveUp() {
        return new InstantCommand(() -> elevator.set(.5));
    }

    public InstantCommand moveDown() {
        return new InstantCommand(() -> elevator.set(-.5));
    }

    public InstantCommand stopElevator() {
        return new InstantCommand(() -> elevator.stopMotor());
    }

    public SequentialCommandGroup loadLongShot() {
        return new InstantCommand(() -> solenoid.setVoltage(5)).withTimeout(6).andThen(() -> solenoid.stopMotor());
    }

    public SequentialCommandGroup loadShortShot() {
        return new InstantCommand(() -> solenoid.setVoltage(5)).withTimeout(3).andThen(() -> solenoid.stopMotor());
    }

    public InstantCommand shoot(Trigger leftTrigger) {
        return new InstantCommand(() -> {
            if (leftTrigger.getAsBoolean()) {
                trigger.set(1);
            }
        });
    }

    public InstantCommand stopShooting() {
        return new InstantCommand(() -> trigger.stopMotor());
    }




}
