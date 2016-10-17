package org.firstinspires.ftc.teamcode.actualCode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Keys;
import org.firstinspires.ftc.teamcode.LancerLinearOpMode;

/**
 * Created by spork on 10/17/2016.
 */
@Autonomous (name = "TimeBasedAutonSimple", group = "Autonomous")
public abstract class TimeBasedAutonSimple extends LancerLinearOpMode {

    public static DcMotor fl, fr, br, bl, catapult;

    public static AHRS navx_device;

    public static boolean turnComplete = false;

    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.dcMotor.get(Keys.fl);

        fr = hardwareMap.dcMotor.get(Keys.fr);

        br = hardwareMap.dcMotor.get(Keys.br);

        bl = hardwareMap.dcMotor.get(Keys.bl);

        fr.setDirection(DcMotorSimple.Direction.REVERSE);

        br.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        fl.setPower(0.6);
        fr.setPower(0.6);
        br.setPower(0.6);
        bl.setPower(0.6);
        sleep(4000);
        fl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
    }
}
