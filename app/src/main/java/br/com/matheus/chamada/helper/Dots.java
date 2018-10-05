package br.com.matheus.chamada.helper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * Created by mathe on 29/12/2017.
 */

public class Dots implements LineBackgroundSpan{

    private static final float RADIUS = 9;
    private final int color1, color2, color3, qtd;

    Dots(int color1) {
        this.color1 = color1;
        this.color2 = 0;
        this.color3 = 0;
        qtd = 1;
    }

    Dots(int color1, int color2) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = 0;
        qtd = 2;
    }

    Dots(int color1, int color2, int color3) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        qtd = 3;
    }

    @Override
    public void drawBackground(Canvas canvas, Paint paint,
                               int i, int i1, int i2, int i3, int i4, //left, right, top, baseline, bottom
                               CharSequence charSequence,
                               int i5, int i6, int i7) { //start, end, lineNum
        int oldColor = paint.getColor();
        switch (qtd){
            case 1:
                paint.setColor(color1);
                canvas.drawCircle((i + i1) / 2, i4 + RADIUS, RADIUS, paint);
                break;
            case 2:
                paint.setColor(color1);
                canvas.drawCircle((float) ((i + i1) / 2.3), i4 + RADIUS, RADIUS, paint);
                paint.setColor(color2);
                canvas.drawCircle((float) ((i + i1) / 1.7), i4 + RADIUS, RADIUS, paint);
                break;
            case 3:
                paint.setColor(color1);
                canvas.drawCircle((float) ((i + i1) / 2.8), i4 + RADIUS, RADIUS, paint);
                paint.setColor(color2);
                canvas.drawCircle(((i + i1) / 2), i4 + RADIUS, RADIUS, paint);
                paint.setColor(color3);
                canvas.drawCircle((float) ((i + i1) / 1.55), i4 + RADIUS, RADIUS, paint);
                break;
        }
        paint.setColor(oldColor);
    }
}
