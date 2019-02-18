package frc.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.VisionCommand;

public class Vision extends Subsystem {

	private UsbCamera frontCamera;
	private CameraServer cameraServer;
	private CvSink cameraSink;
	private CvSource outputStream;
		
	private Thread visionThread;
	
	private UsbCamera selectedUsbCamera;

	public static final int CAMERA_WIDTH = 320;
	public static final int CAMERA_HEIGHT = 240;
	public static final int CAMERA_FPS = 30;
	
	public Vision() {
		visionThread = new Thread();
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	public void runVision() {
		
		// get an instance of the CameraServer class
		cameraServer = CameraServer.getInstance();

		// create the front camera
		frontCamera = new UsbCamera("Front Camera", 0);
        frontCamera.setResolution(CAMERA_WIDTH, CAMERA_HEIGHT);
        frontCamera.setFPS(CAMERA_FPS);
        
        // create a CvSink for sourcing a camera
        cameraSink = new CvSink("CameraCvSink");

        // set the source for the CvSink to the selected camera
        cameraSink.setSource(selectedUsbCamera);
        
        // create an outPutStream to write video the dashboard
        // This stream can be viewed on SmartDashboard by adding a "CameraServer Stream Viewer" widget
        // and setting its "Camera Choice" property to "Selected Camera"
        outputStream = cameraServer.putVideo("Selected Camera", CAMERA_WIDTH, CAMERA_HEIGHT);
        
        // Creates a mat to hold a video image frame
        Mat image = new Mat();
        
		while (!Thread.interrupted()) {
            cameraSink.setEnabled(false);
			frontCamera.setFPS(CAMERA_FPS);		// set FPS for front camera
            cameraSink.setSource(frontCamera);
            cameraSink.setEnabled(true);

            if(cameraSink.grabFrame(image) == 0) {
                // Error:
                outputStream.notifyError("grabFrame failed: " + cameraSink.getError());
                // Skip iteration
                continue;
		}

        Imgproc.putText(image, "IAN DO U SEE THIS", new Point(10, CAMERA_HEIGHT / 2), 0, 1.0, new Scalar(255, 255, 255));
        
        //Crosshair
        Imgproc.line(image, new Point(image.width()/2,100),new Point(image.width()/2,image.height()-100), new Scalar(255, 255, 255));
        Imgproc.line(image, new Point(150, image.height()/2),new Point(image.width()-150,image.height()/2), new Scalar(255, 255, 255));
        Imgproc.rectangle(image, new Point(10, 10), new Point(CAMERA_WIDTH - 10, 20),new Scalar(255, 255, 255), 2);
			
		//Send image to output stream
        outputStream.putFrame(image);
        }
	}

	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new VisionCommand());
    }
    
}