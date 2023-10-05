package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class teleop extends LinearOpMode {
    @Override
    public void runOpMode()  throws InterruptedException
    {

        GamepadEx g1=new GamepadEx(gamepad1);
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        Gamepad previousGamepad = new Gamepad();
        Gamepad currentGamepad = new Gamepad();

        waitForStart();

        if(isStopRequested())
            return;


        double y = -gamepad1.left_stick_y;
        double x = gamepad1.right_stick_x*1.1;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y)+Math.abs(x)+Math.abs(rx) , 1);
        double frontLeftPower = ( y + x + rx ) / denominator;
        double backLeftPower = ( y - x + rx ) / denominator;
        double frontRightPower = ( y - x - rx) / denominator;
        double backRightPower = ( y + x - rx) / denominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);

        DcMotor lift1,lift2;
        lift1 = hardwareMap.dcMotor.get("lift1");
        lift2 = hardwareMap.dcMotor.get("lift2");

        lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if(gamepad1.dpad_up  /* &&lift1.getCurrentPosition()*/)
        {
            lift1.setPower(1);
            lift2.setPower(1);
        }
        else
        {
            lift1.setPower(0);
            lift2.setPower(0);
        }

        Servo claw;
        claw = hardwareMap.servo.get("claw");//
        if(g1.getButton("a"))
            claw.setPosition(claw.getPosition()+0.1);
        else
            claw.setPosition(claw.getPosition()-0.1);

        Servo ax;
        ax=hardwareMap.servo.get("ax");
        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad1);
        if(currentGamepad.b && !previousGamepad.b)
        {
            ax.setPosition(ax.getPosition()+0.1);
        }


    }

}