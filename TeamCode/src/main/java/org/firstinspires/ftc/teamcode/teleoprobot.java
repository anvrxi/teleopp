package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;


@TeleOp
public class teleoprobot extends LinearOpMode{

    public static int SVRABAT_EXT;
    public static int SVRABAT_INT;

    public static int LIFT_HIGH;
    public static int LIFT_LOW;

    public static int CLAW_OPEN;
    public static int CLAW_CLOSED;

    public static int ARM_COLLECT;
    public static int ARM_DROP;


    boolean CLAW_OP=true;
    boolean RABAT_INIT=true;
    boolean ARMCO=true;
    @Override
    public void runOpMode() {
        GamepadEx g1 = new GamepadEx(gamepad1);
        DcMotor motorfl = hardwareMap.dcMotor.get("motorfl");
        DcMotor motorfr = hardwareMap.dcMotor.get("motorfr");
        DcMotor motorbl = hardwareMap.dcMotor.get("motorbl");
        DcMotor motorbr = hardwareMap.dcMotor.get("motorbr");

        DcMotor lift1 = hardwareMap.dcMotor.get("lift1");
        DcMotor lift2= hardwareMap.dcMotor.get("lift2");

        DcMotor rabat =  hardwareMap.dcMotor.get("rabat");
        Servo svrabat1 = hardwareMap.servo.get("svrabat1");
        Servo  svrabat2 = hardwareMap.servo.get("svrabat2");

        Servo clawdr = hardwareMap.servo.get("clawdr");
        Servo clawst = hardwareMap.servo.get("clawst");

        Servo arm1 = hardwareMap.servo.get("arm1");
        Servo arm2 = hardwareMap.servo.get("arm2");



        motorfr.setDirection(DcMotorSimple.Direction.REVERSE);
        motorbr.setDirection(DcMotorSimple.Direction.REVERSE);

        lift2.setDirection(DcMotorSimple.Direction.REVERSE);

        svrabat2.setDirection(Servo.Direction.REVERSE);

        clawst.setDirection(Servo.Direction.REVERSE);

        arm2.setDirection(Servo.Direction.REVERSE);


        motorfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rabat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.right_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            motorfl.setPower(frontLeftPower);
            motorfr.setPower(backLeftPower);
            motorbl.setPower(frontRightPower);
            motorbr.setPower(backRightPower);

            if(gamepad1.dpad_up && lift1.getCurrentPosition() < LIFT_HIGH)
            {
                lift1.setPower(1);
                lift2.setPower(1);
            }
            if(gamepad1.dpad_down && lift1.getCurrentPosition() < LIFT_LOW)
            {
                lift1.setPower(-1);
                lift2.setPower(-1);
            }

            if(gamepad1.a)
            {
                rabat.setPower(1);
            }

            if(g1.getButtonDown("b") )
            {
                if(RABAT_INIT)
                {
                    svrabat1.setPosition(SVRABAT_EXT);
                    svrabat2.setPosition(SVRABAT_EXT);
                    RABAT_INIT=false;
                }
                else
                {
                    svrabat1.setPosition(SVRABAT_INT);
                    svrabat2.setPosition(SVRABAT_INT);
                    RABAT_INIT=true;
                }
            }

            if(g1.getButtonDown("y") )
            {
                if(CLAW_OP)
                {
                    clawdr.setPosition(CLAW_CLOSED);
                    clawst.setPosition(CLAW_CLOSED);
                    CLAW_OP=false;
                }
                else
                {
                    clawdr.setPosition(CLAW_OPEN);
                    clawst.setPosition(CLAW_OPEN);
                    CLAW_OP=true;
                }
            }

            if(g1.getButtonDown("x") )
            {
                if(ARMCO)
                {
                    arm1.setPosition(ARM_DROP);
                    arm2.setPosition(ARM_DROP);
                    CLAW_OP=false;
                }
                else
                {
                    arm1.setPosition(ARM_COLLECT);
                    arm2.setPosition(ARM_COLLECT);
                    CLAW_OP=true;
                }
            }
        }
    }
}
