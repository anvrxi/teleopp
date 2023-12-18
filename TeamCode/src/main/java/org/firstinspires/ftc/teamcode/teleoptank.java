package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class teleoptank extends LinearOpMode{

    public static int  MAX_VALUE_LIFT,MIN_VALUE_LIFT;
    public static int SERVO_POS_INIT, SERVO_POS_DROP;
    double rightPower,leftPower;

    boolean  BUTTON_B_IS_PRESSED=false;


    @Override
    public void runOpMode() {
        GamepadEx g1 =new GamepadEx(gamepad1);
        DcMotor motorfl = hardwareMap.dcMotor.get("motorfl");
        DcMotor motorfr = hardwareMap.dcMotor.get("motorfr");
        DcMotor motorbl = hardwareMap.dcMotor.get("motorbl");
        DcMotor motorbr = hardwareMap.dcMotor.get("motorbr");

        DcMotor liftLeft= hardwareMap.dcMotor.get("liftLeft");
        /*


        DcMotor axHydro = hardwareMap.dcMotor.get("axHydro");


        DcMotor liftRight = hardwareMap.dcMotor.get("lift1");


        Servo boxRight = hardwareMap.servo.get("boxRight");
        Servo boxLeft = hardwareMap.servo.get("boxLeft");

         */


        double drive;
        double turn;


        motorfr.setDirection(DcMotorSimple.Direction.REVERSE);
        motorbr.setDirection(DcMotorSimple.Direction.REVERSE);

        liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeft.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);

        /*




        liftRight.setDirection(DcMotorSimple.Direction.REVERSE);

        boxRight.setDirection(Servo.Direction.REVERSE);

        liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        liftRight.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);


         */
        waitForStart();

        while(!isStopRequested() && opModeIsActive())
        {
            drive = gamepad1.right_stick_y*-1;
            turn = gamepad1.right_trigger-gamepad1.left_trigger;

            rightPower= Range.clip(drive+turn, -1,1);
            leftPower=drive-turn;

            motorfl.setPower(leftPower);
            motorfr.setPower(rightPower);
            motorbl.setPower(leftPower);
            motorbr.setPower(rightPower);
        /*
            if(gamepad1.a) axHydro.setPower(1);


            if(g1.getButtonDown("b"))
            {
                if( !BUTTON_B_IS_PRESSED);
                {
                    boxRight.setPosition(SERVO_POS_DROP);
                    boxLeft.setPosition(SERVO_POS_DROP);
                    BUTTON_B_IS_PRESSED=true;
                }
                if(BUTTON_B_IS_PRESSED)
                {
                    boxRight.setPosition(SERVO_POS_INIT);
                    boxLeft.setPosition(SERVO_POS_INIT);
                    BUTTON_B_IS_PRESSED=false;
                }
            }






        */

            if(gamepad1.dpad_up && liftLeft.getCurrentPosition()<MAX_VALUE_LIFT)
            {
                liftLeft.setPower(1);
            }
            if(gamepad1.dpad_down && liftLeft.getCurrentPosition()<MIN_VALUE_LIFT)
            {
                liftLeft.setPower(-1);
            }





        }
    }
}