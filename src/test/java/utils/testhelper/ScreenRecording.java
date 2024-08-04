package utils.testhelper;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecording {

  private SpecializedScreenRecorder recorder;

  public void startRecording(String folder, String fileName)
      throws IOException, AWTException, InterruptedException {
    if (!TestParams.SCREEN_RECORDING) {
      return;
    }

    File file = new File(folder);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    Rectangle captureSize = new Rectangle(0, 0, width, height);

    GraphicsConfiguration gc =
        GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration();

    this.recorder =
        new SpecializedScreenRecorder(
            gc,
            captureSize,
            new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
            new Format(
                MediaTypeKey,
                MediaType.VIDEO,
                EncodingKey,
                ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                CompressorNameKey,
                ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                DepthKey,
                24,
                FrameRateKey,
                Rational.valueOf(15),
                QualityKey,
                1.0f,
                KeyFrameIntervalKey,
                15 * 60),
            new Format(
                MediaTypeKey,
                MediaType.VIDEO,
                EncodingKey,
                "black",
                FrameRateKey,
                Rational.valueOf(30)),
            null,
            file,
            fileName);
    this.recorder.start();
    Thread.sleep(2000);
  }

  public File stopRecording() throws IOException, InterruptedException {
    if (this.recorder != null) {
      Thread.sleep(2000);
      this.recorder.stop();
      return this.recorder.file;
    }
    return null;
  }

  class SpecializedScreenRecorder extends ScreenRecorder {

    private final String name;
    public File file;

    public SpecializedScreenRecorder(
        GraphicsConfiguration cfg,
        Rectangle captureArea,
        Format fileFormat,
        Format screenFormat,
        Format mouseFormat,
        Format audioFormat,
        File movieFolder,
        String name)
        throws IOException, AWTException {
      super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
      this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
      if (!movieFolder.exists()) {
        movieFolder.mkdirs();
      } else if (!movieFolder.isDirectory()) {
        throw new IOException("\"" + movieFolder + "\" is not a directory.");
      }
      file = new File(movieFolder, name + "." + Registry.getInstance().getExtension(fileFormat));
      return file;
    }
  }
}
