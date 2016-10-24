package org.firstinspires.ftc.teamcode.actualCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Keys;
import org.firstinspires.ftc.teamcode.LancerLinearOpMode;
import org.firstinspires.ftc.teamcode.LancerOpMode;

/**
 * Created by shlok.khandelwal on 10/24/2016.
 */
@Autonomous(name="Encoder Test", group="Test")
    public class EncoderTester extends LancerLinearOpMode{

    @Override
    public void runOpMode() {
        setup();
        waitForStart();
        moveStraight(br, 12, true, .6);
        sleep(1000);
        //moveStraightBackup(br, 12, true, .6);
    }
}
