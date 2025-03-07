package com.example.studioghibliapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

public class RoundCornerPicasso implements Transformation {
    private final int radius;
    private final int margin;

    public RoundCornerPicasso(int radius, int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new android.graphics.BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Path path = new Path();
        RectF rectF = new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin);
        path.addRoundRect(rectF, radius, radius, Path.Direction.CW);

        canvas.drawPath(path, paint);
        source.recycle();

        return output;
    }

    @Override
    public String key() {
        return "round_corners_radius_" + radius + "_margin_" + margin;
    }
}
