package one.empty3.library;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;

import com.zakgof.velvetvideo.IAudioDecoderStream;
import com.zakgof.velvetvideo.IAudioEncoderStream;
import com.zakgof.velvetvideo.IAudioFrame;
import com.zakgof.velvetvideo.IDecodedPacket;
import com.zakgof.velvetvideo.IDemuxer;
import com.zakgof.velvetvideo.IMuxer;
import com.zakgof.velvetvideo.IVelvetVideoLib;
import com.zakgof.velvetvideo.MediaType;
import com.zakgof.velvetvideo.impl.VelvetVideoLib;

public class DecodeVelvet extends VideoDecoder {
   
/***
* init, start, run, and block on maxsize reached
* @param file video to draw on surface
* @param refTextureMov texture to apply
*/
    public VideoDecoder(File file, TextureMov refTextureMov) {
        super(file, refTextureMov);
	init();

   }
   public void run() {
	IVelvetVideoLib lib = VelvetVideoLib().getInstance();
	try (IDemuxer demuxer = lib.demuxer(new File(file))) {
	    IDecoderVideoStream videoStream = demuxer.videoStream(0);
	    IFrame videoFrame;
	    while ((videoFrame = videoStream.nextFrame()) != null) {
	   	    BufferedImage image = videoFrame.image();
	   	    imgBuf. add(new ECBufferedImage(image) );

	    }
	}      
    }
}
