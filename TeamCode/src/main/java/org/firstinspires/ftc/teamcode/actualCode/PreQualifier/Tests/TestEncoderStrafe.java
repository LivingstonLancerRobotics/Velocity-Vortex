package org.firstinspires.ftc.teamcode.actualCode.PreQualifier.Tests;

import org.firstinspires.ftc.teamcode.LancerLinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Keys;
import org.firstinspires.ftc.teamcode.LancerOpMode;

/**
 * Created by yibin.long on 10/27/2016.
 */


@Autonomous (name = "TestEncoderStrafe", group = "Autonomous")
public class TestEncoderStrafe extends LancerLinearOpMode{

    public void moveSideToSide(double inches, boolean left, double power) {
        inches = inches - 5; //Conversion rate due to drift/high speed (NEED TO CHANGE THIS)
        double inches_per_rev = 560.0 / (Keys.WHEEL_DIAMETER * Math.PI);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (left) {
            fl.setTargetPosition(fl.getCurrentPosition() + (int) (inches_per_rev * inches));
            br.setTargetPosition(br.getCurrentPosition() + (int) (inches_per_rev * inches));
        } else {
            fl.setTargetPosition(fl.getCurrentPosition() + (int) (inches_per_rev * inches));
            br.setTargetPosition(br.getCurrentPosition() + (int) (inches_per_rev * inches));
            power = power * -1.0;
        }


        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fr.setPower(power);
        fl.setPower(-power);
        br.setPower(-power);
        bl.setPower(power);

        while (opModeIsActive() && fl.isBusy() && br.isBusy()) {
            telemetry.addData("Moving Left", fl.isBusy());
            telemetry.addData("Moving Right", br.isBusy());
            telemetry.update();
        }

        rest();
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runOpMode() {
        setup();
        waitForStart();
        startUp();
        //moveLeft
        moveSideToSide(20,true,.4);
        sleep(10000);
        //moveRight
        moveSideToSide(10,false,.1);
    }

}