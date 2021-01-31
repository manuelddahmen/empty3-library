package one.empty3.library;

import org.jcodec.codecs.h264.H264Utils;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.mp4.Brand;
import org.jcodec.containers.mp4.MP4Packet;
import org.jcodec.containers.mp4.MP4TrackType;
//import org.jcodec.containers.mp4.muxer.FramesMP4MuxerTrack;
import org.jcodec.containers.mp4.muxer.MP4Muxer;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
/*false code
public class DecodeJcodec extends VideoDecoder {

public DecodeJcodec(File f, TextureMov tex) {
     super(f,tex);
}
public void run() {
    SeekableByteChannel sink = null;
    SeekableByteChannel source = null;
    try {
        source = new ReadableFileChannel(file);
 //       sink = writableFileChannel(out);

        MP4Demuxer demux = new MP4Demuxer(source);

        H264Decoder decoder = new H264Decoder();

        Transform transform = new Yuv420pToRgb(0, 0);

        MP4DemuxerTrack inTrack = demux.getVideoTrack();

        VideoSampleEntry ine = (VideoSampleEntry) inTrack.getSampleEntries()[0];
        Picture target1 = Picture.create((ine.getWidth() + 15) & ~0xf, (ine.getHeight() + 15) & ~0xf,
                ColorSpace.YUV420);
        Picture rgb = Picture.create(ine.getWidth(), ine.getHeight(), ColorSpace.RGB);
        ByteBuffer _out = ByteBuffer.allocate(ine.getWidth() * ine.getHeight() * 6);
        BufferedImage bi = new BufferedImage(ine.getWidth(), ine.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        AvcCBox avcC = Box.as(AvcCBox.class, Box.findFirst(ine, LeafBox.class, "avcC"));

        decoder.addSps(avcC.getSpsList());
        decoder.addPps(avcC.getPpsList());

        Packet inFrame;
        int totalFrames = (int) inTrack.getFrameCount();
        for (int i = 0; (inFrame = inTrack.getFrames(1)) != null; i++) {
            ByteBuffer data = inFrame.getData();

            Picture dec = decoder.decodeFrame(splitMOVPacket(data, avcC), target1.getData());
            transform.transform(dec, rgb);
            _out.clear();

            AWTUtil.toBufferedImage(rgb, bi);
            imgBuf.add(new ECBufferedImage(bi));
            if (size()>MAXSISE)
              try {
   Thread .sleep(100);
}catch(IOException ex){}
               
        }
    } finally {
        if (sink != null)
            sink.close();
        if (source != null)
            source.close();
    }
}
 
}*/
