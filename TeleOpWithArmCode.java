package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class TeleOpWithArmCode extends OpMode {
    private DcMotor lf;
    private DcMotor lb;
    private DcMotor rf;
    private DcMotor rb;

    private DcMotor scissor;
    private CRServo grabber;

    private float leftPower, rightPower, xValue, yValue;

    @Override
    public void init() {
        lf = hardwareMap.dcMotor.get("LF");
        lb = hardwareMap.dcMotor.get("LB");
        rf = hardwareMap.dcMotor.get("RF");
        rb = hardwareMap.dcMotor.get("RB");

        scissor = hardwareMap.dcMotor.get("scissor");
        // grabber = hardwareMap.crservo.get("grabber");

        scissor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lf.setDirection(DcMotorSimple.Direction.FORWARD);
        lb.setDirection(DcMotorSimple.Direction.FORWARD);
        rf.setDirection(DcMotorSimple.Direction.REVERSE);
        rb.setDirection(DcMotorSimple.Direction.REVERSE);

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
        yValue = gamepad1.left_stick_y * -1;
        xValue = gamepad1.left_stick_x * -1;

        leftPower =  yValue - xValue;
        rightPower = yValue + xValue;

        lf.setPower(Range.clip(leftPower, -1.0, 1.0)*3/2);
        lb.setPower(Range.clip(leftPower, -1.0, 1.0)*3/2);
        rf.setPower(Range.clip(rightPower, -1.0, 1.0)*3/2);
        rb.setPower(Range.clip(rightPower, -1.0, 1.0)*3/2);

        // strafe
        if (gamepad1.right_trigger > 0) {
            lf.setPower(1);
            lb.setPower(-1);
            rf.setPower(-1);
            rb.setPower(1);

        } else if (gamepad1.left_trigger > 0) {
            lf.setPower(-1);
            lb.setPower(1);
            rf.setPower(1);
            rb.setPower(-1);

        }

        // scissor lift
        if (gamepad2.right_bumper) {
            scissor.setPower(0.5);

        } else if (gamepad2.left_bumper) {
            scissor.setPower(-0.5);

        } else {
            scissor.setPower(0);
        }
    }
}