package uk.co.jumprapp.photos.quality;

import android.graphics.Bitmap;

/**
 * Created by jacek on 12/03/16.
 */
public class QualityMeasure {
    public static double getQuality(Bitmap image) {
        double score = Blurr.getBlurrMeasure(image);
        return score;
    }
}
