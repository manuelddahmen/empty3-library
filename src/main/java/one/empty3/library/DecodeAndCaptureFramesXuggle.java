/*
 *  This file is part of Empty3.
 *
 *     Empty3 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Empty3 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 */

/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package one.empty3.library;
/*__
 * @author manu
 */



import com.xuggle.xuggler.*;

import java.awt.image.BufferedImage;
import java.io.File;

/*__
 * Takes a media container, finds the first video stream, decodes that
 * stream, and then writes video frames at some interval based on the
 * video presentation time stamps.
 *
 * @author trebor
 */

public class DecodeAndCaptureFramesXuggle extends VideoDecoder {
    /*__
     * The number of seconds between frames.
     */

    public final double SECONDS_BETWEEN_FRAMES = 0;

    /*__
     * The number of nano-seconds between frames.
     */

    public final long NANO_SECONDS_BETWEEN_FRAMES =
            (long) (Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);

    /*__
     * Time of last frame write.
     */

    private long mLastPtsWrite = Global.NO_PTS;
   
    /*__
     * Write the video frame out to a PNG file every once and a while.
     * The files are written out to the system's temporary directory.
     *
     * @param picture the video frame which contains the time stamp.
     * @param image   the buffered image to write out
     */

    private synchronized void processFrame(IVideoPicture picture, BufferedImage image) {
        try {
            // if uninitialized, backdate mLastPtsWrite so we get the very
            // first frame

            if (mLastPtsWrite == Global.NO_PTS)
                mLastPtsWrite = picture.getPts() - NANO_SECONDS_BETWEEN_FRAMES;

            // if it's time to write the next frame

            if (picture.getPts() - mLastPtsWrite >= NANO_SECONDS_BETWEEN_FRAMES) {
                // Make a temorary file name


                

                
                // indicate file written

                double seconds = ((double) picture.getPts()) / Global.DEFAULT_PTS_PER_SECOND;

                // update last write time

                mLastPtsWrite += NANO_SECONDS_BETWEEN_FRAMES;
            }
            imgBuf.add(new ECBufferedImage (image));
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*__
     * Takes a media container (file) as the first argument, opens it,
     * reads through the file and captures video frames periodically as
     * specified by SECONDS_BETWEEN_FRAMES.  The frames are written as PNG
     * files into the system's temporary directory.
     */


    public DecodeAndCaptureFramesXuggle(File file, TextureMov refTextureMov) {
        super(file, refTextureMov);
    }

    public void run() {
        System.out.println("run xuggler video texture");
        if (!IVideoResampler.isSupported(
                IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION))
            throw new RuntimeException(
                    "you must install the GPL version of Xuggler (with IVideoResampler" +
                            " support) for this demo to work");

        // create a Xuggler container object

        IContainer container = IContainer.make();

        // open up the container

        if (container.open(file.getAbsolutePath(), IContainer.Type.READ, null) < 0)
            throw new IllegalArgumentException("could not open file: " + file.getAbsolutePath());

        // query how many streams the call to open found

        int numStreams = container.getNumStreams();

        // and iterate through the streams to find the first video stream

        int videoStreamId = -1;
        IStreamCoder videoCoder = null;
        for (int i = 0; i < numStreams; i++) {
            // find the stream object

            IStream stream = container.getStream(i);

            // get the pre-configured decoder that can decode this stream;

            IStreamCoder coder = stream.getStreamCoder();

            if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
                videoStreamId = i;
                videoCoder = coder;
                break;
            }
        }

        if (videoStreamId == -1)
            throw new RuntimeException("could not find video stream in container: " + file.getAbsolutePath());

        // Now we have found the video stream in this file.  Let's open up
        // our decoder so it can do work

        if (videoCoder.open() < 0)
            throw new RuntimeException(
                    "could not open video decoder for container: " + file.getAbsolutePath());

        IVideoResampler resampler = null;
        if (videoCoder.getPixelType() != IPixelFormat.Type.BGR24) {
            // if this stream is not in BGR24, we're going to need to
            // convert it.  The VideoResampler does that for us.

            resampler = IVideoResampler.make(
                    videoCoder.getWidth(), videoCoder.getHeight(), IPixelFormat.Type.BGR24,
                    videoCoder.getWidth(), videoCoder.getHeight(), videoCoder.getPixelType());
            if (resampler == null)
                throw new RuntimeException(
                        "could not create color space resampler for: " + file.getAbsolutePath());
        }

        // Now, we start walking through the container looking at each packet.

        IPacket packet = IPacket.make();
        while (container.readNextPacket(packet) >= 0) {

            // Now we have a packet, let's see if it belongs to our video strea

            if (packet.getStreamIndex() == videoStreamId) {
                // We allocate a new picture to get the data out of Xuggle

                IVideoPicture picture = IVideoPicture.make(videoCoder.getPixelType(),
                        videoCoder.getWidth(), videoCoder.getHeight());

                int offset = 0;
                while (offset < packet.getSize()) {
                    // Now, we decode the video, checking for any errors.

                    int bytesDecoded = videoCoder.decodeVideo(picture, packet, offset);
                    if (bytesDecoded < 0)
                        throw new RuntimeException("got error decoding video in: " + file.getAbsolutePath());
                    offset += bytesDecoded;

                    // Some decoders will consume data in a packet, but will not
                    // be able to construct a full video picture yet.  Therefore
                    // you should always check if you got a complete picture from
                    // the decode.

                    if (picture.isComplete()) {
                        IVideoPicture newPic = picture;

                        // If the resampler is not null, it means we didn't get the
                        // video in BGR24 format and need to convert it into BGR24
                        // format.

                        if (resampler != null) {
                            // we must resample
                            newPic = IVideoPicture.make(
                                    resampler.getOutputPixelFormat(), picture.getWidth(),
                                    picture.getHeight());
                            if (resampler.resample(newPic, picture) < 0)
                                throw new RuntimeException(
                                        "could not resample video from: " + file.getAbsolutePath());
                        }

                        if (newPic.getPixelType() != IPixelFormat.Type.BGR24)
                            throw new RuntimeException(
                                    "could not decode video as BGR 24 bit data in: " + file.getAbsolutePath());

                        // convert the BGR24 to an Java buffered image

                        BufferedImage javaImage = Utils.videoPictureToImage(newPic);

                        // process the video frame

                        processFrame(newPic, javaImage);
                        
                        while(size()>MAXSIZE)
                            try {
                                Thread.sleep(100);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        System.out.println("xuggler image process");
                    }
                }
            } else {
                // This packet isn't part of our video stream, so we just
                // silently drop it.
                do {
                } while (false);
            }
        }

        // Technically since we're exiting anyway, these will be cleaned up
        // by the garbage collector... but because we're nice people and
        // want to be invited places for Christmas, we're going to show how
        // to clean up.

        if (videoCoder != null) {
            videoCoder.close();
            videoCoder = null;
        }
        if (container != null) {
            container.close();
            container = null;
        }
    }
}
