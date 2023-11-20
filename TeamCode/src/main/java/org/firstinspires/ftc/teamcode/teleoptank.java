package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class teleoptank extends LinearOpMode{
    @Override
    public void runOpMode() {
        DcMotor motorfl = hardwareMap.dcMotor.get("motorfl");
        DcMotor motorfr = hardwareMap.dcMotor.get("motorfr");
        DcMotor motorbl = hardwareMap.dcMotor.get("motorbl");
        DcMotor motorbr = hardwareMap.dcMotor.get("motorbr");

        motorfr.setDirection(DcMotorSimple.Direction.REVERSE);
        motorbr.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(!isStopRequested() && opModeIsActive())
        {
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
        }
    }
}
