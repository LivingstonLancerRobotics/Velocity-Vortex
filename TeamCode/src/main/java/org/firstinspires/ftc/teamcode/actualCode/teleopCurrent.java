package org.firstinspires.ftc.teamcode.actualCode;



import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Keys;


@TeleOp(name="Tele", group = "Teleop")  // @Autonomous(...) is the other common choice

public class teleopCurrent extends OpMode

{

    /* Declare OpMode members. */
    public static volatile AHRS navx_device;

    public static volatile ElapsedTime runtime = new ElapsedTime();

    public static volatile DcMotor fl, fr, bl, br;

    public static volatile float gp1_right_stick_x, gp1_left_stick_y, gp1_left_stick_x;

    public static volatile boolean gp1_dpad_up, gp1_dpad_down, gp1_dpad_right, gp1_dpad_left, gp1_x;

    public static volatile double x, y, z, trueX, trueY;

    public static volatile FPSDrive fpsDrive;
    /*

     * Code to run ONCE when the driver hits INIT

     */

    @Override

    public void init() {

        fl = hardwareMap.dcMotor.get(Keys.fl);

        fr = hardwareMap.dcMotor.get(Keys.fr);

        br = hardwareMap.dcMotor.get(Keys.br);

        bl = hardwareMap.dcMotor.get(Keys.bl);

        navx_device = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get(Keys.cdim),
                Keys.NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData,
                Keys.NAVX_DEVICE_UPDATE_RATE_HZ);

        navx_device.zeroYaw();

        fpsDrive = new FPSDrive();

        telemetry.addData("Status", "Initialized");



        /* eg: Initialize the hardware variables. Note that the strings used here as parameters

         * to 'get' must correspond to the names assigned during the robot configuration

         * step (using the FTC Robot Controller app on the phone).

         */

        // leftMotor  = hardwareMap.dcMotor.get("left motor");

        // rightMotor = hardwareMap.dcMotor.get("right motor");



        // eg: Set the drive motor directions:

        // Reverse the motor that runs backwards when connected directly to the battery

        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors

        //  rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // telemetry.addData("Status", "Initialized");

    }



    /*

     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY

     */

    @Override

    public void init_loop() {

    }



    /*

     * Code to run ONCE when the driver hits PLAY

     */

    @Override

    public void start() {

        runtime.reset();

    }



    /*

     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP

     */

    @Override

    public void loop() {

        fpsDrive.start();

        if(gamepad1.right_stick_x > Keys.deadzone) {
            gp1_right_stick_x = gamepad1.right_stick_x;
        }
        else {
            gp1_right_stick_x = 0;
        }
        if(gamepad1.left_stick_y > Keys.deadzone) {
            gp1_left_stick_y = gamepad1.left_stick_y;
        }
        else {
            gp1_right_stick_x = 0;
        }
        if(gamepad1.left_stick_x > Keys.deadzone) {
            gp1_left_stick_x = gamepad1.left_stick_x;
        }
        else {
            gp1_right_stick_x = 0;
        }
        gp1_dpad_down = gamepad1.dpad_down;
        gp1_dpad_left = gamepad1.dpad_left;
        gp1_dpad_right = gamepad1.dpad_right;
        gp1_dpad_up = gamepad1.dpad_up;
        gp1_x = gamepad1.x;

        if(gp1_x) {
            navx_device.zeroYaw();
        }

        teleopCurrent.x = teleopCurrent.gp1_right_stick_x; //sideways
        teleopCurrent.y = teleopCurrent.gp1_left_stick_y; //forward and backward
        teleopCurrent.z = teleopCurrent.gp1_left_stick_x; //rotation


        if (x == 0 && y == 0 && z == 0) {

            if (gp1_dpad_right) {

                bl.setPower(Keys.MAX_MOTOR_SPEED);
                fl.setPower(Keys.MAX_MOTOR_SPEED);

            } else if (gp1_dpad_up) {

                bl.setPower(-Keys.MAX_MOTOR_SPEED);
                fl.setPower(-Keys.MAX_MOTOR_SPEED);

            } else if (gp1_dpad_down) {

                br.setPower(Keys.MAX_MOTOR_SPEED);
                fr.setPower(Keys.MAX_MOTOR_SPEED);

            } else if (gp1_dpad_left) {

                br.setPower(-Keys.MAX_MOTOR_SPEED);
                fr.setPower(-Keys.MAX_MOTOR_SPEED);

            }

        }


        telemetry.addData("Status", "Running: " + runtime.toString());

        telemetry.addData("GamePad 1 Right Stick X", gp1_right_stick_x);
        telemetry.addData("GamePad 1 Left Stick Y", gp1_left_stick_y);
        telemetry.addData("GamePad 1 Left Stick X", gp1_left_stick_x);
        telemetry.addData("GamePad 1 DPad Down", gp1_dpad_down);
        telemetry.addData("GamePad 1 DPad Up", gp1_dpad_up);
        telemetry.addData("GamePad 1 DPad Left", gp1_dpad_left);
        telemetry.addData("GamePad 1 DPad Right", gp1_dpad_right);
        telemetry.addData("GamePad 1 X", gp1_x);
        telemetry.addData("Yaw", convertYaw(navx_device.getYaw()));
        telemetry.addData("x", x);
        telemetry.addData("y", y);
        telemetry.addData("z", z);
        telemetry.addData("true x", trueX);
        telemetry.addData("true y", trueY);



        // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)

        // leftMotor.setPower(-gamepad1.left_stick_y);

        // rightMotor.setPower(-gamepad1.right_stick_y);

    }

    public float convertYaw (double yaw) {
        if (yaw <= 0) {
            yaw = 360 + yaw;
        }
        return (float)yaw;
    }



    /*

     * Code to run ONCE after the driver hits STOP

     */

    @Override

    public void stop() {

    }
}
//bs
class FPSDrive extends Thread {

    private Thread fpsDrive;


    public void start() {

        if (fpsDrive == null) {

            fpsDrive = new Thread(this, "FPSDrive");

            fpsDrive.start();

        }

    }



    public void run() {

        teleopCurrent.trueX = (Math.cos(Math.toRadians(360-convertYaw(teleopCurrent.navx_device.getYaw())) * teleopCurrent.x)) - (Math.sin(Math.toRadians(360-convertYaw(teleopCurrent.navx_device.getYaw())) * teleopCurrent.y)); //sets trueX to rotated value
        teleopCurrent.trueY = (Math.sin(Math.toRadians(360-convertYaw(teleopCurrent.navx_device.getYaw())) * teleopCurrent.x)) + (Math.cos(Math.toRadians(360-convertYaw(teleopCurrent.navx_device.getYaw())) * teleopCurrent.y));

        teleopCurrent.x = teleopCurrent.trueX;
        teleopCurrent.y = teleopCurrent.trueY;

    }

    public float convertYaw (double yaw) {
        if (yaw <= 0) {
            yaw = 360 + yaw;
        }
        return (float)yaw;
    }

}